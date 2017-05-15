package process;
import util.Register;

public class Memory implements Runnable{
	private static Memory instance;
	private Thread tInstance;
	private int answer, pc;
	private Register dest, sDest;
	private Writeback writeback;
	private boolean ready;

	private Memory(){
		this.writeback = Writeback.getInstance();
	}
	
	@Override
	public void run(){
		if(this.dest == null) return;
		System.out.println("Memory-ing " + pc);
		this.writeback.setDestValue(dest, answer, pc);

		this.sDest = this.dest;
		this.dest = null;
	}

	public void start(){
		if(this.tInstance == null || !this.tInstance.isAlive())
			this.tInstance = new Thread(this);
		
		if(this.ready){
			this.writeback.setDestValue(this.sDest, this.answer, this.pc);
			this.ready = false;
		}
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
