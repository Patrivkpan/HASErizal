package process;


public class Fetch implements Runnable{

	private static Fetch instance;
	private String instructions[][];
	
	private Fetch(){}

	public void run(){
		//get pc value
		int pc = 0;

		System.out.println("Current instruction: " + this.instructions[pc][0]);
		System.out.println("Operands: " + this.instructions[pc][1] + " "
							+ this.instructions[pc][2]);
		
	}

	public boolean start(){
		if(instructions == null)
			return false;

		(new Thread(this)).start();
		return true;
	}

	public static Fetch getInstance(){
		if(instance == null)
			instance = new Fetch();

		return instance;
	}

	public static Fetch getInstance(String instructions[][]){
		if(instance == null)
			instance = new Fetch();

		instance.setInstructions(instructions);
		return instance;
	}

	public void setInstructions(String instructions[][]){
		this.instructions = instructions;
	}

}
