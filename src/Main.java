import java.util.ArrayList;

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

public class Main {

	public static void main(String[] args) {
		InstructionFileReader.getInstance().readFile("bin/ins.txt");

		Clock c = Clock.getInstance();
		Fetch f = Fetch.getInstance();
		Decode d = Decode.getInstance();
		Execute e = Execute.getInstance();
		Memory m = Memory.getInstance();
		Writeback w = Writeback.getInstance();


		for(int i = 0; i < 10; i++){
			
			w.start();
			m.start();
			e.start();
			d.start();
			f.start();
			
			c.start();
			try{
				c.getThreadInstance().join();
			}catch(Exception ex){}

			System.out.println("===========================\n");
		}
		
	}

}
