package Lesson8;
import lejos.nxt.LCD;
/**
 * A platform for a behavior based system with a simple
 * suppression mechanism. An actual behavior is defined by extending 
 * the Behavior class. Each behavior is a single thread of control.
 * It has access to control a car by means of the methods of the class Car.
 * However, access can be suppressed so that the motor commands
 * will not be performed while the behavior is suppressed.
 * 
 * The class also provide a simple mechanism for reporting
 * the state of each behavior to the LCD. For that each
 * behavior has a name and a row number ( 1-7 ) so the state can be 
 * reported to the LCD on different rows.
 *  
 * @author  Ole Caprani
 * @version 23.08.08
 */
public class Behavior extends Thread 
{
    private String name;
    private int LCDrow;
    
    private int suppressCount;
    private Behavior subsumedBehavior;
	
    public Behavior(String name, int LCDrow, Behavior subsumedBehavior)
    {
        suppressCount = 0;
        this.setDaemon(true);
    	this.name = name;
    	this.LCDrow = LCDrow;
    	this.subsumedBehavior = subsumedBehavior;
    }
	
    // Suppress mechanism
    public boolean isSuppressed()
    {
        return suppressCount != 0;
    }

    public void suppressCountIncrement()
    {
        suppressCount++;
    }

    public void suppressCountDecrement()
    {
        suppressCount--;
    }
	
    public synchronized void suppress()
    {
        if ( subsumedBehavior != null )
        {
            subsumedBehavior.suppressCountIncrement();
            subsumedBehavior.suppress();
        }
    }
	
    public synchronized void release()
    {
        if ( subsumedBehavior != null )
        {
            subsumedBehavior.suppressCountDecrement();
            subsumedBehavior.release();
        }
    }

    // Access to motors
    public void forward(int leftPower, int rightPower)
    {
    	if ( ! isSuppressed() ) Car.forward(leftPower, rightPower); 
    }

    public void backward(int leftPower, int rightPower)
    {
    	if ( ! isSuppressed() ) Car.backward(leftPower, rightPower); 
    }
 
    public void stop()
    {
    	if ( ! isSuppressed() ) Car.stop(); 
    }

    // Access to LCD
    public void reportState()
    {
    	LCD.drawString(name, 0, LCDrow);
    	LCD.drawInt(isSuppressed()?1:0, name.length()+1, LCDrow);
    	LCD.refresh();  
    }

    public void drawInt(int i)
    {
        LCD.drawInt(i,3,name.length()+3, LCDrow);
        LCD.refresh();
    }

    public void drawString(String s)
    {
        LCD.drawString(s,name.length()+7, LCDrow);
        LCD.refresh();
    }
    
    public void delay(int time)
    {
        try 
        {
             Thread.sleep(time);
        }
        catch (Exception e)
        {
			
        }
    }
	
    // Override the run method to define the actual behavior
    public void run()
    {
		
    }
}
