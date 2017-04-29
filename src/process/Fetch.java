package process;


public class Fetch implements Runnable{

	private static Fetch instance;
	private Thread tInstance;
	private String instructions[][];
	
	private Fetch(){}

	public void run(){
		//get pc

		System.out.println("Current instruction: " + this.instructions[pc][0]);
		System.out.println("Operands: " + this.instructions[pc][1] + " "
							+ this.instructions[pc][2]);

		//update table

		try{
			Thread.sleep(1000);
		} catch (Exception e) {}

	}

	/*
		Starts thread, returns -1 if there are no instructions, 0 if thread ran
		normally, 1 if thread is still running
	*/
	public int start(){
		if (this.instructions == null)
			return -1;

		if (this.tInstance == null || !this.tInstance.isAlive()){
			this.tInstance = new Thread(this);
			this.tInstance.start();
			return 0;			
		}

		return 1;
	}

	public static Fetch getInstance(){
		if(Fetch.instance == null)
			Fetch.instance = new Fetch();

		return Fetch.instance;
	}

	public static Fetch getInstance(String instructions[][]){
		if(Fetch.instance == null)
			Fetch.instance = new Fetch();

		Fetch.instance.setInstructions(instructions);
		return Fetch.instance;
	}

	public void setInstructions(String instructions[][]){
		this.instructions = instructions;
	}

}
