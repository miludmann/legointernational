package sandbox;

import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.SoundSensor;
import Common.BluetoothCommander;
import Common.LIMessage;
import Common.LIMessageType;

/**
 * A test program to test the bluetooth commander 
 * 
 * @author Dyhrberg
 * @version 1.00.00
 */
public class LIMFEcho 
{
   public static void main(String [] args) throws Exception
   {
       final BluetoothCommander m_commander = new BluetoothCommander();
       
       
       //m_commander.StartListen();
       
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

       //m_commander.StopListen();
       
       LCD.clear();
       LCD.drawString("Program stopped", 0, 0);
       Thread.sleep(2000);
   }
}