package Lesson10;

import lejos.nxt.*;
import lejos.robotics.subsumption.*;

public class HitWall implements Behavior {
	
   TouchSensor touch;
   
   public HitWall()
   {
	   touch = new TouchSensor(SensorPort.S2);
   }
   
   public void suppress() {}
   
   public boolean takeControl() {
      return touch.isPressed();
   }

   public void action() {
      // Back up:
      try {
        Motor.A.backward();
        Motor.C.backward();
        Thread.sleep(1000);      
        // Rotate by causing only one wheel to stop:
        Motor.A.stop();
        Thread.sleep(300);
        Motor.C.stop();
      }
      catch(Exception e) {}
      Motor.A.stop();
      Motor.C.stop();
   }
}


