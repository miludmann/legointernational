package Lesson9;

import lejos.nxt.*;
import lejos.robotics.navigation.TachoPilot;

public class SquareTracerAvoid {
	TachoPilot pilot;
	private UltrasonicSensor us ;
    private final int tooCloseThreshold = 20; // cm
    private static float AVOID = 30;
    private float right, left, top, bottom;
    private final static float length = 60;
    private int i = 0;
    

    public void  drawSquare(float length)
    {
    	right = length;
    	left = length;
    	bottom = length;
    	top = length;


    	checkObstacles(right);
    	i++;
        pilot.rotate(94);   
    	checkObstacles(top);
    	i++;
        pilot.rotate(94);   
    	checkObstacles(left);
    	i++;
    	pilot.rotate(94);   
    	checkObstacles(bottom);
    	i++;
    	pilot.rotate(94);   

        
    }

	
	public void checkObstacles(float length) {
		
		float distance = 0f;
		
		while(distance < length)
		{
			pilot.forward();
			if (us.getDistance() < tooCloseThreshold)
			{
				pilot.stop();
				pilot.rotate(94);
				pilot.travel(AVOID);
				pilot.rotate(-94);
			}
			distance = pilot.getTravelDistance();
		}
		pilot.stop();
	}


	public static void main(String[] args) {
		TravelTest traveler = new TravelTest();
		UltrasonicSensor us = new UltrasonicSensor(SensorPort.S1);
		traveler.pilot = new TachoPilot(7.8f, 11.2f, Motor.B, Motor.C);
		traveler.drawSquare(length);
	}

} 