package process;
import util.Register;

public class Memory extends Thread{
	private static Memory instance;
	private String instruction[];
	
	private Memory(){}
	
	@Override
	public void run(){
		try{
			Thread.sleep(1000);
		} catch (Exception e) {}
	}
}