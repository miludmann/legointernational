import lejos.nxt.*;
import lejos.subsumption.*;

public class PlaySound implements Behavior {
   
   public PlaySound()
   {
	   //Empty
   }
   
   public boolean takeControl() {
      return Button.ENTER.isPressed();
   }

   public void suppress() {
      //Empty
   }

   public void action() {
      try{Sound.beep();}
      catch (Exception e) {}
   }
}