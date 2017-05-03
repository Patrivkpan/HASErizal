package process;
import util.Register;
import util.InstructionMemory;

public class Fetch implements Runnable{

	private static Fetch instance;
	private Thread tInstance;
	private InstructionMemory memory;
	private Decode decode;
	private Register pc, mar;
	
	private Fetch(){
		this.memory = InstructionMemory.getInstance();
		this.decode = Decode.getInstance();

		this.pc = Register.getRegister("PC");
		this.mar = Register.getRegister("MAR");
	}

	@Override
	public void run(){
		String instruction[];

		// if(decode.isBusy()) return;
		this.mar.setValue(pc.getValue());
		this.pc.setValue(pc.getValue() + 1);

		//catch possible errors(overflow/underflow)
		instruction = this.memory.getInstruction(mar.getValue());
		//update table
		this.decode.setInstruction(instruction);
		
	}

	public void start(){
		if(this.tInstance == null || !this.tInstance.isAlive())
			this.tInstance = new Thread(this);
			
		this.tInstance.start();
	}

	public Thread getThreadInstance(){
		return this.tInstance;
	}

	public int getPC(){
		return this.pc.getValue();
	}
	
	public static Fetch getInstance(){
		if(Fetch.instance == null)
			Fetch.instance = new Fetch();

		return Fetch.instance;
	}
	
}
