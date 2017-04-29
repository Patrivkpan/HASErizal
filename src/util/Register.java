package util;
import java.util.HashMap;

public class Register{
    private static HashMap<String, Register> registers;

    private String name;
    private int value;
    private Register(String name, int value){
        this.name = name;
        this.value = value;
    }

    /* Gets a specific register, returns null if register with that name doesn't exist' */
    public static Register getRegister(String name){
        if(Register.registers == null)
            Register.registerInit();

        return Register.registers.get(name);
    }

    /* Initializes registers */
    private static void registerInit(){
        Register.registers = new HashMap<String, Register>();
        /* Special registers */

        Register.registers.put("PC", new Register("PC", 0));
        Register.registers.put("OF", new Register("OF", 0));
        Register.registers.put("NF", new Register("NF", 0));
        Register.registers.put("ZF", new Register("ZF", 0));
        Register.registers.put("MAR", new Register("MAR", 0));
        Register.registers.put("MBR", new Register("MBR", 0));

        /* Ordinary registers */
        String name;
        for(int i = 1; i != 33; i++){
            name = "R" + i;
            Register.registers.put(name, new Register(name, 0));
        }

    }

    /* GETTERS AND SETTERS */
    public String getName(){
        return this.name;
    }

    public int getValue(){
        return this.value;
    }

    public void setValue(int value){
        if(this.name == "OF" || this.name == "NF" || this.name == "ZF")
            this.value = (this.value > 0)? 1: 0;
        else
            this.value = value;
    }

    /* throws exception if register is not a flag */
    public void switchFlag() throws NotAFlagException{
        if(this.name != "OF" || this.name != "NF" || this.name != "ZF")
            throw new NotAFlagException(this.name);

        this.value = (this.value == 1)? 0 : 1;
    }

}