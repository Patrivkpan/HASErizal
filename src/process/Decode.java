package process;

public class Decode implements Runnable{
	private static Decode instance;
	private Thread tInstance;
	private String instruction[];

	private Decode(){}

	@Override
	public void run(){
		//do decoding stuff here
		
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
