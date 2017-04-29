package util;

public class NotAFlagException extends Exception{
    private String registerName;
	public NotAFlagException(String registerName){
        this.registerName = registerName;
    }

	public String getMessage(){
		return "Register " + this.registerName + " is not a flag.";
	}
	
}

