package util;

import process.Fetch;
import process.Decode;

public class Clock extends Thread{
    private static Clock instance;

    private Thread processes[];
    private int cycle;

    private Clock(int cycle){
        this.cycle = cycle;
        this.processes = {  Fetch.getInstance(),
                            Decode.getInstance()
        };
    }

    @Override
    public void run(){
        this.cycle++;
        for(Thread t : this.processes){
            t.join();
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