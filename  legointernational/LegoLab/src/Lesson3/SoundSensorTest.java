package Lesson3;

import lejos.nxt.*;
/**
 * A simple sonar sensor test program.
 * 
 * The sensor should be connected to port 1. In the
 * known bugs and limitations of leJOS NXJ version alfa_03
 * it is mentioned that a gap of at least 300 msec is 
 * needed between calls of getDistance. This is the reason 
 * for the delay of 300 msec between sonar readings in the 
 * loop.
 * 
 * @author  Ole Caprani
 * @version 30.08.07
 */
public class SoundSensorTest 
{

   public static void main(String [] args)  
   throws Exception 
   {
	   
       SoundSensor ss = new SoundSensor(SensorPort.S1);
       ss.setDBA(false);
       
       SoundSensor ss_a = new SoundSensor(SensorPort.S4);
       ss_a.setDBA(true);
       
       MultiDataLogger dl = new MultiDataLogger("multilog.txt");
       
      // DataLogger dl_a = new DataLogger("Sample_dba.txt");
       
       LCD.drawString("Decible(db) ", 0, 0);
       LCD.drawString("Decible(dba) ", 0, 1);
	   
       Car.forward(60,60);
       
       while (! Button.ESCAPE.isPressed())
       {
    	   int db = ss.readValue();
    	   int dba = ss_a.readValue();
    	   
           LCD.drawInt(db,3,13,0);
           LCD.drawInt(dba,3,13,1);
      
           dl.writeSample( new int[] {db,dba});
           //dl_a.writeSample(dba);
           
           Thread.sleep(20);
       }
       
       dl.close();
       //dl_a.close();
       
       LCD.clear();
       LCD.drawString("Program stopped", 0, 0);
       Thread.sleep(2000);
   }
}