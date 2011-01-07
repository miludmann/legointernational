package Networking;

import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.LCD;
import lejos.nxt.Sound;

/**
 * A test program to test the bluetooth commander 
 * 
 * @author Dyhrberg
 * @version 1.00.00
 */
public class BluetoothCommanderTester implements MessageListenerInterface 
{
   public static void main(String [] args) throws Exception
   {
	   BluetoothCommanderTester bct = new BluetoothCommanderTester();
	   bct.run();
   }
   
   public BluetoothCommanderTester() {
   }
   
   public void run()
   {
	   final BluetoothCommander m_commander = new BluetoothCommander();
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
       
	   while (!Button.ESCAPE.isPressed())
	   {
	   }

       m_commander.StopListen();
       
       LCD.clear();
       LCD.drawString("Program stopped", 0, 0);
       
       try {
		Thread.sleep(2000);
       } catch (InterruptedException e) {
			LCD.drawString(e.getMessage(), 0, 0);
       }
   }

	@Override
	public void recievedNewMessage(LIMessage msg) {
		Sound.beep();		
	}
}