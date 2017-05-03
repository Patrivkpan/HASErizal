package util;

import process.Fetch;
import process.Decode;

public class Clock extends Thread{
    private static Clock instance;

    private Thread processes[] = {  
                            Fetch.getInstance(),
                            Decode.getInstance()/*,
                            Execute.getInstace(),
                            Memory.getInstance(),
                            WriteBack.getInstance()*/
        };
    private int cycle;

    private Clock(int cycle){
        this.cycle = cycle;
    }

    @Override
    public void run(){
        this.cycle++;
        for(Thread t : this.processes){
            try{t.join();}
            catch(Exception e){}
        }
    }

    public static Clock getInstance(){
        if(Clock.instance == null)
            Clock.instance = new Clock(0);

        return Clock.instance;
    }

    public int getCycle(){
        return this.cycle;
    }

}