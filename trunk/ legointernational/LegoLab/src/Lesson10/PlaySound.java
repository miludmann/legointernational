package Lesson10;

import lejos.nxt.Button;
import lejos.nxt.Sound;
import lejos.robotics.subsumption.Behavior;

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