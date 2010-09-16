package Lesson2;
import lejos.nxt.*;
/**
 * A simple sonar sensor test program.
 * 
 * The sensor should be connected to port 1. In the
 * known bugs and limitations of leJOS NXJ version alfa_03
 * it is mentioned that a gap of at least 300 msec is 
 * needed between calls of getDistance. This is the reason 
 * for the delay of 300 msec between sonar readings in the 
 * loop.
 * 
 * @author  Ole Caprani
 * @version 30.08.07
 */
public class SonicSensorTest 
{

   public static void main(String [] args)  
   throws Exception 
   {
	   
       UltrasonicSensor us = new UltrasonicSensor(SensorPort.S1);

       LCD.drawString("Distance(cm) ", 0, 0);
	   
       while (! Button.ESCAPE.isPressed())
       {
           LCD.drawInt(us.getDistance(),3,13,0);

           Thread.sleep(20); // This is the minimum scan time if we want full sensor range. Due to speed of sound
       }
       LCD.clear();
       LCD.drawString("Program stopped", 0, 0);
       Thread.sleep(2000);
   }
}
