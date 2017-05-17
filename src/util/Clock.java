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
    private int stalls;

    private Clock(int cycle){
        this.stalls = 0;
        this.cycle = cycle;
        this.processes = new Thread[5];
        
        this.f = Fetch.getInstance();
        this.d = Decode.getInstance();
        this.e = Execute.getInstance();
        this.m = Memory.getInstance();
        this.w = Writeback.getInstance();
    }

    @Override
    public void run(){
        for(Thread t : this.processes){
            try{t.join();}
            catch(Exception e){e.printStackTrace();}
        }
        this.cycle++;
    }

	public void start(){
		if(this.tInstance == null || !this.tInstance.isAlive())
			this.tInstance = new Thread(this);
        
        this.processes[0] = this.f.getThreadInstance();
        this.processes[1] = this.d.getThreadInstance();
        this.processes[2] = this.e.getThreadInstance();
        this.processes[3] = this.m.getThreadInstance();
        this.processes[4] = this.w.getThreadInstance();
        
        this.tInstance.start();
	}

    public static Clock getInstance(){
        if(Clock.instance == null)
            Clock.instance = new Clock(1);

        return Clock.instance;
    }

	public Thread getThreadInstance(){
		return this.tInstance;
	}

    public void addStalls(int stalls){
        this.stalls += stalls;
    }

    public int getCycle(){
        return this.cycle;
    }

    public int getStalls(){
        return this.stalls;
    }

}
