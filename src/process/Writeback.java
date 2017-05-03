package process;
import util.Register;

public class Writeback implements Runnable{
	private static Writeback instance;
	private Thread tInstance;
	private int value, pc;
	private Register dest;
	
	private Writeback(){}
	
	@Override
	public void run(){
		this.dest.setValue(this.value);
	}

	public void start(){
		if(this.tInstance == null || !this.tInstance.isAlive())
			this.tInstance = new Thread(this);

		this.tInstance.start();
	}

	public Thread getThreadInstance(){
		return this.tInstance;
	}

	/* Sets destination and value*/
	public void setDestValue(Register dest, int value){
		this.dest = dest;
		this.value = value;
	}

	public static Writeback getInstance(){
		if(Writeback.instance == null)
			Writeback.instance = new Writeback();

		return Writeback.instance;
	}
}
