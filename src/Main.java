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


		for(int i = 0; i < 5; i++){
			f.start();
			d.start();
			e.start();
			m.start();
			w.start();

			c.start();
			try{
				c.getThreadInstance().join();
			}catch(Exception ex){}
		}
		
	}

}
