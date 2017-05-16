package process;
import util.Register;
import java.util.ArrayDeque;


public class Decode implements Runnable{
	
	private static Decode instance;
	private Thread tInstance;
	
	private String instruction[]; // 0 operator, 1 and 2 are the operands

	private ArrayDeque<String[]> instructionQueue;
	private ArrayDeque<Integer> pcQueue;
	
	private Register dest, src;
	private Execute execute;
	
	private boolean stalling;
	private Operation op;
	private int pc, srcVal;


	private Decode(){
		this.instructionQueue = new ArrayDeque<String[]>();
		this.pcQueue = new ArrayDeque<Integer>();
		this.execute = Execute.getInstance();
		this.stalling = false;
		this.op = Operation.NULL;
	}

	@Override
	public void run(){
		this.stalling = false; 
		if(this.instruction == null) return;

		this.dest = 	Register.getRegister(instruction[1]);
		this.src  =  	Register.getRegister(instruction[2]);

		
		if(this.stalling = this.checkHazard(dest, src)){
			System.out.println("Decode stall " + pc);	
			return;
		}
		

		System.out.println("Decoding " + pc);
		this.dest.setOperand("dest");
		this.dest.setBusy(true);
		
		if(this.src != null){
			this.src.setOperand("src");
			this.src.setBusy(true);
		}

		switch(instruction[0]){
			case "ADD":
				this.op = Operation.ADD; 
				break;
			case "SUB":
				this.op = Operation.SUB; 
				break;
			case "LOAD":
				this.op = Operation.LD; 
				break;
			case "CMP":
				this.op = Operation.CMP; 
				break;
			default:
				this.op = Operation.NULL;
		}
		
		if(src == null)
			this.srcVal = Integer.parseInt(instruction[2]);
		else{
			this.srcVal = src.getValue();
			this.src.setBusy(false);
		}
		this.instruction = null;
	}

	public boolean checkHazard(Register dest, Register src){
		// Hazard checking

		if (dest.getBusy()) {
			if(dest.getOperand() == "src")
				System.out.println("WAR Hazard");
			else
				System.out.println("WAW Hazard");
			return true;
		}

		if (src != null && src.getBusy() && src.getOperand()=="dest") {
			System.out.println("RAW Hazard ");
			return true;
		}

		return false;
	}

	public void start(){
		if(this.tInstance == null || !this.tInstance.isAlive())
			this.tInstance = new Thread(this);

		if(this.dest == null) {
			this.instruction = this.instructionQueue.poll();
			if(this.instruction != null) this.pc = this.pcQueue.poll();
		}

		this.tInstance.start();
	}

	public void setNext(){
		if(!this.isStalling() && this.dest != null ){
			this.execute.setDestOperands(this.op, this.dest, this.dest.getValue(), 
											this.srcVal, this.pc);
			this.dest = null;
			this.op = Operation.NULL;
		} 
	}

	public static Decode getInstance(){
		if(Decode.instance == null) 
			Decode.instance = new Decode();

		return Decode.instance;
	}

	public Thread getThreadInstance(){
		return this.tInstance;
	}

	public boolean isStalling(){
		return this.stalling;
	}

	public void setInstruction(String instruction[], int pc){
		if(instruction == null) return;
		this.instructionQueue.add(instruction);
		this.pcQueue.add(pc);
	}
}
