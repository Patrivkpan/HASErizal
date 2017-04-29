package util;

public class NotAFlagException extends Exception{
    private String registerName;
	public InstructionSyntaxException(String registerName){
        this.registerName = registerName;
    }

	public String getMessage(){
		return "Register " + this.registerName + " is not a flag.";
	}
	
}

