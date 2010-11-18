import lejos.subsumption.*;
import lejos.nxt.*;

/**
 * Demonstration of the Behavior subsumption classes.
 * 
 * Requires a wheeled vehicle with two independently controlled
 * motors connected to motor ports A and C, and a touch
 * sensor connected to sensor  port 2.
 * 
 * @author Brian Bagnall and Lawrie Griffiths
 *
 */
public class BumperCar {
   public static void main(String [] args) {
      Behavior b1 = new DriveForward_improved();
      Behavior b2 = new PlaySound();
      Behavior b3 = new HitWall();
      Behavior b4 = new ExitImproved();
      Behavior [] bArray = {b1, b2, b3, b4};
      AltArbitrator arby = new AltArbitrator(bArray);
      Motor.A.setSpeed(200);
      Motor.C.setSpeed(200);
      arby.start();
   }
}

