package util;
import util.Register;
import util.Clock;
import process.Fetch;
import process.Writeback;

public class RegisterTable{
	private int cycle = Clock.getCycle();
	private String instruction[];
	private Register dest = Register.getRegister(instruction[1]);
	private Register src = Register.getRegister(instruction[2]);
	private Register mar = Fetch.getMAR();
	private int pc = Writeback.getPc();
	String name;

	public void printRegister(){
		for(int i = 1; i < cycle; i++){
			System.out.println("Clock cycle:" + i);

			for(int j = 1; j < 33; j++){
	            name = "R" + i;
	            if(src ==  null) System.out.println(name + ": ");
	            else System.out.println(name + ": " + src);
	        }

			System.out.println("PC:" + pc);
			System.out.println("MAR:" + mar);
			//System.out.println("MBR:" + mbr);
			if(dest == Register.getRegister("OF")) System.out.println("OF: " + src);
			if(dest == Register.getRegister("NF")) System.out.println("NF: " + src);
			if(dest == Register.getRegister("ZF")) System.out.println("ZF: " + src);

	    }
	}
		
}