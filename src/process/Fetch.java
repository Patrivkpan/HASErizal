package process;
import util.Register;

public class Fetch implements Runnable{

	private static Fetch instance;
	private Thread tInstance;
	private String instructions[][];
	
	private Fetch(){}

	@Override
	public void run(){
		Register pc = Register.getRegister("PC");
		Register mar = Register.getRegister("MAR");

		mar.setValue(pc.getValue());
		pc.setValue(pc.getValue() + 1);

		//catch possible errors(overflow/underflow)
		String[] IR = this.instructions[mar.getValue()];
		//update table

		
		try{
			Thread.sleep(1000);
		} catch (Exception e) {}

	}

	public void start(){
		if(this.tInstance == null || !this.tInstance.isAlive())
			this.tInstance = new Thread(this);
			
		this.tInstance.start();
	}
	
	public static Fetch getInstance(){
		if(Fetch.instance == null)
			Fetch.instance = new Fetch();

		return Fetch.instance;
	}
	
	

}
