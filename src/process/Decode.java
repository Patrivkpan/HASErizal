package process;

public class Decode extends Thread{
	private static instance;
	private String instruction[];

	private Decode(){}

	public void run(){
		//do decoding stuff here
	}

	public static Decode getInstance(){
		if(Decode.instance == null) Decode.instance = new Decode();

		return Decode.instance;
	}

	public void setInstruction(String instruction[]){
		this.instruction = instruction;;
	}
}
