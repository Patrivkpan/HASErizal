package util;

import process.Fetch;
import process.Decode;
import process.Execute;
import process.Memory;
import process.Writeback;

public class Clock implements Runnable{
    private static Clock instance;
    private Thread tInstance;

    private Thread processes[];
    private Fetch f;
    private Decode d;
    private Execute e;
    private Memory m;
    private Writeback w;
    private int cycle;

    private Clock(int cycle){
        this.cycle = cycle;
        this.processes = new Thread[5];
    }

    @Override
    public void run(){
        this.cycle++;
        for(Thread t : this.processes){
            try{t.join();}
            catch(Exception e){e.printStackTrace();}
        }
    }

	public void start(){
		if(this.tInstance == null || !this.tInstance.isAlive())
			this.tInstance = new Thread(this);
			
            this.processes[0] = Fetch.getInstance().getThreadInstance();
            this.processes[1] = Decode.getInstance().getThreadInstance();
            this.processes[2] = Execute.getInstance().getThreadInstance();
            this.processes[3] = Memory.getInstance().getThreadInstance();
            this.processes[4] = Writeback.getInstance().getThreadInstance();
        
        this.tInstance.start();
	}

    public static Clock getInstance(){
        if(Clock.instance == null)
            Clock.instance = new Clock(0);

        return Clock.instance;
    }

	public Thread getThreadInstance(){
		return this.tInstance;
	}

    public int getCycle(){
        return this.cycle;
    }

}
