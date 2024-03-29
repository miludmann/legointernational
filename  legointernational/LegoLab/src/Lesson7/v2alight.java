package Lesson7;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

import Common.Car;
import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.nxt.addon.RCXLightSensor;


/**
 * The locomotions of a  LEGO 9797 car is controlled by
 * sound detected through a microphone on port 1.
 *
 * @author  Ole Caprani
 * @version 23.08.07
 *
 * @author Dyhrberg
 *  - Rewritten to use an eventbased structure.
 *  - Included a simple state like structure.
 * 
 */
public class v2alight
{
    public static String lightValues = "Light values";
    public static String minmax = "MIN/MAX";
	public static String strSnd;
	public static int light1, norm1;
	public static int light2, norm2;

	public static int MIN_LIGHT = 0;
	public static int MAX_LIGHT = 999;
	

	public static RCXLightSensor ls1, ls2;

	public static int normalizeValue(int light, int mini, int maxi){

		int res;
		res = (light-mini)*100/(maxi-mini);
		
		if ( res > 100 )
			return 100;
			
		if ( res < 0 )
			return 0;
		
		return res;
	}
	
	public static int adjustQueue(int light1, int light2, Queue qMin, int N){
		
		int tmp = ((light1+light2)/2);
		
		if ( N == 0 ){
			qMin.pop();	
		}
		else{
			N--;
		}
		
		qMin.push(tmp);
		
		return N;
	}

	
	private static int getMin(Queue queue) {
		int min = MAX_LIGHT;
		
		for( int i = 0; i<queue.size(); i++)
		{
			min = Math.min(min, (Integer) queue.elementAt(i));
		}
		return min;
	}
	
	private static int getMax(Queue queue) {
		int max = MIN_LIGHT;
		
		for(int i = 0; i<queue.size(); i++)
		{
			max = Math.max(max, (Integer) queue.elementAt(i));
		}
		return max;
	}

    public static void main(String [] args) throws Exception
    {
    	int mini = MIN_LIGHT;
    	int maxi = MAX_LIGHT;
    	int N = 100;
    	
    	Queue q = new Queue();

    	q.push(mini);
    	q.push(maxi);
 
        ls1 = new RCXLightSensor(SensorPort.S2);
        ls2 = new RCXLightSensor(SensorPort.S3);
        ls1.setFloodlight(true);
        ls2.setFloodlight(true);

        
        while (! Button.ESCAPE.isPressed())
        {
        	light1 = ls1.getNormalizedLightValue();
        	light2 = ls2.getNormalizedLightValue();
       	
        	N = adjustQueue(light1, light2, q, N);
        	
        	mini = getMin(q);
        	maxi = getMax(q);
        	

        	norm1 = normalizeValue(light1, mini, maxi);
        	norm2 = normalizeValue(light2, mini, maxi);
        	
        	LCD.clear();

        	LCD.drawString(lightValues, 1, 1);

        	LCD.drawInt(norm1, 3, 2, 2);
        	LCD.drawInt(norm2, 3, 2, 3);
        	
        	LCD.drawString(minmax, 1, 5);

        	LCD.drawInt(mini, 3, 9, 5);
        	LCD.drawInt(maxi, 3, 13, 5);
        	
			Thread.sleep(10);

			if ( N == 0 )
				Car.forward(norm1, norm2);

        }
      
       Car.stop();
       LCD.clear();
       LCD.drawString("Program stopped", 0, 0);
       Thread.sleep(500);
   }
}