package process;
import util.Register;

public class Memory implements Runnable{
	private static Memory instance;
	private Thread tInstance;
	private int answer, pc;
	private Register dest;
	private Writeback writeback;
	
	private Memory(){
		this.writeback = Writeback.getInstance();
	}
	
	@Override
	public void run(){
		try{
			Thread.sleep(1000);
		} catch (Exception e) {}

		this.writeback.setDestValue(dest, answer, pc);
	}

	public void start(){
		if(this.tInstance == null || !this.tInstance.isAlive())
			this.tInstance = new Thread(this);

		this.tInstance.start();
	}

	public Thread getThreadInstance(){
		return this.tInstance;
	}

	public void setDestValue(Register dest, int answer, int pc){
		this.dest = dest;
		this.answer = answer;
		this.pc = pc;
	}

	public static Memory getInstance(){
		if(Memory.instance == null)
			Memory.instance = new Memory();

		return Memory.instance;
	}

}
