package Lesson9;

import lejos.nxt.*;
import lejos.robotics.navigation.TachoPilot;

public class SquareTracer2 {
    TachoPilot pilot ;
    public void  drawSquare(float length)
    {
        byte direction = 1;
        if(length < 0 )
        {
            direction = -1;
            length = -length;
        }
        for(int i = 0; i<4 ; i++)
        {
            pilot.travel(length);
            pilot.rotate(direction * 90);                 
        }
    }
    public static void main( String[] args)
    {
        System.out.println(" Square Tracer 2");
        Button.waitForPress();
        SquareTracer2 sq = new SquareTracer2();
        sq.pilot = new TachoPilot(7.8f, 11.2f, Motor.B, Motor.C);
        byte direction = 1;
        int length = 55;
        for(int i = 0; i<4; i++)
        {
            sq.drawSquare(direction * length );
            if( i == 1)
            {
                sq.pilot.rotate( 90);
                direction = -1;
            }
        }
    }

} 