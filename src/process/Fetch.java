package process;


public class Fetch implements Runnable{

	private static Fetch instance;
	private String[][];
	
	private Fetch(){}

	public void run(){
		//get pc valur
		
		
	}

	public static Fetch getInstance(){
		if(instance == null)
			instance = new Fetch();

		return instance;
	}

}
