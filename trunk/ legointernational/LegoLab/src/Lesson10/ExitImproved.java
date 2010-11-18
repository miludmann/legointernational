package Lesson10;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.robotics.subsumption.Behavior;

public class ExitImproved implements Behavior {
   
   public ExitImproved()
   {
	   //Empty
   }
   
   public void suppress() {}
   
   public boolean takeControl() {
      return Button.ESCAPE.isPressed();
   }

   @SuppressWarnings("static-access")
public void action() {
      try {
        LCD.drawString("Exiting...", 1, 1);
        Thread.currentThread().sleep(1000);
        System.exit(0);
      }
      catch(Exception e) {}
   }
}