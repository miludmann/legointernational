package Lesson4;

import lejos.nxt.*;

public class SejwayBad {
    public static void main (String[] args) {
		int NEUTRAL = 37;
		int speed = 900;
		LightSensor s = new LightSensor (SensorPort.S3, true);
		Motor.B.setSpeed (speed);
	    Motor.C.setSpeed (speed);
		
		while (!Button.ENTER.isPressed()) {
		    int SENS_VAL = s.readValue();
		    
		    boolean forward = SENS_VAL > NEUTRAL;
		    if (forward) {
		    	Motor.B.forward();
		    	Motor.C.forward();
		    } else {
		    	Motor.B.backward();
		    	Motor.C.backward();
		    }
		}
		
		s.setFloodlight(false);
		Motor.B.flt();
		Motor.C.flt();
	}
}