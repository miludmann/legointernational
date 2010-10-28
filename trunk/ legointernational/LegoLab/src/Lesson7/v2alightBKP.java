package Lesson7;

import java.util.ArrayList;
import java.util.Queue;

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
public class v2alightBKP
{
    public static String lightValues = "Light values";
    public static String minmax = "MIN/MAX";
	public static String strSnd;
	public static int light1, norm1;
	public static int light2, norm2;


	public static RCXLightSensor ls1, ls2;

	public static int normalizeValue(int light, int MIN_LIGHT, int MAX_LIGHT){

		int res;
		res = (light-MIN_LIGHT)*100/(MAX_LIGHT-MIN_LIGHT);
		
		if ( res > 100 )
			return 100;
			
		if ( res < 0 )
			return 0;
		
		return res;
	}
	
	public static int adjust_min(int light1, int light2, int MIN_LIGHT){
		
		return Math.min(MIN_LIGHT, Math.min(light1, light2));
	}
	
	public static int adjust_max(int light1, int light2, int MIN_LIGHT){
		
		return Math.max(MIN_LIGHT, Math.max(light1, light2));
	}
	
	

    public static void main(String [] args) throws Exception
    {
    	
    	int MIN_LIGHT = 999;
    	int MAX_LIGHT = 0;
 
        ls1 = new RCXLightSensor(SensorPort.S2);
        ls2 = new RCXLightSensor(SensorPort.S3);
        ls1.setFloodlight(true);
        ls2.setFloodlight(true);

        
        while (! Button.ESCAPE.isPressed())
        {
        	light1 = ls1.getNormalizedLightValue();
        	light2 = ls2.getNormalizedLightValue();
       	
        	MIN_LIGHT = adjust_min(light1, light2, MIN_LIGHT);
        	MAX_LIGHT = adjust_max(light1, light2, MAX_LIGHT);

        	norm1 = normalizeValue(light1, MIN_LIGHT, MAX_LIGHT);
        	norm2 = normalizeValue(light2, MIN_LIGHT, MAX_LIGHT);
        	
        	LCD.clear();

        	LCD.drawString(lightValues, 1, 1);

        	LCD.drawInt(norm1, 3, 2, 2);
        	LCD.drawInt(norm2, 3, 2, 3);
        	
        	LCD.drawString(minmax, 1, 5);

        	LCD.drawInt(MIN_LIGHT, 3, 9, 5);
        	LCD.drawInt(MAX_LIGHT, 3, 13, 5);
        	
			Thread.sleep(10);

			Car.forward(norm1, norm2);

        }
      
       Car.stop();
       LCD.clear();
       LCD.drawString("Program stopped", 0, 0);
       Thread.sleep(500);
   }
}