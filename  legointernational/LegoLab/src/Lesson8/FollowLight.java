package Lesson8;

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
	public RCXLightSensor ls1, ls2;
	public int min, max;
	public int light1, norm1;
	public int light2, norm2;
	public static int MIN_LIGHT;
	public static int MAX_LIGHT;
           
    public FollowLight( String name, int LCDrow, Behavior b)
    {
    	super(name, LCDrow, b);
        ls1 = new RCXLightSensor(SensorPort.S2);
        ls2 = new RCXLightSensor(SensorPort.S3);
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
    	
    	int tmp = 30;

        while (true)
        {
        	light1 = ls1.getNormalizedLightValue();
        	light2 = ls2.getNormalizedLightValue();
        	
        	if ( light1 > max )
        		max = light1;
        	
        	if ( light1 < min )
        		min = light1;
        	
        	if ( light2 > max )
        		max = light2;
        	
        	if ( light2 < min )
        		min = light2;


        	norm1 = normalizeValue(light1, min, max);
        	norm2 = normalizeValue(light2, min, max);


        	drawInt((norm1+norm2)/2);
        	
        	
        	if ( norm1 > tmp || norm2 > tmp )
        	{
	            suppress();
	            
	            forward(norm1, norm2);
	            delay(500);

	            stop();
	            delay(10);
				
	            release();
        	}
       }
    }
}


