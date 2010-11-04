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

	public static void adjust_min(int light, Queue qMin){

		qMin.pop();
		qMin.push(light);
	}
	
	public static void adjust_max(int light, Queue qMax){
	
		qMax.pop();
		qMax.push(light);	}
	
	private static int getMin(Queue queue) {
		int min = MAX_LIGHT, i;
		for(i = 0; i<queue.size(); i++)
		{
			min = Math.min(min, (Integer) queue.elementAt(i));
		}
		return min;
	}
	
	private static int getMax(Queue queue) {
		int max = MIN_LIGHT, i;
		for(i = 0; i<queue.size(); i++)
		{
			max = Math.max(max, (Integer) queue.elementAt(i));
		}
		return max;
	}
   
    public void run() 
    {
    	
    	min = MIN_LIGHT;
    	max = MAX_LIGHT;
    	int N = 1000;
    	
    	Queue qMin = new Queue();
    	Queue qMax = new Queue();
    	
    	for ( int i = 0; i<N; i++){
    		qMin.push(max);
    		qMax.push(min);
    	}
    	
        while (true)
        {
        	light = ls.getNormalizedLightValue();
        	
        	adjust_min(light, qMin);
        	adjust_max(light, qMax);
        	
        	min = getMin(qMin);
        	max = getMax(qMax);
        	
        	norm = normalizeValue(light, min, max);

        	
        	drawInt(norm);
        	
            suppress();
            
			mport.controlMotor(norm,1);
			delay(500);
			
            release();		   
       }
    }
}


