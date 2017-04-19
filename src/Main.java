import java.util.ArrayList;

import util.InstructionFileReader;
import util.InstructionParser;
import util.InstructionSyntaxException;

import process.Fetch;

public class Main {

	public static void main(String[] args) {
		String lines[] = (InstructionFileReader.getInstance("bin/ins.txt"))
							.readFile();

		InstructionParser ip = InstructionParser.getInstance();
		ArrayList<String[]> instructions = new ArrayList<String[]>(); 
		for(int i = 0; i < lines.length; i++){
			String line[];
			try {
				line = ip.parseString(lines[i], i);
			} catch(InstructionSyntaxException iis) {
				System.out.println(iis.getMessage());
				break;
			}
			for(String t : line)
					System.out.print("| " + t + " |");
			System.out.println();

			instructions.add(line);
		}

		Fetch f = Fetch.getInstance(instructions.toArray(new String[0][]));
		if(!f.start())
			System.out.println("Instructions not found.");
	}

}
