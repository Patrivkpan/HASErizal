package process;
import util.Register;


public class Decode implements Runnable{
	
	private static Decode instance;
	private Thread tInstance;
	private String instruction[]; // 0 operator, 1 and 2 are the operands
	private Register dest,src;
	private String firstUseOfDestRegister, firstUseOfSrcRegister;

	private Decode(){}

	@Override
	public void run(){
		//do decoding stuff here
		if(this.instruction == null) return;	

		System.out.println(this.instruction[0]);
		// Hazard checking
		dest=Register.getRegister(instruction[1]);
		src=Register.getRegister(instruction[2]);

		if (dest.getBusy()) {
			firstUseOfDestRegister=dest.getOperand();
			if(firstUseOfDestRegister=="src"){
				System.out.println("WAR Hazard");
			}
		}
		if (src != null && src.getBusy()) {
			firstUseOfSrcRegister=src.getOperand();
			if(firstUseOfSrcRegister=="dest"){
				System.out.println("RAW Hazard");
			}
		}

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

	public void setInstruction(String instruction[]){
		this.instruction = instruction;
	}
}
