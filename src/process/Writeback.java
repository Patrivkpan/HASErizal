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
		if(this.dest == null) return;
		this.dest.setValue(this.value);
		this.dest = null;
	}

	public void start(){
		if(this.tInstance == null || !this.tInstance.isAlive())
			this.tInstance = new Thread(this);

		this.tInstance.start();
	}

	public Thread getThreadInstance(){
		return this.tInstance;
	}

	public int getPc(){
		return this.pc;
	}

	/* Sets destination and value*/
	public void setDestValue(Register dest, int value, int pc){
		this.dest = dest;
		this.value = value;
		this.pc = pc;
	}

	public static Writeback getInstance(){
		if(Writeback.instance == null)
			Writeback.instance = new Writeback();

		return Writeback.instance;
	}
}
