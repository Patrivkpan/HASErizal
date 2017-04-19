package util;

public class InstructionSyntaxException extends Exception{
	private int lineNumber;

	public InstructionSyntaxException(int lineNumber){
		this.lineNumber = lineNumber;
	}

	public int getLineNumber(){
		return this.lineNumber;
	}

	public String getMessage(){
		return "Syntax error at line " + lineNumber;
	}
	
}

