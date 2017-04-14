package util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses instructions. Singleton design implementation.
 */
public class InstructionParser {
	/* ATTRIBUTES */
	private static final String REGEX = "(LOAD|ADD|SUB|CMP) (R\\d+),\\s*((R\\d+)|\\d+)\\s*";
	private static InstructionParser instance;
	private String[] result;

	/* CONSTRUCTOR*/
	private InstructionParser(){}

	
	/* METHODS */
	public String[] parseString(String s){
		this.result = new String[3];
		Pattern pattern = Pattern.compile(InstructionParser.REGEX);
		Matcher matcher = pattern.matcher(s);

		if(matcher.find())
			for(int i = 0; i < 3; i++)
				this.result[i] = matcher.group(i+1);
		else
			this.result = null;

		return this.result;
	}

	/* GETTERS AND SETTERS */
	public static InstructionParser getInstance(){
		if(instance == null) 
			InstructionParser.instance = new InstructionParser();
		return InstructionParser.instance;
	}

	public String[] getResult(){
		return this.result;
	}
	
}
