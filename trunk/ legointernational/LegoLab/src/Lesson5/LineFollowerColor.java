package Lesson5;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import Common.Car;

/**
 * A simple line follower for the LEGO 9797 car with
 * a light sensor. Before the car is started on a line
 * a BlackWhiteSensor is calibrated to adapt to different
 * light conditions and colors.
 * 
 * The light sensor should be connected to port 3. The
 * left motor should be connected to port C and the right 
 * motor to port B.
 * 
 * @author  Ole Caprani
 * @version 23.08.07
 */
public class LineFollowerColor
{
  public static void main (String[] aArg) throws InterruptedException
  {
     final int power = 80;
     int i = 0;
	  
     ColorBWSensor sensor = new ColorBWSensor(SensorPort.S3);
	 String black = "black";
	 String white = "white";
	 String color = "finish color";

	 
     sensor.calibrate();
	 
     LCD.clear();
     LCD.drawString("Light: ", 0, 2); 
     LCD.drawString("Color: ", 0, 3); 

	 
     while (! Button.ESCAPE.isPressed() || i < 5 )
     {

	     LCD.drawInt(sensor.light(),4,10,2);
	     LCD.refresh();
	     
	     if ( sensor.finishColor() ){
		     LCD.drawString(color,1,4);
		     i++;
	     }
	     else{
	    	 i = 0;
	    	 
		     if ( sensor.black() )
		     {
		    	Car.forward(power, 0);
		     	LCD.drawString(black,1,4);
		     }
		     else {
		         Car.forward(0, power);
			     LCD.drawString(white,1,4);
		     } 
	     }

	     
	     Thread.sleep(10);
	     
     }
     
     Car.stop();
     LCD.clear();
     LCD.drawString("Program stopped", 0, 0);
     LCD.refresh();
   }
}