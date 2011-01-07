package Networking;

import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.Sound;

public class BluetoothCommanderTester implements MessageListenerInterface {

	protected BluetoothCommander m_commander;   
	   
	public BluetoothCommanderTester() {
		m_commander = new BluetoothCommander();
	}
	   
	public void launch() {
		
	   m_commander.addMessageListener(this);
	   m_commander.StartListen();
	   
	   Button.LEFT.addButtonListener(new ButtonListener() {
		   @Override
		   public void buttonReleased(Button b) {
			   m_commander.SendMessage(new LIMessage(LIMessageType.Command, "LEFT"));
		   }
			
		   @Override
		   public void buttonPressed(Button b) {};
	   });
	   
	   Button.RIGHT.addButtonListener(new ButtonListener() {
		   @Override
		   public void buttonReleased(Button b) {
			   m_commander.SendMessage(new LIMessage(LIMessageType.Command, "RIGHT"));
		   }
			
		   @Override
		   public void buttonPressed(Button b) {};
	   });
	
	   m_commander.StopListen();
		
		while (!Button.ESCAPE.isPressed())
		{
		}
	}

	@Override
	public void recievedNewMessage(LIMessage msg) {
		Sound.beep();		
	}
}
