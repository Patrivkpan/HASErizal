package process;
import util.Register;

public class Execute implements Runnable{
	private static Execute instance;
	private Thread tInstance;
	private Operation operation;
	private int op1, op2;
	private Register dest;
	private Memory memory;
	
	private Execute(){
		this.operation = Operation.NULL;
		this.memory = Memory.getInstance();
	}

	public void run(){
		double answer = 0d;

		switch(this.operation){
			case NULL:
				break;
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
		}
		int ans = (int)answer;
		this.memory.setDestValue(dest, ans);
	}

	public void start(){		
		if(this.tInstance == null || !this.tInstance.isAlive())
			this.tInstance = new Thread(this);

		this.tInstance.start();
	}

	public void setDestOperands(Register dest, int op1, int op2){
		this.dest = dest;
		this.op1 = op1;
		this.op2 = op2;
    }

	public Thread getThreadInstance(){
		return this.tInstance;
	}

	public static Execute getInstance(){
		if(Execute.instance == null)
			Execute.instance = new Execute();

		return Execute.instance;
	}
}
