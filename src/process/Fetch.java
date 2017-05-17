package process;
import util.Clock;
import util.Register;
import util.InstructionMemory;
import util.FDEMW_Table;

public class Fetch implements Runnable{

	private static Fetch instance;
	private Thread tInstance;
	private InstructionMemory memory;
	private Decode decode;
	private Register pc, mar;
	private String instruction[];
	private boolean end;
	private int numInst=0,clock;
	
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
		clock= Clock.getInstance().getCycle();
		FDEMW_Table.getInstance().getTable().get(mar.getValue()).set(clock+2,"F");
	}

	public void start(){
		if(this.end){
			return;
		}
		if(this.tInstance == null || !this.tInstance.isAlive())
			this.tInstance = new Thread(this);

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
