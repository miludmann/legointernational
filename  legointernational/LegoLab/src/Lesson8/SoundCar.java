package Lesson8;
import lejos.nxt.*;

public class SoundCar 
{
    public static void main(String [] args)
    {    	
    	RandomDrive rd;
    	AvoidFront af;
    	PlaySounds ps;
    	FollowLight flLeft, flRight;
    	
        LCD.drawString("Sound Car",0,0);
        LCD.refresh();
    	
    	rd = new RandomDrive("Drive",1, null);
    	af = new AvoidFront ("Avoid",2,rd);
    	ps = new PlaySounds ("Play ",3,af);
    	flLeft = new FollowLight("LightL", 4, ps, SensorPort.S2, MotorPort.B);
    	flRight = new FollowLight("LightR", 5, ps, SensorPort.S3, MotorPort.A);
    	

       	//rd.start();
       	//af.start();
    	//ps.start();
    	flRight.start();
    	flLeft.start();

    	while (! Button.ESCAPE.isPressed())
    	{
    		rd.reportState();
    		af.reportState();
    		ps.reportState();
    		flRight.reportState();
    		flLeft.reportState();
    		
    	}
    	
    	LCD.clear();
        LCD.drawString("Program stopped",0,0);
        LCD.refresh();

    }    
}
