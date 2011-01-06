package Lesson5;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.MotorPort;
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
public class LineFollowerPid
{
  public static void main (String[] aArg)
  throws Exception
  {
     // final int power = 80;
	  
     BlackWhiteSensor sensor = new BlackWhiteSensor(SensorPort.S3);
	 /*
     String black = "black";
	 String white = "white";
*/
	
     // P control only
     //float Kp = 500, Ki = 0F, Kd = 0F;

     // PI control
     float Kp = 450F, Ki = 21.6F, Kd = 0F;
     
     // PID control
     //float Kp = 600, Ki = 48, Kd = 3;

     float offset = 45, Tp = 45;
     float  integral = 0, lastError = 0, derivative = 0;
     float Turn, powerA, powerC;
     float error;
     
     int lightValue = 0;
     
     
     sensor.calibrate();
     offset = sensor.getOffset();
	 
     LCD.clear();
     LCD.drawString("Light: ", 0, 2); 
     LCD.drawString("Color: ", 0, 3); 
     LCD.drawString("motorA:", 0, 4);
     LCD.drawString("motorB:", 0, 5);
     LCD.drawString("Turn:", 0, 6);

 
     while (! Button.ESCAPE.isPressed())
     {
    	 
    	 lightValue = sensor.light();
    	 error = lightValue - offset;
    	 if ( lastError * error < 0 ) {
    		 integral = 0;
    	 }
    	 else {
    		 integral = (integral + error);        // calculate the integral	 
    	 }
    	 derivative = error - lastError;     		// calculate the derivative
    	 Turn = Kp*error + Ki*integral + Kd*derivative;  // the "P term" the "I term" and the "D term"
    	 Turn = Turn/100;                      		// REMEMBER to undo the affect of the factor of 100 in Kp, Ki and Kd!
    	 powerA = Tp + Turn;                 		// the power level for the A motor
    	 powerC = Tp - Turn;  						// the power level for the C motor
    	 
    	 if (powerA > 100)
    	 {
    		 powerA = 100;
    	 } else {
    		 if(powerA < 0){
    			 powerA = 0;
    		 }
    	 } 
    	 if (powerC > 100)
    	 {
    		 powerC = 100;
    	 } else {
    		 if(powerC < 0){
    			 powerC = 0;
    		 }
    	 }
    	 
		 MotorPort.A.controlMotor((int) powerA, 1);
    	 MotorPort.C.controlMotor((int) powerC, 1);

		 LCD.drawInt((int) powerA, 4, 8, 4);
		 LCD.drawInt((int) powerC, 4, 8, 5);
		 LCD.drawInt((int) Turn, 4, 8, 6);

    	 lastError = error;                
    	 
    	 
	     LCD.drawInt(sensor.light(),4,10,2);
	     LCD.refresh();
	     
	     
	     /*
	     if ( sensor.black() )
	     {
	    	// Car.forward(power, 0);
	     	LCD.drawString(black,1,4);
	     }
	     else {
	         // Car.forward(0, power);
		     LCD.drawString(white,1,4);
	
	     }
	     */
	     Thread.sleep(10);
     }
     
     Car.stop();
     LCD.clear();
     LCD.drawString("Program stopped", 0, 0);
     LCD.refresh();
   }
}