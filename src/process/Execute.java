package process;
import util.Clock;
import util.Register;

import java.util.ArrayDeque;

public class Execute implements Runnable{
	private static Execute instance;
	private Thread tInstance;
	private Memory memory;
		
	private Operation operation;
	private int op1, op2, pc, ans;
	private Register dest;

	private ArrayDeque<Operation> opQueue;
	private ArrayDeque<Integer[]> intQueue;
	private ArrayDeque<Register> destQueue;

	private boolean ready;
	
	private Execute(){
		this.opQueue = new ArrayDeque<Operation>();
		this.intQueue = new ArrayDeque<Integer[]>();
		this.destQueue = new ArrayDeque<Register>();
		this.operation = Operation.NULL;
		this.memory = Memory.getInstance();
		this.ready = false;
	}

	public void run(){
		this.ans = 0;

		if(this.opQueue.peek() == Operation.NULL || this.opQueue.peek() == null) 
			return;
		this.operation = this.opQueue.poll();
		this.dest = this.destQueue.poll();

		Clock.getInstance().addStalls(destQueue.size());

		Integer operands[] = this.intQueue.poll();
		this.op1 = operands[0];
		this.op2 = operands[1];
		this.pc = operands[2];

		System.out.println("Executing " + pc);
		switch(this.operation){
			case NULL:
				break;
			case ADD:
				this.ans = op1 + op2;
				break;
			case SUB:
				this.ans = op1 - op2;
				break;
			case LD:
				this.ans = op2;
				break;
			case CMP:
				this.ans = op1 - op2;
				if(this.ans == 0){
					Register.getRegister("ZF").setValue(1);
				} 
				if(this.ans < 0){
					Register.getRegister("NF").setValue(1);
				}
				break;
			default:
				System.out.println("Invalid instruction.");
		}
		this.ready = true;
		this.operation = Operation.NULL;
	}

	public void start(){		
		if(this.tInstance == null || !this.tInstance.isAlive())
			this.tInstance = new Thread(this);
		
		this.tInstance.start();
	}

	public void setDestOperands(Operation operation, Register dest, 
								int op1, int op2, int pc){
		Integer operands[] = {op1, op2, pc};

		this.intQueue.add(operands);
		this.opQueue.add(operation);
		this.destQueue.add(dest);
    }

	public void setNext(){
		if(this.ready){
			this.memory.setDestValue(this.dest, this.ans, this.pc);
			this.ready = false;
		}
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
