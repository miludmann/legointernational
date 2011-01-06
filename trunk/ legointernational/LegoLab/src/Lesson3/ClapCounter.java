package Lesson3;

/**
 * Clap and MultiClap counter
 * 
 * @author  Anders Dyhrberg
 * @version 1.00.00
 * 
 */

import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import Logger.MultiLogger;

public class ClapCounter {

	//Clap detection parameters
	protected static final int CLAP_START_INDICATION_LEVEL = 50;
	protected static final int CLAP_PEAK_INDICATION_LEVEL = 80;
	protected static final int CLAP_END_INDICATION_LEVEL = 50;
	protected static final int CLAP_RISE_INDICATION_DELAY = 25;
	protected static final int CLAP_FALL_INDICATION_DELAY = 250;
	
	protected static final int MULTIBLE_CLAP_DETECT_DELAY = 1000;
	
	
	protected static boolean m_running = true;
	protected static int m_clapCount = 0;
	protected static int m_multiClapCount = 0; //Used to count the ammount of claps in one multi clap command
	protected static ClapDetectionState m_ClapState = ClapDetectionState.NOCLAP;
	protected static long m_LatestClapStateChangeTime = 0;

	//For debuging
	protected static MultiLogger logger = new MultiLogger("claplog.txt");
	
	public static void main(String[] args) throws Exception {
		
		LCD.drawString("Clap count: ",0,0);
		LCD.drawString("Multi clap: ",0,1);
        
        LCD.refresh();
        
		//Add Listeners
		Button.ESCAPE.addButtonListener( new ButtonListener() {

			public void buttonPressed(Button b) {
				m_running = false;
			}

			public void buttonReleased(Button b) {
			}
		});
		
		// Resetting the clap counter
		Button.ENTER.addButtonListener(new ButtonListener() {

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

			public void buttonReleased(Button b) {
			}
		});

		SensorPort.S1.addSensorPortListener( new SensorPortListener () {

			public void stateChanged(SensorPort source, int oldValue, int newValue) {
				
				int soundSensorNormalizedValue = ((1023 - newValue) * 100/ 1023);
				long msSinceLastState = System.currentTimeMillis() - m_LatestClapStateChangeTime;
				
				switch(m_ClapState)
				{
					case NOCLAP:
						if(soundSensorNormalizedValue > CLAP_START_INDICATION_LEVEL)
						{
							m_ClapState = ClapDetectionState.START_DETECTED;
							m_LatestClapStateChangeTime = System.currentTimeMillis();
							
							logger.log("NOCLAP -> START, " + soundSensorNormalizedValue + ", " + msSinceLastState + "ms");
						}
						else
						{
							//Do nothing
						}
						break;
					case START_DETECTED:
						if(msSinceLastState < CLAP_RISE_INDICATION_DELAY)
						{
							if(soundSensorNormalizedValue > CLAP_PEAK_INDICATION_LEVEL)
							{
								m_ClapState = ClapDetectionState.PEAK_DETECTED;
								m_LatestClapStateChangeTime = System.currentTimeMillis();
								
								logger.log("START -> PEAK, " + soundSensorNormalizedValue + ", " + msSinceLastState + "ms");
							}
						}
						else
						{
							//Time is up, does not match clap profile
							m_ClapState = ClapDetectionState.NOCLAP;
							m_LatestClapStateChangeTime = System.currentTimeMillis();
							
							logger.log("START -> NOCLAP, " + soundSensorNormalizedValue + ", " + msSinceLastState + "ms");
						}
						break;
					case PEAK_DETECTED:
						if(msSinceLastState < CLAP_FALL_INDICATION_DELAY)
						{
							if(soundSensorNormalizedValue < CLAP_END_INDICATION_LEVEL)
							{
								//Clap detected
								m_clapCount++;
								m_multiClapCount++;
								LCD.drawInt(m_clapCount,4,12,0);
								
								m_ClapState = ClapDetectionState.MULTI_CLAP_POTENTIAL;
								m_LatestClapStateChangeTime = System.currentTimeMillis();
								
								logger.log("PEAK -> MULTICLAP, " + soundSensorNormalizedValue + ", " + msSinceLastState + "ms");
							}
						}
						else
						{
							//Time is up, does not match clap profile
							m_ClapState = ClapDetectionState.NOCLAP;
							m_LatestClapStateChangeTime = System.currentTimeMillis();
							
							logger.log("PEAK_DETECTED -> NOCLAP, " + soundSensorNormalizedValue + ", " + msSinceLastState + "ms");
						}
						break;
					case MULTI_CLAP_POTENTIAL:
						if(msSinceLastState < MULTIBLE_CLAP_DETECT_DELAY)
						{
							if(soundSensorNormalizedValue > CLAP_START_INDICATION_LEVEL)
							{
								m_ClapState = ClapDetectionState.START_DETECTED;
								m_LatestClapStateChangeTime = System.currentTimeMillis();
								logger.log("MULTICLAP -> START, " + soundSensorNormalizedValue + ", " + msSinceLastState + "ms");
							}
						}
						else
						{
							//Evaluate multi clap scenario, no more are accepted since delay was to long
							LCD.drawInt(m_multiClapCount,4,12,1);
							
							//Cleanup
							m_multiClapCount = 0;
							m_ClapState = ClapDetectionState.NOCLAP;
							m_LatestClapStateChangeTime = System.currentTimeMillis();
						
							logger.log("MULTICLAP -> NOCLAP, " + soundSensorNormalizedValue + ", " + msSinceLastState + "ms");
						}
						break;
					default:
						//Unexpected system state.
						System.exit(0);
				}
			}
		});
				
		while(m_running)
		{
			//wait for user input to terminate program
		}
		
		logger.close();
	}
	
	enum ClapDetectionState
	{
		NOCLAP,
		START_DETECTED,
		PEAK_DETECTED,
		MULTI_CLAP_POTENTIAL
	}
	
}
