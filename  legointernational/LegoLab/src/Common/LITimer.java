package Common;

import Common.LITimer;
import lejos.util.TimerListener;

public class LITimer
{
    private TimerListener myListener;
    private Thread        myThread  ;
    private int           delay     ;
    private boolean       running   ;
    
    protected long delayStartTimeInMillis;

    /**
     * Create a Timer object. Every theDelay milliseconds
     * the el.timedOut() function is called. You may
     * change the delay with setDelay(int). You need
     * to call start() explicitly.
     */
    public LITimer(int theDelay, TimerListener el) 
    {
		running    = false;
		delay      = theDelay;
		myListener = el;
	
		myThread   = new Thread() {
		    public void run() {
			
		    while(true) {
			   if (running)
			   {
	               while(running && System.currentTimeMillis()-delayStartTimeInMillis < delay)
	               {}
	               
	               if(running) //Verify if we were still running, or a stop killed it
	            	   myListener.timedOut();
	               
			   } else {
			       yield();
			   }
			}
	    }
	};
	
	myThread.setDaemon(true);
    }

    /**
     * access how man milliseconds between timedOut() messages.
     */
    public synchronized int getDelay() {
    	return delay;
    }
    /**
     * Change the delay between timedOut messages. Safe to call
     * while start()ed. Time in milli-seconds.
     */
    public synchronized void setDelay(int newDelay) {
    	delay = newDelay;
    }

    /**
     * Stops the timer. timedOut() messages are not sent.
     */
    public synchronized void stop() {
    	running = false;
    }

    /**
     * Starts the timer, telling it to send timeOut() methods
     * to the TimerListener.
     * Starting the timer again will reset the timer
     */
    public synchronized void start() {
    	running = true;
    	delayStartTimeInMillis = System.currentTimeMillis();
    	
    	if (!myThread.isAlive())
    		myThread.start();
    }
}
