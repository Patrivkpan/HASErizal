package util;
import java.util.HashMap;

public class Register{
    private static HashMap<String, Register> registers;

    private String name;
    private int value;
    private boolean isBusy;
    private String operand;

    private Register(String name, int value){
        this.name = name;
        this.value = value;
        this.isBusy = false;
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
        for(int i = 1; i < 33; i++){
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


    public boolean getBusy(){
        return this.isBusy;
    }

    public String getOperand(){
        return this.operand;
    }

    public void setValue(int value){		
        if(value > 99){
            Register.getRegister("OF").setValue(1);
            this.value = 99;
        } else if(value < - 99){
            Register.getRegister("OF").setValue(1);
            this.value = -99;
        } else{
            this.value = value;
        }
    }

    public void setOperand(String value){
        this.operand=value;
    }

}
