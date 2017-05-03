import java.util.ArrayList;

import util.InstructionSyntaxException;
import util.InstructionFileReader;
import util.InstructionParser;
import util.InstructionMemory;
import util.Clock;

import process.Fetch;
import process.Decode;

public class Main {

	public static void main(String[] args) {
		InstructionFileReader.getInstance().readFile("bin/ins.txt");
	}

}
