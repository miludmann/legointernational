package Lesson10;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

/**
 * Demonstration of the Behavior subsumption classes.
 * 
 * Requires a wheeled vehicle with two independently controlled
 * motors connected to motor ports A and C, and 
 * a touchmsensor connected to sensor  port 1 and
 * an ultrasonic sensor connected to port 3;
 * 
 * @author Brian Bagnall and Lawrie Griffiths, modified by Roger Glassey
 *
 */
public class BumperCar
{

  public static void main(String[] args)
  {
    Motor.A.setSpeed(400);
    Motor.C.setSpeed(400);
    Behavior b1 = new DriveForward();
    Behavior b2 = new DetectWall();
    Behavior b3 = new Exit();
    Behavior[] behaviorList =
    {
      b1, b2, b3
    };
    Arbitrator arbitrator = new Arbitrator(behaviorList);
    LCD.drawString("Bumper Car",0,1);
    Button.waitForPress();
    arbitrator.start();
  }
}


class DriveForward implements Behavior
{

  private boolean _suppressed = false;

  public boolean takeControl()
  {
    return true;  // this behavior always wants control.
  }

  public void suppress()
  {
    _suppressed = true;// standard practice for suppress methods
  }

  public void action()
  {
    _suppressed = false;
    Motor.A.forward();
    Motor.C.forward();
    while (!_suppressed)
    {
      Thread.yield(); //don't exit till suppressed
    }
    Motor.A.stop(); // not strictly necessary, but good probramming practice
    Motor.C.stop();
  }
}


class DetectWall implements Behavior
{
	private boolean _suppressed = false;

  public DetectWall()
  {
    touch = new TouchSensor(SensorPort.S1);
    sonar = new UltrasonicSensor(SensorPort.S3);
  }

  public boolean takeControl()
  {
    sonar.ping();
    Sound.pause(20);
    return touch.isPressed() || sonar.getDistance() < 25;
  }

  public void suppress()
  {
	 _suppressed = true;// standard practice for suppress methods
  }

  public void action()
  {
    Motor.C.resetTachoCount();
    _suppressed = false;
    Motor.A.rotate(-180, true);// start Motor.A rotating backward
    Motor.C.backward();  // rotate C farther to make the turn
    while (!_suppressed && Motor.C.getTachoCount() > -360)
    {
      Thread.yield(); //don't exit till suppressed
    }
    Motor.C.stop();
  }
  
  private TouchSensor touch;
  private UltrasonicSensor sonar;
}

class Exit implements Behavior
{

	private boolean _suppressed = false;
	
	public Exit()
	{
	}

	public void action() {
	    _suppressed = false;
		LCD.drawString("Exiting...", 1, 1);
        System.exit(0);	
	}
	
	public void suppress() {
		 _suppressed = true;// standard practice for suppress methods		
	}
	
	public boolean takeControl() {
	    Sound.pause(20);
		return Button.ESCAPE.isPressed();
}
  
}


