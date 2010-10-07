package sandbox;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import Common.Car;
import Lesson5.ColorBWSensor;

public class uploadtest {
	public static void main (String[] aArg) throws Exception {
		  
		ColorBWSensor sensor = new ColorBWSensor(SensorPort.S3);
		
		lejos.nxt.LCD.drawString(sensor.toString(), 0, 0, false);
		
		final int power = 80;
		int i = 0;
		  
		String black = "black";
		String white = "white";
		String color = "color";
		
	     sensor.calibrate();

	     while (! Button.ESCAPE.isPressed() || i < 5 )
	     {

		     LCD.drawInt(sensor.light(),4,10,2);
		     LCD.refresh();
		     

		     if ( sensor.finishColor() ){
			     LCD.drawString(color,1,4);
			     i++;
			     
			     if ( i > 30 ){
				     Car.stop();
			     }
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
	     }
	     
	     Car.stop();
	     LCD.clear();
	     LCD.drawString("Program stopped", 0, 0);
	     LCD.refresh();
	  }
}
