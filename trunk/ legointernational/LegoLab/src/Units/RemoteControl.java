package Units;

import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.robotics.navigation.Pilot;
import lejos.robotics.navigation.TachoPilot;
import Networking.LIMessage;
import Networking.MessageFramework;
import Networking.MessageListenerInterface;

/** 
 * Thread to wait for a Bluetooth connection and execute remote commands 
 */ 
class RemoteControl extends Thread implements MessageListenerInterface { 
	private int speed;
  	private final static int DIFF_SPEED = 30;
  	protected volatile boolean remoteCtrlAlive;
  
  	TachoPilot pilot;
	  
	public void run() { 
		remoteCtrlAlive = true;
		pilot = new TachoPilot(3.0f,12.5f,Motor.B, Motor.C); 
	
	    LCD.drawString("Waiting",0,0); 
	    MessageFramework mfw = MessageFramework.getInstance();
	    mfw.addMessageListener(this);
	    mfw.StartListen();
	    LCD.drawString("Connected",0,0); 
	   
	    speed = 500;
	    
	    while(remoteCtrlAlive) {} 
	}
	  
	public void stop() {
		remoteCtrlAlive = false;
	}

  	@Override
  	public void recievedNewMessage(LIMessage msg) {		
  		LCD.drawString("Command: "+msg.getPayload(), 0, 5);
  		if(msg.getPayload().equals("stop"))
  		{
		  pilot.stop();
		  LCD.clearDisplay();
		  LCD.drawString("STOP", 0, 6);
  		}
  		else if(msg.getPayload().equals("forward"))
  		{
		  pilot.forward(); 
		  LCD.clearDisplay();
		  LCD.drawString("FORWARD", 0, 6);
  		}
  		else if(msg.getPayload().equals("backward"))
  		{
		  pilot.backward(); 
		  LCD.clearDisplay();
		  LCD.drawString("BACKWARD", 0, 6);
  		}
  		else if(msg.getPayload().equals("left"))
  		{
		  pilot.steer(-200, 2000, true); 
		  LCD.clearDisplay();
		  LCD.drawString("LEFT", 0, 6);
  		}
  		else if(msg.getPayload().equals("right"))
  		{
		  pilot.steer(200, 2000, true); 
		  LCD.clearDisplay();
		  LCD.drawString("RIGHT", 0, 6);
  		}
  		else if(msg.getPayload().equals("fwdL"))
  		{
		  pilot.arc(-20, 360, true);
		  LCD.clearDisplay();
		  LCD.drawString("FwdL", 0, 6);
  		}
  		else if(msg.getPayload().equals("fwdR"))
  		{
		  pilot.arc(20, 360, true); 
		  LCD.clearDisplay();
		  LCD.drawString("FwdR", 0, 6);
  		}
  		else if(msg.getPayload().equals("rwdL"))
  		{
		  pilot.arc(-20, -360, true);
		  LCD.clearDisplay();
		  LCD.drawString("RwdL", 0, 6);
  		}
  		else if(msg.getPayload().equals("rwdR"))
  		{
  		  pilot.arc(20, -360, true);
		  LCD.clearDisplay();
		  LCD.drawString("RwdR", 0, 6);
  		}
  		else if(msg.getPayload().equals("increaseSpeed"))
  		{
  		  speed = speed + DIFF_SPEED;
  		  if(speed <= 0)
  			  speed = 0;
  		  pilot.setSpeed(speed);
  		  LCD.clearDisplay();
  		  LCD.drawString("INCREASE SPEED", 0, 6);
		}
  		else if(msg.getPayload().equals("decreaseSpeed"))
  		{
  		  speed = speed - DIFF_SPEED;
  		  if(speed >= 800)
			  speed = 800;
  		  pilot.setSpeed(speed);
		  LCD.clearDisplay();
  		  LCD.drawString("DECREASE SPEED", 0, 6);
		}
  		else {
  			LCD.drawString("No match", 0, 6);
  		}
		
	} 
} 