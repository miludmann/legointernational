package Lesson3;

import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;

public class ClapCounter {

	private static boolean m_running = true;
	private static int m_clapCount = 0;
	
	public static void main(String[] args) throws Exception {
		
		LCD.drawString("Clap count: ",0,0);
        LCD.refresh();
        
		//Add Listeners
		Button.ESCAPE.addButtonListener( new ButtonListener() {

			@Override
			public void buttonPressed(Button b) {
				m_running = false;
			}

			@Override
			public void buttonReleased(Button b) {
			}
		});
		
		// Resetting the clap counter
		Button.ENTER.addButtonListener(new ButtonListener() {

			@Override
			public void buttonPressed(Button b) {
				try {
					LCD.clear();
					LCD.drawString("Counter resat", 0, 0);
					m_clapCount = 0;
					Thread.sleep(1000);
					LCD.clear();
				} catch (InterruptedException e) {
					LCD.drawString(e.getMessage(), 0, 0);
					System.exit(0);
				}
			}

			@Override
			public void buttonReleased(Button b) {
			}
		});

		SensorPort.S1.addSensorPortListener( new SensorPortListener () {

			@Override
			public void stateChanged(SensorPort source, int oldValue, int newValue) {
				m_clapCount++;
				LCD.drawInt(m_clapCount,4,12,0);
			}
		});
		
		
		while(m_running)
		{
		}
	}
}
