import java.util.ArrayList;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

import util.InstructionSyntaxException;
import util.InstructionFileReader;
import util.InstructionParser;
import util.InstructionMemory;
import util.Clock;

import process.Fetch;
import process.Decode;
import process.Execute;
import process.Memory;
import process.Writeback;
import util.RegisterTable;

public class Main {

	public static void main(String[] args) {
		InstructionFileReader.getInstance().readFile("bin/ins.txt");
		int lines = InstructionMemory.getInstance().getLines();

		Clock c = Clock.getInstance();
		Fetch f = Fetch.getInstance();
		Decode d = Decode.getInstance();
		Execute e = Execute.getInstance();
		Memory m = Memory.getInstance();
		Writeback w = Writeback.getInstance();
		RegisterTable rt = RegisterTable.getInstance();
		w.setLines(lines);

		do{
			System.out.println("Cycle: " + c.getCycle());

			m.setNext();
			e.setNext();
			d.setNext();
			f.setNext();

			w.start();
			m.start();
			e.start();
			d.start();
			f.start();
			
			c.start();
			rt.printRegister();
			try{
				c.getThreadInstance().join();
			}catch(Exception ex){}


			System.out.println("===========================\n");
		} while(!w.isDone());
		System.out.println("Stalls: " + c.getStalls());
		System.out.println("Clock Cycles: " + c.getCycle());
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter("FDEMW.txt", true));
			writer.append("Total Stalls: " + c.getStalls()+"\n\n");
			writer.close(); 
		}
		catch(IOException exc) { 
	
		}
		catch(Exception exc) { 
			System.out.println(exc.getMessage()); 
		}
	}

}
