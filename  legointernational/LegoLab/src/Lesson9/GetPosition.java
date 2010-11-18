package Lesson9;

import lejos.nxt.*;
import lejos.robotics.navigation.SimpleNavigator;
import lejos.robotics.navigation.TachoPilot;

/**
 * example of some TachoNavigator methods
 */
public class GetPosition
{
	static SimpleNavigator tachoNav;

    @SuppressWarnings("deprecation")
	public static void main( String[] args)
    {     
        tachoNav = new SimpleNavigator(7.8f, 11.2f, Motor.B, Motor.C);
        tachoNav.setMoveSpeed(500);
        tachoNav.setPosition(0, 0, 90);// heading along y axis
        tachoNav.goTo(-10, 0);
        int row = 0;
        print(row);
        Button.waitForPress();
        tachoNav.goTo(10,20, true);
        while (tachoNav.isMoving())
        {
            Sound.pause(500);// wait half a second
            tachoNav.updatePosition();
            print(row++);
            if (row > 7)row = 1;
        }
        tachoNav.stop();// calls update position;
        print(row);
        Button.waitForPress();
    }
    static void print(int row)
    {
        LCD.drawInt((int)tachoNav.getX(),4,0,row);
        LCD.drawInt((int)tachoNav.getY(), 4,6, row);
        LCD.drawInt((int)tachoNav.getAngle(),4,10,row);      
    }

 }

