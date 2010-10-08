package Lesson6;

import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.addon.RCXLightSensor;

public class TurnHandler {
	
	public static TurnHandler m_instance = new TurnHandler();
	
	protected static int m_outerTachoValue = 500; //Enough to skip the center of the plateau 
	protected static int m_OuterPower = 100; //Maximum speed for fastest turn on outer wheel. Longest distance
	protected static int m_InnerPower = 53; //Measured distance for inner wheel shortest distance.  
	
	protected static RCXLightSensor m_leftLS = new RCXLightSensor(SensorPort.S3);
	protected static RCXLightSensor m_midLS = new RCXLightSensor(SensorPort.S2);
	protected static RCXLightSensor m_rightLS = new RCXLightSensor(SensorPort.S1);

	protected static Motor m_leftMotor = new Motor(MotorPort.B);
	protected static Motor m_rightMotor = new Motor(MotorPort.C);
	
	protected static boolean m_debugMode = false;
	
	private TurnHandler(){
		//nop;
	}
	
	///Default disabled
	public void setDebugModeEnabled(boolean enabled)
	{
		m_debugMode = enabled;
	}
	
	public static TurnHandler getInstance()
	{
		return m_instance;
	}
	
	public static void Turn180(int leftSensorLineThreshold, int midSensorLineThreshold, int rightSensorLineThreshold)
	{
		if(m_debugMode)
			Sound.beepSequenceUp();
		
		m_rightMotor.resetTachoCount();
		
		//Turn ccw
		MotorPort.B.controlMotor(50, 2); //Left
		MotorPort.C.controlMotor(50, 1); //Right
		
		while(m_rightMotor.getTachoCount() < 20) //measuring the outer wheel tacho for higher resolution
		{
			//Skip most of the turn
		}
		
		//if(m_debugMode)
		Sound.buzz();
		
		//keep going until we se a line again..
		while(SensorPort.S3.readRawValue() < leftSensorLineThreshold && SensorPort.S2.readRawValue() < midSensorLineThreshold && SensorPort.S1.readRawValue() < rightSensorLineThreshold)
		{
			//Change nothing just keep going until we se the line
		}
		
		if(m_debugMode)
		{
			MotorPort.B.controlMotor(m_OuterPower, 3); //Left stop
			MotorPort.C.controlMotor(m_OuterPower, 3); //Right stop
		
			Sound.beepSequence();
		}
	}
	
	public static void Turn(Direction direction, int leftSensorLineThreshold, int midSensorLineThreshold, int rightSensorLineThreshold)
	{
		if(m_debugMode)
			Sound.beepSequenceUp();
		
		if(direction == Direction.RIGHT)
		{
			m_leftMotor.resetTachoCount();
			m_rightMotor.resetTachoCount();
			
			MotorPort.B.controlMotor(m_OuterPower, 1); //Left
			MotorPort.C.controlMotor(m_InnerPower, 1); //Right  //-5 adjusted for mecanicly inconsistency on engines
			
			while(m_leftMotor.getTachoCount() < m_outerTachoValue) //measuring the outer wheel tacho for higher resolution
			{
				//skip most of the turn
			}
			
			//if(m_debugMode)
			Sound.buzz();
			
			//Now we want to follow the line again.
			while(SensorPort.S3.readRawValue() < leftSensorLineThreshold && SensorPort.S2.readRawValue() < midSensorLineThreshold && SensorPort.S1.readRawValue() < rightSensorLineThreshold)
			{
				//change nothing just keep going until we se the line
			}
			
			if(m_debugMode)
			{
				MotorPort.B.controlMotor(m_OuterPower, 3); //Left
				MotorPort.C.controlMotor(m_InnerPower, 3); //Right
			}
		}
		else if(direction == Direction.LEFT)
		{	
			m_leftMotor.resetTachoCount();
			m_rightMotor.resetTachoCount();
			
			MotorPort.B.controlMotor(m_InnerPower-3, 1); //Left
			MotorPort.C.controlMotor(m_OuterPower, 1); //Right
			
			while(m_rightMotor.getTachoCount() < m_outerTachoValue) //measuring the outer wheel tacho for higher resolution
			{
				//skip most of the turn
			}
			
			//if(m_debugMode)
			Sound.buzz();
			
			//Now we want to follow the line again.
			while(SensorPort.S3.readRawValue() < leftSensorLineThreshold && SensorPort.S2.readRawValue() < midSensorLineThreshold && SensorPort.S1.readRawValue() < rightSensorLineThreshold)
			{
				//change nothing just keep going until we se the line
			}
			
			if(m_debugMode)
			{
				MotorPort.B.controlMotor(m_InnerPower, 3); //Left
				MotorPort.C.controlMotor(m_OuterPower, 3); //Right
			}
		}
		
		if(m_debugMode)
			Sound.beepSequence();
	}

	public enum Direction
	{
		LEFT,
		RIGHT,
		TURN180
	}
	
	public enum CalibrateMode
	{
		TACHO,
		INNER_POWER,
		DIRECTION
	}
	
}
