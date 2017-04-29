import java.util.Hashmap;

public class Register(){
    private static Hashmap<String, Register> registers;

    private String name;
    private int value;
    private Register(String name, int value){
        this.name = name;
        this.value = value;
    }

    /* Gets a specific register, returns null if register with that name doesn't exist' */
    private static Register getRegister(String name){
        if(Registers.registers == null)
            Registers.registerInit();

        return Registers.registers.get(name);
    }

    /* Initializes registers */
    private static void registerInit(){
        Register.registers = new Hashmap<String, Register>();
        /* Special registers */

        Register.registers.add("PC", new Register("PC", 0));
        Register.registers.add("OF", new Register("OF", 0));
        Register.registers.add("NF", new Register("NF", 0));
        Register.registers.add("ZF", new Register("ZF", 0));
        Register.registers.add("MAR", new Register("MAR", 0));
        Register.registers.add("MBR", new Register("MBR", 0));

        /* Ordinary registers */
        String name;
        for(int i = 1; i != 33; i++){
            name = "R" + i;
            Register.registers.add(name, new Register(name, 0));
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
        this.value = value;
    }

    public void switchFlag() throws NotAFlagException{
        if(this.name != "OF" || this.name != "NF" || this.name != "ZF")
            throw new NotAFlagException(this.name);

        this.value = (this.value == 1)? 0 : 1;
    }

}