package sandbox;

import Common.Car;
import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;

public class testcode {


	static int m_OuterPower = 100;
	static int m_InnerPower = 72;
	
	static int m_sleepTime = 1400; //Ignore most of the turn
	
	static int tempDarkValue = 70;
	static int tempLightValue = 40;
	
	public static void main(String[] args) throws InterruptedException {
		
		LCD.drawString("Inner:",0,0);
		LCD.drawInt(m_InnerPower, 5, 10, 0);
		
		LCD.drawString("Sleep", 0, 1);
		LCD.drawInt(m_sleepTime, 5, 10, 1);
		
		Button.LEFT.addButtonListener( new ButtonListener() {
		
			@Override
			public void buttonReleased(Button b) {
				m_sleepTime+=50;
				LCD.drawInt(m_InnerPower, 5, 10, 0);
				LCD.drawInt(m_sleepTime, 5, 10, 1);
			}
		
			@Override
			public void buttonPressed(Button b) {
			}
		});
		
		Button.RIGHT.addButtonListener( new ButtonListener() {
		
			@Override
			public void buttonReleased(Button b) {
				//m_sleepTime-=50;
				LCD.drawInt(m_InnerPower, 5, 10, 0);
				LCD.drawInt(m_sleepTime, 5, 10, 1);
				
			}
		
			@Override
			public void buttonPressed(Button b) {
			}
		});
		
		Button.ENTER.addButtonListener( new ButtonListener() {

			@Override
			public void buttonPressed(Button b) {
				Turn(Direction.LEFT, tempDarkValue, tempLightValue);
			}

			@Override
			public void buttonReleased(Button b) {
			}
			
		});
		
		while(!Button.ESCAPE.isPressed())
		{
			//Just do nothing
		}
	}
	
	public static void Turn(Direction direction, int rightSensorLineThreshold, int leftSensorLineThreshold)
	{
		try
		{
			if(direction == Direction.LEFT)
			{
				Car.backward(m_InnerPower, m_OuterPower);
				Thread.sleep(m_sleepTime); //Measured 1500

				while(SensorPort.S3.readRawValue() < leftSensorLineThreshold)
				{
					//change nothing just kepp going until we se the line
				}
				
				Car.stop();
				
			}
			
			if(direction == Direction.RIGHT)
			{
				Car.backward(m_OuterPower, m_InnerPower);
				Thread.sleep(m_sleepTime); //Mesured 1600
				
				while(SensorPort.S1.readRawValue() < rightSensorLineThreshold)
				{
					//change nothing just kepp going until we se the line
				}
				
				Car.stop();
			}
		} catch (Exception e){
			LCD.drawString(e.getMessage(), 0, 0);
		}
	}

	public enum Direction
	{
		LEFT,
		RIGHT
	}
	
}
