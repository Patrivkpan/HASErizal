package util;

public class InstructionMemory{
    private static InstructionMemory instance;
    private String instructions[][];
    private int lines;

    private InstructionMemory(){}

    public static InstructionMemory getInstance(){
        if(InstructionMemory.instance == null)
            InstructionMemory.instance = new InstructionMemory();
        return InstructionMemory.instance;
    }

    public void setInstructions(String instructions[][]){
        this.instructions = instructions;
        this.lines = instructions.length;
    }

    public String[] getInstruction(int pc){
        if(pc < lines)
            return this.instructions[pc];
        
        return null;
    }

    public int getLines(){
        return this.lines;
    }
}
