package process;


public class Execute implements Runnable{

	private static Execute instance;
	private Operation operation;
	private Register dest, src;

	
	private Execute(){}

	public void run(){
		double answer;

		switch(this.instruction){
			case ADD:
				answer = dest.getValue() + src.getValue();
				break;
			case SUB:
				answer = dest.getValue() - src.getValue();
				break;
			case LD:
				answer = src.getValue();
				break;
			case CMP:
				answer = dest.getValue() - src.getValue();
				if(answer == 0){
					Register.getRegister("ZF").setValue(1);
				} 
				if(answer < 0){
					Register.getRegister("NF").setValue(1);
				}
				break;
			default:
				System.out.println("Invalid instruction.");
				System.exit(0);	
		}
		System.out.println("Current instruction: " + this.instructions[pc][0]);
		System.out.println("Operands: " + this.instructions[pc][1] + " "
							+ this.instructions[pc][2]);
		
	}

	public boolean start(String operation, Register dest, Register src)	{		
		(new Thread(this)).start();
		return true;
	}

	public static Execute getInstance(){
		if(instance == null)
			instance = new Execute();

		return instance;
	}

	public static Execute getInstance(String instructions[][]){
		if(instance == null)
			instance = new Execute();

		instance.setInstructions(instructions);
		return instance;
	}

	public void setInstructions(String instructions[][]){
		this.instructions = instructions;
	}
}
