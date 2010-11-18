import lejos.nxt.*;
import lejos.subsumption.*;

public class Exit implements Behavior {
   
   public Exit()
   {
	   //Empty
   }
   
   public void suppress() {}
   
   public boolean takeControl() {
      return Button.ESCAPE.isPressed();
   }

   public void action() {
      try {
        LCD.drawString("Exiting...", 1, 1);
        Thread.currentThread().sleep(1000);
        System.exit(0);
      }
      catch(Exception e) {}
   }
}