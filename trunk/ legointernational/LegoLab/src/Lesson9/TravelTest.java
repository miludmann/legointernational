package Lesson9;

import lejos.nxt.*;
import lejos.robotics.navigation.TachoPilot;

/**
* Robot that stops if it hits something before it completes its travel.
*/
public class TravelTest {
	TachoPilot pilot;

    public void  drawSquare(float length)
    {
        for(int i = 0; i<4 ; i++)
        {
            pilot.travel(length);
            pilot.rotate(90);   
        }
        
    }

	
	public static void main(String[] args) {
		TravelTest traveler = new TravelTest();
		traveler.pilot = new TachoPilot(7.8f, 11.2f, Motor.B, Motor.C);
		traveler.drawSquare(55);
	}
} 