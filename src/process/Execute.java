package process;


public class Execute implements Runnable{

	private static Execute instance;
	private Operation operation;
	private Register dest;
	private int op1, op2;

	
	private Execute(){}

	public void run(){
		double answer;

		switch(this.operation){
			case ADD:
				answer = op1 + op2;
				break;
			case SUB:
				answer = op1 - op2;
				break;
			case LD:
				answer = op1;
				break;
			case CMP:
				answer = op1 - op2;
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
		
	}

	public boolean start(){		
		(new Thread(this)).start();
		return true;
	}

	public static Execute getInstance(){
		if(instance == null)
			instance = new Execute();

		return instance;
	}

	public static Execute getInstance(){
		if(instance == null)
			instance = new Execute();

		return instance;
	}

	public void setOperands(int op1, int op2){
		this.op1 = op1;
		this.op2 = op2;
    }

}
