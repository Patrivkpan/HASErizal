package process;
import util.Register;


public class Decode implements Runnable{
	
	private static Decode instance;
	private Thread tInstance;
	private String instruction[]; // 0 operator, 1 and 2 are the operands
	private Register dest, src;
	private Execute execute;
	private String firstUseOfDestRegister, firstUseOfSrcRegister;
	private int pc;

	private Decode(){
		this.execute = Execute.getInstance();
	}

	@Override
	public void run(){
		//do decoding stuff here
		if(this.instruction == null) return;	

		// Hazard checking
		dest = 	Register.getRegister(instruction[1]);
		src  =  Register.getRegister(instruction[2]);

		if (dest.getBusy()) {
			// System.out.println("A Dest: " + instruction[1] + " Src: " + instruction[2]);
			firstUseOfDestRegister=dest.getOperand();
			if(firstUseOfDestRegister=="src"){
				System.out.println("WAR Hazard");
			}
		}
		if (src != null && src.getBusy()) {
			// System.out.println("B Dest: " + instruction[1] + " Src: " + instruction[2]);
			firstUseOfSrcRegister=src.getOperand();
			if(firstUseOfSrcRegister=="dest"){
				System.out.println("RAW Hazard");
			}
		}
		dest.setOperand("dest");
		dest.setBusy(true);
		
		if(src != null){
			src.setOperand("src");
			src.setBusy(true);
		}

		//do stall here
		Operation op;
		switch(instruction[0]){
			case "ADD":
				op = Operation.ADD; 
				break;
			case "SUB":
				op = Operation.SUB; 
				break;
			case "LOAD":
				op = Operation.LD; 
				break;
			case "CMP":
				op = Operation.CMP; 
				break;
			default:
				op = Operation.NULL;
		}
		int srcVal;
		if(src == null )
			srcVal = Integer.parseInt(instruction[2]);
		else
			srcVal = src.getValue();

		this.execute.setDestOperands(op, dest, dest.getValue(), 
			srcVal, this.pc);

		this.instruction = null;
	}

	public void start(){
		if(this.tInstance == null || !this.tInstance.isAlive())
			this.tInstance = new Thread(this);

		this.tInstance.start();
	}

	public static Decode getInstance(){
		if(Decode.instance == null) 
			Decode.instance = new Decode();

		return Decode.instance;
	}

	public Thread getThreadInstance(){
		return this.tInstance;
	}

	public void setInstruction(String instruction[], int pc){
		this.instruction = instruction;
		this.pc = pc;
	}
}
