package Lesson7;

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
    
    private static boolean m_running = true;
    private static int m_state = 0;
    private static boolean m_allowNewReaction = true;

    public static void main(String [] args) throws Exception
    {
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
					
					LCD.drawInt(arg2,4,10,0);
					
					if(SoundSensorNormalizedValue < 90)
						m_allowNewReaction = true;
					
					if(SoundSensorNormalizedValue > soundThreshold && m_allowNewReaction)
					{
						//Notify program that we are above threshold
						switch (m_state)
						{
						case 0:
							LCD.drawString("Forward ",0,1);
					      	Car.forward(100, 100);
					      	break;
						case 1:
							LCD.drawString("Right   ",0,1);
				   	        Car.forward(100, 0);
				   	        break;
						case 2:
							LCD.drawString("Left    ",0,1);
							Car.forward(0, 100);
							break;
						case 3:
							LCD.drawString("Stop    ",0,1);
							Car.stop();
							break;
						}
						
						m_allowNewReaction = false;
						
						m_state++;
						if(m_state > 3)
							m_state = 0;
					}
				}     
	        }  	
        });
	   	   
       while (m_running)
       {
       }
       
       Car.stop();
       LCD.clear();
       LCD.drawString("Program stopped", 0, 0);
       Thread.sleep(2000); 
   }
}