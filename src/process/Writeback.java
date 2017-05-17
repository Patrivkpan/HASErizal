package process;

import util.Clock;
import util.Register;
import util.FDEMW_Table;
import java.util.ArrayDeque;

public class Writeback implements Runnable{
	private static Writeback instance;
	private Thread tInstance;

	private int value, pc, lines, clock, numInst=0;
	private Register dest, of;

	private ArrayDeque<Integer[]> intQueue;
	private ArrayDeque<Register> destQueue;

	private boolean done;
	
	private Writeback(){
		this.done = false;
		this.intQueue = new ArrayDeque<Integer[]>();
		this.destQueue = new ArrayDeque<Register>();
		this.of = Register.getRegister("OF");
		this.pc = -1;
	}
	
	@Override
	public void run(){
		if(this.destQueue.peek() == null) return;
		this.clock = Clock.getInstance().getCycle();
		Integer ops[] = this.intQueue.poll();
		this.dest = this.destQueue.poll();
		this.value = ops[0];
		this.pc = ops[1];

		if(destQueue.size() > 0) Clock.getInstance().addStalls(1);
		System.out.println("Writing " + pc + " " + dest.getName());
		this.dest.setValue(this.value);

		this.of.setValue(0);

		FDEMW_Table.getInstance().getTable().get(numInst).set(clock+2,"W");
		this.numInst++;
		if(this.pc == lines-1) this.done = true;
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

	public boolean isDone(){
		return this.done;
	}

	public void setLines(int lines){
		this.lines = lines;
	}

	/* Sets destination and value*/
	public void setDestValue(Register dest, int value, int pc){
		Integer ops[] = {value, pc};

		this.intQueue.add(ops);
		this.destQueue.add(dest);
		
	}

	public void setFree(){
		if(this.dest != null)
			this.dest.setBusy(false);
	}

	public static Writeback getInstance(){
		if(Writeback.instance == null)
			Writeback.instance = new Writeback();

		return Writeback.instance;
	}
}
