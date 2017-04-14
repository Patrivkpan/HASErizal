import util.InstructionFileReader;
import util.InstructionParser;

public class Main {

	public static void main(String[] args) {
		String[] instructions = (InstructionFileReader.getInstance("bin/ins.txt")).readFile();
		for(String s: instructions){
			String[] sample = (InstructionParser.getInstance()).parseString(s);
			if(sample == null){
				System.out.println("Invalid Instruction found: " + s);
				break;
			}
			for(String t : sample)
					System.out.print("| " + t + " |");
			System.out.println();
		}
		
	}

}
