package Lesson8;
import lejos.nxt.*;

public class SoundCar 
{
    public static void main(String [] args)
    {    	
    	RandomDrive rd;
    	AvoidFront af;
    	PlaySounds ps;
    	FollowLight fl;
    	
        LCD.drawString("Sound Car",0,0);
        LCD.refresh();
    	
    	rd = new RandomDrive("Drive",1, null);
    	fl = new FollowLight ("Light",4,rd);
    	ps = new PlaySounds ("Play ",2,fl);
    	af = new AvoidFront ("Avoid",3,ps);

    	

       	rd.start();
    	fl.start();
       	af.start();
    	ps.start();
    	


    	while (! Button.ESCAPE.isPressed())
    	{
    		rd.reportState();
    		af.reportState();
    		ps.reportState();
    		fl.reportState();
    	}
    	
    	LCD.clear();
        LCD.drawString("Program stopped",0,0);
        LCD.refresh();

    }    
}
