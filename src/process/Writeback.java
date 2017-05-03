package process;
import util.Register;

public class Writeback extends Thread{
	private static Writeback instance;
	private Register dest, src;
	private int value;
	
	private Writeback(){}
	
	@Override
	public void run(String name, int value){
		Register.getRegister(name).setValue(value);
	}
}