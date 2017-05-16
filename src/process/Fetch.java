package process;
import util.Register;
import util.InstructionMemory;

public class Fetch implements Runnable{

	private static Fetch instance;
	private Thread tInstance;
	private InstructionMemory memory;
	private Decode decode;
	private Register pc, mar;
	private String instruction[];
	private boolean end;
	
	private Fetch(){
		this.memory = InstructionMemory.getInstance();
		this.decode = Decode.getInstance();

		this.pc = Register.getRegister("PC");
		this.mar = Register.getRegister("MAR");
		this.end = false;
	}

	@Override
	public void run(){
		this.mar.setValue(pc.getValue());
		this.pc.setValue(pc.getValue() + 1);

		this.instruction = this.memory.getInstruction(mar.getValue());
		if(this.instruction == null){
			this.end = true;
			return;
		}
		System.out.println("Fetching " + mar.getValue());
	}

	public void start(){
		if(this.tInstance == null || !this.tInstance.isAlive())
			this.tInstance = new Thread(this);

		if(this.decode.isStalling() || this.end)
			return;

		this.tInstance.start();
	}

	public void setNext(){
		if(this.instruction!= null){
			this.decode.setInstruction(this.instruction, mar.getValue());
			this.instruction = null;
		}
	}

	public Thread getThreadInstance(){
		return this.tInstance;
	}
	
	public static Fetch getInstance(){
		if(Fetch.instance == null)
			Fetch.instance = new Fetch();

		return Fetch.instance;
	}

}
