package sandbox;

import lejos.nxt.*;
import lejos.nxt.comm.*;
import lejos.robotics.navigation.*;

import java.io.*;


public class BTTest {    
    
    public static void main(String [] args) throws Exception 
    {
      int[] command = new int[3];
      int[] reply = new int[8];
      boolean keepItRunning = true;
      String connected = "Connected";
      String waiting = "Waiting...";
      String closing = "Closing...";
      DataInputStream dis = null;
      DataOutputStream dos = null;
      BTConnection btc = null;
      UltrasonicSensor sonic = new UltrasonicSensor(SensorPort.S1);
      Pilot piloting = new TachoPilot(56.0f, 121.0f, Motor.C, Motor.A);
      
      LCD.drawString(waiting,0,0);
      LCD.refresh();
      
      Sound.twoBeeps();
      
      btc = Bluetooth.waitForConnection();
     
      dis = btc.openDataInputStream();
      dos = btc.openDataOutputStream();
      
      Sound.beepSequenceUp();

      dos.writeBytes("hurraaaaaa");
	  dos.flush();
      
      while (keepItRunning){
    	    
          // put your stuff here including code to change the Boolean
          // flag to false once the PC signals to end the connection
    	  
    	  
                  
      }
      
      dis.close();
      dos.close();
      Thread.sleep(100); // wait for data to drain
      LCD.clear();
      LCD.drawString(closing,0,0);
      LCD.refresh();
      btc.close();
      LCD.clear();
      
    }
	
//	public BTTest() throws InterruptedException
//	{
//		
//		LCPBTResponder lcpThread = new LCPBTResponder();
//		lcpThread.setDaemon(true);
//		lcpThread.start();
//	}
//		
//	public void Sence() throws InterruptedException
//	{
//		SoundSensor s = new SoundSensor(SensorPort.S1);
//	    int soundLevel;
//	    
//	    LCD.drawString("Level: ", 0, 0);
//
//       while (! Button.ESCAPE.isPressed())
//       {		   
//           soundLevel = s.readValue();
//           LCD.drawInt(soundLevel,3,7,0);
//           SendRemoteString();
//
//           Thread.sleep(5);
//       }
//	}
}
