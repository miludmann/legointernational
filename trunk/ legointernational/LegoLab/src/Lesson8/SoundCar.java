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
    	flRight = new FollowLight("LightR", 5, rd, SensorPort.S3, MotorPort.C);
    	flLeft = new FollowLight("LightL", 4, flRight, SensorPort.S2, MotorPort.B);
    	ps = new PlaySounds ("Play ",3,flLeft);
    	af = new AvoidFront ("Avoid",2,ps);

    	

       	rd.start();
       	af.start();
    	ps.start();
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
