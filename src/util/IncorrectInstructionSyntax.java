package util;

public class IncorrectInstructionSyntax extends Exception{
	private int lineNumber;

	public IncorrectInstructionSyntax(int lineNumber){
		this.lineNumber = lineNumber;
	}

	public int getLineNumber(){
		return this.lineNumber;
	}
	
}

