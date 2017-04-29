package process;

public class Decode implements Runnable{
	private static instance;
	private Thread tInstance;
	private String instruction[];

	private Decode{}

	public void run(){
		//do decoding stuff here
		/*
			assign each line to an add/sub/smp/load 
		*/
	}

	public int start(String instruction[]){
		if (this.tInstance == null || !this.tInstance.isAlive()){
			this.tInstance = new Thread(this);
			this.instruction = instruction
			this.tInstance.start();
		}

	}

	public static Decode getInstance(){
		if(Decode.instance == null) Decode.instance = new Decode();

		return Decode.instance;
	}


}
