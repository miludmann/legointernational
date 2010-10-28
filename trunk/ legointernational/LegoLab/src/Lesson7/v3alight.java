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
public class v3alight
{
    public static String lightValues = "Light values";
    public static String minmax = "MIN/MAX";
	public static String strSnd;
	public static int light1, norm1;
	public static int light2, norm2;

	public static int MIN_LIGHT;
	public static int MAX_LIGHT;
	

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
	
	public static void adjust_min(int light1, int light2, Queue qMin){
		
		int tmp = Math.min(light1, light2);
		
		qMin.pop();
		qMin.push(tmp);
	}
	
	public static void adjust_max(int light1, int light2, Queue qMax){
		
		int tmp = Math.max(light1, light2);
		
		qMax.pop();
		qMax.push(tmp);	}
	
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

    public static void main(String [] args) throws Exception
    {
    	int mini = MIN_LIGHT;
    	int maxi = MAX_LIGHT;
    	int N = 1000;
    	
    	Queue qMin = new Queue();
    	Queue qMax = new Queue();
    	
    	for ( int i = 0; i<N; i++){
    		qMin.push(maxi);
    		qMax.push(mini);
    	}
 
        ls1 = new RCXLightSensor(SensorPort.S2);
        ls2 = new RCXLightSensor(SensorPort.S3);
        ls1.setFloodlight(true);
        ls2.setFloodlight(true);

        
        while (! Button.ESCAPE.isPressed())
        {
        	light1 = ls1.getNormalizedLightValue();
        	light2 = ls2.getNormalizedLightValue();
       	
        	adjust_min(light1, light2, qMin);
        	adjust_max(light1, light2, qMax);
        	
        	mini = getMin(qMin);
        	maxi = getMax(qMax);
        	

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

			Car.forward(norm1, norm2);

        }
      
       Car.stop();
       LCD.clear();
       LCD.drawString("Program stopped", 0, 0);
       Thread.sleep(500);
   }
}