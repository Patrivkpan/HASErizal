package process;
import util.Register;

public class Decode extends Thread{
	
	private static Decode instance;
	private String instruction[]; // 0 operator, 1 and 2 are the operands
	private Register dest,src;
	private String firstUseOfDestRegister, firstUseOfSrcRegister;

	private Decode(){}

	@Override
	public void run(){
		//do decoding stuff here
	

		// Hazard checking
		dest=Register.getRegister(instruction[1]);
		src=Register.getRegister(instruction[2]);
		firstUseOfOP2=dest.getOperand();

		if (dest.getBusy()) {
			firstUseOfDestRegister=dest.getOperand();
			if(firstUseOfDestRegister=="src"){
				System.out.println("WAR Hazard");
			}
		}
		if (src.getBusy()) {
			firstUseOfSrcRegister=src.getOperand();
			if(firstUseOfSrcRegister=="dest"){
				System.out.println("RAW Hazard");
			}
		}

		//do later=
	}

	public static Decode getInstance(){
		if(Decode.instance == null) Decode.instance = new Decode();

		return Decode.instance;
	}

	public void setInstruction(String instruction[]){
		this.instruction = instruction;
	}
}
