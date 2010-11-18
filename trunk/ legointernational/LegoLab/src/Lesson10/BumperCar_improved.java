package Lesson10;

import lejos.nxt.Motor;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

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
public class BumperCar_improved {
   public static void main(String [] args) {
      Behavior b1 = new DriveForward_improved();
      Behavior b2 = new PlaySound();
      Behavior b3 = new HitWall();
      Behavior b4 = new ExitImproved();
      Behavior [] bArray = {b1, b2, b3, b4};
      Arbitrator arby = new Arbitrator(bArray);
      Motor.A.setSpeed(200);
      Motor.C.setSpeed(200);
      arby.start();
   }
}

