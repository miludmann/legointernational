package Units;

import java.io.DataInputStream;
import java.io.IOException;

import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;
import lejos.robotics.navigation.TachoPilot;

/** 
 * Thread to wait for a Bluetooth connection and execute remote commands 
 */ 
class RemoteControl extends Thread { 
  private final static byte STOP = 0; 
  private final static byte FORWARD = 1; 
  private final static byte BACKWARD = 2; 
  private final static byte LEFT = 3; 
  private final static byte RIGHT = 4; 
  
  public void run() { 
	TachoPilot pilot = new TachoPilot(5.6f,16.0f,Motor.B, Motor.C,true); 

    LCD.drawString("Waiting",0,0); 
    NXTConnection conn = Bluetooth.waitForConnection(); 
    LCD.drawString("Connected",0,0); 
    DataInputStream dis = conn.openDataInputStream(); 
    
    while(true) { 
      try { 
        byte command = dis.readByte(); 
        switch (command) { 
        case STOP: 
          pilot.stop(); 
          break; 
        case FORWARD: 
          pilot.forward(); 
          break; 
        case BACKWARD: 
          pilot.backward(); 
          break; 
        case LEFT: 
          pilot.steer(-200); 
          break; 
        case RIGHT: 
          pilot.steer(200); 
          break; 
        } 
        Thread.yield(); 
      } catch (IOException e) { 
        System.out.println("IO Exception"); 
        System.exit(1); 
      } 
    } 
  } 
} 