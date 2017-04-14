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
	private String path;
	
	/* CONSTRUCTOR */
	private InstructionFileReader(String file_path){
		path = file_path;
	}

	/* METHODS */
	public String[] readFile(){
		ArrayList<String> textData = null;
		try{
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			textData = new ArrayList<String>();
			
			String s;
			while((s = br.readLine()) != null)
				textData.add(s);
			br.close();
			
		} catch(Exception e) {System.out.println("File not found");};

		return textData.toArray(new String[0]);
	}

	/* GETTERS AND SETTERS */
	public static InstructionFileReader getInstance(){
		if(InstructionFileReader.instance == null) 
			InstructionFileReader.instance = new InstructionFileReader("");
		
		return InstructionFileReader.instance;
	}
	
	public static InstructionFileReader getInstance(String file_path){
		if(InstructionFileReader.instance == null) 
			InstructionFileReader.instance = new InstructionFileReader(file_path);
		
		else InstructionFileReader.instance.setPath(file_path);
		
		return InstructionFileReader.instance;
	}
	
	public void setPath(String path){
		this.path = path;
	}
}