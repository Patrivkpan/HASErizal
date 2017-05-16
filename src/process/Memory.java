package process;
import util.Register;
import java.util.ArrayDeque;

public class Memory implements Runnable{
	private static Memory instance;
	private Thread tInstance;

	private int answer, pc;
	private Register dest, sDest;

	private ArrayDeque<Integer[]> intQueue;
	private ArrayDeque<Register> destQueue;
	
	private Writeback writeback;
	private boolean ready;

	private Memory(){
		this.intQueue = new ArrayDeque<Integer[]>();
		this.destQueue = new ArrayDeque<Register>();
		this.writeback = Writeback.getInstance();
	}
	
	@Override
	public void run(){
		if(this.destQueue.peek() == null) return;

		Integer ops[] = this.intQueue.poll();
		this.answer = ops[0];
		this.pc = ops[1];

		this.dest = this.destQueue.poll();

		System.out.println("Memory-ing " + pc + " " + answer);

		this.sDest = this.dest;
		this.dest = null;
	}

	public void start(){
		if(this.tInstance == null || !this.tInstance.isAlive())
			this.tInstance = new Thread(this);
		
		this.tInstance.start();
	}

	public void setNext(){
		if(this.sDest != null){
			this.writeback.setDestValue(this.sDest, this.answer, this.pc);
			this.sDest = null;
		}
	}

	public Thread getThreadInstance(){
		return this.tInstance;
	}

	public void setDestValue(Register dest, int answer, int pc){
		Integer ops[] = {answer, pc};

		this.intQueue.add(ops);
		this.destQueue.add(dest);
	}

	public static Memory getInstance(){
		if(Memory.instance == null)
			Memory.instance = new Memory();

		return Memory.instance;
	}

}
