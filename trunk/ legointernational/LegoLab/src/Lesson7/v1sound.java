package Lesson7;

import Common.BluetoothLogger;
import Common.Car;
import Common.ILogger;
import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;

import Common.Car;

/**
 * The locomotions of a  LEGO 9797 car is controlled by
 * sound detected through a microphone on port 1.
 *
 * @author  Ole Caprani
 * @version 23.08.07
 *
 * @author Dyhrberg
 *  - Rewritten to use an eventbased structure.
 *  - Included a simple state like structure.
 * 
 */
public class v1sound
{
	private static int soundThreshold = 90;
	private static int snd = 0;
    
    private static boolean m_running = true;
    private static int m_state = 0;
    private static boolean m_allowNewReaction = true;
    
    public static ILogger il;
    public static String soundvalue = "Sound value";
	public static String strSnd;	


    public static void main(String [] args) throws Exception
    {
		//il = new BluetoothLogger();
		
        LCD.drawString("dB level: ",0,0);
        LCD.refresh();
       
        Button.ESCAPE.addButtonListener( new ButtonListener () {
            public void buttonPressed(Button arg0) {
                m_running = false;
            }

            public void buttonReleased(Button arg0) {
                 //do nothing
            }        
        });
       
        SensorPort.S1.addSensorPortListener( new SensorPortListener() {

            public void stateChanged(SensorPort arg0, int arg1, int arg2) {
                if(SensorPort.S1 == arg0)
                {   
                    int SoundSensorNormalizedValue = ((1023 - arg2) * 100/ 1023);
                    snd=SoundSensorNormalizedValue;
  
               }    
            }     
        });

    	LCD.drawString(soundvalue,1,4);

        
       while (m_running)
       {
    	   strSnd = Integer.toString(snd);
           //il.log(new Object[]{snd});
    	   //il.log(strSnd);
           Car.forward(snd, snd);
           LCD.drawInt(snd, 2, 2);
           Thread.sleep(10);
       }
      
       Car.stop();
       LCD.clear();
       LCD.drawString("Program stopped", 0, 0);
       Thread.sleep(2000);
   }
}