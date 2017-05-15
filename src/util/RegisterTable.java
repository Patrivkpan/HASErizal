package util;
import util.Register;
import util.Clock;
import process.Fetch;
import process.Writeback;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class RegisterTable{
	private static RegisterTable instance;
	private static final String FILE_TEXT = "FDEMW.txt";
	String name;

	public void printRegister(){
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter("FDEMW.txt", true));
			
			writer.append("===========================\n");
			writer.append("Clock:" + Register.getRegister("PC").getValue() + "\n");
			for(int j = 1; j < 33; j++){
	            name = "R" + j;
		        writer.append(Register.getRegister(name).getName() + ": " + Register.getRegister(name).getValue());
		    	writer.write("\n"); 
		    }
		    writer.append("PC:" + Register.getRegister("PC").getValue() + "\n");
		    writer.append("MAR:" + Register.getRegister("MAR").getValue() +"\n");
		    writer.append("MBR:" + Register.getRegister("MBR").getValue()+"\n");
		    writer.append("OF: " + Register.getRegister("OF").getValue()+"\n");
		    writer.append("NF: " + Register.getRegister("NF").getValue()+"\n");
		    writer.append("ZF: " + Register.getRegister("ZF").getValue()+"\n");
			writer.append("===========================\n\n");
			writer.close(); 
		}
		catch(IOException e) { 
			
		}
		catch(Exception e) { 
			System.out.println(e.getMessage()); 
		} 

		// for(int j = 1; j < 33; j++){
  //           name = "R" + j;
	 //        System.out.println(Register.getRegister(name).getName() + ": " + Register.getRegister(name).getValue());
	 //    }

		// System.out.println("PC:" + Register.getRegister("PC").getValue());
		// System.out.println("MAR:" + Register.getRegister("MAR").getValue());
		// System.out.println("MBR:" + Register.getRegister("MBR").getValue());
		// System.out.println("OF: " + Register.getRegister("OF").getValue());
		// System.out.println("NF: " + Register.getRegister("NF").getValue());
		// System.out.println("ZF: " + Register.getRegister("ZF").getValue());	    
	}

	public static RegisterTable getInstance(){
		if(RegisterTable.instance == null)
			RegisterTable.instance = new RegisterTable();

		return RegisterTable.instance;
	}
}