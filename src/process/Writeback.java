package process;
import util.Register;

public class Writeback implements Runnable{
	private static Writeback instance;
	private Thread tInstance;
	private int value, pc, lines;
	private Register dest;
	private boolean done;
	
	private Writeback(){
		this.done = false;
		this.pc = -1;
	}
	
	@Override
	public void run(){
		if(this.dest == null) return;

		System.out.println("Writing " + pc);
		this.dest.setValue(this.value);
		this.dest.setBusy(false);
		this.dest = null;
		if(this.pc == lines-1) this.done = true;
	}	

	public void setFree(){
		this.dest.setBusy(false);
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
