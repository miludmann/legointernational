package Units;

import lejos.nxt.LCD;
import lejos.nxt.addon.CompassSensor;
import Networking.LIMessage;
import Networking.LIMessageType;
import Networking.MessageFramework;

public class Compass extends Thread{
	protected CompassSensor compass;
	protected float direction;
	protected float directionOld;
	protected String valueStr;
	protected MessageFramework mfw;
	protected LIMessage msg;
	protected volatile boolean isCompassAlive;
	
	public Compass(CompassSensor cp){
		compass = cp;
	    direction = -1;
	    directionOld = -1;
	}
	
	public void run(){
	    isCompassAlive = true;
		mfw = MessageFramework.getInstance();
		
		while(isCompassAlive){
			direction = compass.getDegreesCartesian();
//			for(int i = 0; i<values.length; i++)
//			{
//				if(values[i] != valuesOld[i])
//				{
//					LCD.drawString(Integer.toString(values[i]), i*3, 7);
//				}
//			}
			if(direction != directionOld)
			{
				LCD.drawString("Compass : "+direction, 0, 0);
				msg = new LIMessage(LIMessageType.Command, "CP "+direction);
				mfw.SendMessage(msg);
			}
			directionOld = direction;
		}
	}
	
	public void stop(){
		isCompassAlive = false;
	}
}