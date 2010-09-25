package sandbox;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.SoundSensor;
import Common.BluetoothLogger;
/**
 * A simple sound sensor sampling program
 * that samples the sensor with a fixed sample interval
 * and store the values in a flash file "Sample.txt".
 * 
 * The sound sensor should be connected to port 1. 
 * 
 * @author  Ole Caprani
 * @version 2.09.08
 * 
 * @author Duhrberg
 * Adjusted to use the MultiLogger instead of the DataLogger
 */
public class SoundSampling 
{
   public static void main(String [] args) throws Exception
   {
SoundSensor s = new SoundSensor(SensorPort.S1);
       
       //MultiLogger logger = new MultiLogger("Sample.txt");
       BluetoothLogger logger = new BluetoothLogger();
       
       int soundLevel;
	   
       LCD.drawString("Level: ", 0, 0);

       StringBuilder sb = new StringBuilder();
       
       while (! Button.ESCAPE.isPressed())
       {	
    	   sb = new StringBuilder();
    	   
           soundLevel = s.readValue();
           LCD.drawInt(soundLevel,3,7,0);
		   
           //int limited = Math.min(soundLevel, 65);
           
           for(int i=0; i<soundLevel; i++)
        	   sb.append("*");
           
           logger.log(sb.toString());
       }
       logger.close();
       
       LCD.clear();
       LCD.drawString("Program stopped", 0, 0);
       Thread.sleep(2000);
   }
}