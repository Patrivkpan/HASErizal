package util;

import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;

/**
 * Reads instructions from files
 */
public class InstructionFileReader{
	/* ATTRIBUTES */
	private static InstructionFileReader instance;
	
	/* CONSTRUCTOR */
	private InstructionFileReader(){}

	/* METHODS */
	public void readFile(String path){
		ArrayList<String> textData = null;
		/* loads file */
		try{
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			textData = new ArrayList<String>();
			
			String s;
			while((s = br.readLine()) != null)
				textData.add(s);
			br.close();
			
		} catch(Exception e) {
			System.out.println("File not found");
			return;
		}

		/* set lines */
		String lines[] = textData.toArray(new String[0]);

		/* Parses instructions per line */
		InstructionParser ip = InstructionParser.getInstance();	
		ArrayList<String[]> instructions = new ArrayList<String[]>(); 	

		for(int i = 0; i < lines.length; i++){
			String line[];
			try {
				line = ip.parseString(lines[i], i);
			} catch(InstructionSyntaxException iis) {
				System.out.println(iis.getMessage());
				return;
			}
			for(String t : line)
					System.out.print("| " + t + " |");
			System.out.println();

			instructions.add(line);
		}
		InstructionMemory memory = InstructionMemory.getInstance();
		memory.setInstructions(instructions.toArray(new String[0][0]));
	}

	/* GETTERS AND SETTERS */
	public static InstructionFileReader getInstance(){
		if(InstructionFileReader.instance == null) 
			InstructionFileReader.instance = new InstructionFileReader();
		
		return InstructionFileReader.instance;
	}
	
}