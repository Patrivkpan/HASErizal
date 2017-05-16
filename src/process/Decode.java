package process;
import util.Register;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Decode implements Runnable{
	
	private static Decode instance;
	private Thread tInstance;
	
	private String firstUseOfDestRegister, firstUseOfSrcRegister;
	private String instruction[]; // 0 operator, 1 and 2 are the operands
	
	private Register dest, src;
	private Execute execute;
	
	private boolean stalling;
	private Operation op;
	private int pc, srcVal, war, raw;

	private static final String FILE_TEXT = "FDEMW.txt";

	private Decode(){
		this.execute = Execute.getInstance();
		this.stalling = false;
	}

	@Override
	public void run(){
		this.stalling = false; 
		if(this.instruction == null) return;	

		this.dest = Register.getRegister(instruction[1]);
		this.src  = Register.getRegister(instruction[2]);


		if(this.stalling){
			System.out.println("Decode stall " + pc);	
			return;
		}

		System.out.println("Decoding " + pc);
		if (dest != null) {
			// System.out.println("A Dest: " + instruction[1] + " Src: " + instruction[2]);
			firstUseOfDestRegister=dest.getOperand();
			if(firstUseOfDestRegister=="src"){
				war++;
				System.out.println("WAR Hazard");
				try{
					BufferedWriter writer = new BufferedWriter(new FileWriter("FDEMW.txt", true));
					writer.append("Total of WAR Hazard: " + war+"\n\n");
					writer.close(); 
				}
				catch(IOException e) { 
			
				}
				catch(Exception e) { 
					System.out.println(e.getMessage()); 
				}
			}
		}
		if (src != null && src.getBusy()) {
			// System.out.println("B Dest: " + instruction[1] + " Src: " + instruction[2]);
			firstUseOfSrcRegister=src.getOperand();
			if(firstUseOfSrcRegister=="dest"){
				raw++;
				System.out.println("RAW Hazard");
				try{
					BufferedWriter writer = new BufferedWriter(new FileWriter("FDEMW.txt", true));
					writer.append("Toatl of RAW Hazard: "+ raw+"\n\n");
					writer.close(); 
				}
				catch(IOException e) { 
			
				}
				catch(Exception e) { 
					System.out.println(e.getMessage()); 
				}
			}
		}
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
			firstUseOfDestRegister=dest.getOperand();
			if(firstUseOfDestRegister=="src"){
				System.out.println("WAR Hazard");
				return true;
			}
		}
		if (src != null && src.getBusy()) {
			firstUseOfSrcRegister=src.getOperand();
			if(firstUseOfSrcRegister=="dest"){
				System.out.println("RAW Hazard ");
				return true;
			}
		}

		return false;
	}

	public void start(){
		if(this.tInstance == null || !this.tInstance.isAlive())
			this.tInstance = new Thread(this);

		if(this.dest != null && !this.isStalling()) {
			this.execute.setDestOperands(this.op, this.dest, this.dest.getValue(), 
											this.srcVal, this.pc);
			this.dest = null;
		}
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

	public boolean isStalling(){
		return this.stalling;
	}

	public void setInstruction(String instruction[], int pc){
		this.instruction = instruction;
		this.pc = pc;
	}
}
