package Units;

import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.robotics.navigation.TachoPilot;
import Networking.BluetoothCommander;
import Networking.LIMessage;
import Networking.MessageListenerInterface;

/** 
 * Thread to wait for a Bluetooth connection and execute remote commands 
 */ 
class RemoteControl extends Thread implements MessageListenerInterface { 
  private final static byte STOP = 0; 
  private final static byte FORWARD = 1; 
  private final static byte BACKWARD = 2; 
  private final static byte LEFT = 3; 
  private final static byte RIGHT = 4; 
  
  TachoPilot pilot;
  
  public void run() { 
	pilot = new TachoPilot(5.6f,16.0f,Motor.B, Motor.C,true); 

    LCD.drawString("Waiting",0,0); 
    BluetoothCommander btc = new BluetoothCommander();
    btc.addMessageListener(this);
    LCD.drawString("Connected",0,0); 
   
    
    while(true) { 
      
    } 
  }

  	@Override
	public void recievedNewMessage(LIMessage msg) {
  		byte command = (byte)msg.m_payload.charAt(0);
		
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
	} 
} 