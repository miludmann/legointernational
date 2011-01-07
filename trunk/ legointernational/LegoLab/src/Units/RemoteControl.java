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
    btc.StartListen();
    LCD.drawString("Connected",0,0); 
   
    
    while(true) { 
      
    } 
  }

  	@Override
	public void recievedNewMessage(LIMessage msg) {		
  		LCD.drawString("Command: "+msg.m_payload, 0, 5);
  		if(msg.m_payload.equals("0"))
  		{
		  pilot.stop();
		  LCD.clearDisplay();
		  LCD.drawString("STOP", 0, 6);
  		}
  		else if(msg.m_payload.equals("1"))
  		{
		  pilot.forward(); 
		  LCD.clearDisplay();
		  LCD.drawString("FORWARD", 0, 6);
  		}
  		else if(msg.m_payload.equals("2"))
  		{
		  pilot.backward(); 
		  LCD.clearDisplay();
		  LCD.drawString("BACKWARD", 0, 6);
  		}
  		else if(msg.m_payload.equals("3"))
  		{
		  pilot.steer(-200); 
		  LCD.clearDisplay();
		  LCD.drawString("LEFT", 0, 6);
  		}
  		else if(msg.m_payload.equals("4"))
  		{
		  pilot.steer(200); 
		  LCD.clearDisplay();
		  LCD.drawString("RIGHT", 0, 6);
  		} else {
  			LCD.drawString("No match", 0, 6);
  		}
		
	} 
} 