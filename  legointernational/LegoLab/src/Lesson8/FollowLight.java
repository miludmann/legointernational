package Lesson8;
import java.util.Queue;

import lejos.nxt.*;
import lejos.nxt.addon.RCXLightSensor;
/**
 * A behavior that uses an ultrasonic sensor on port S1
 * to make a car avoid objects in front of the car. 
 *  
 * @author  Ole Caprani
 * @version 23.08.08
 */
public class FollowLight extends Behavior 
{    
	public RCXLightSensor ls;
	public int min, max;
	public int light, norm;
    public MotorPort mport;
	public static int MIN_LIGHT;
	public static int MAX_LIGHT;
           
    public FollowLight( String name, int LCDrow, Behavior b, SensorPort sp, MotorPort mp)
    {
    	super(name, LCDrow, b);
        ls = new RCXLightSensor(sp);
        mport = mp;
    }
   
	public static int normalizeValue(int light, int mini, int maxi){

		int res;
		res = (light-mini)*100/(maxi-mini);
		
		if ( res > 100 )
			return 100;
			
		if ( res < 0 )
			return 0;
		
		return res;
	}
    
    public void run() 
    {
    	min = MIN_LIGHT;
    	max = MAX_LIGHT;

        while (true)
        {
        	light = ls.getNormalizedLightValue();
        	
        	if ( light > max )
        		max = light;
        	
        	if ( light < min )
        		min = light;
        	
        	norm = normalizeValue(light, min, max);

        	
        	drawInt(norm);
        	
            suppress();
            
			mport.controlMotor(norm,1);
			delay(500);
			
            release();		   
       }
    }
}


