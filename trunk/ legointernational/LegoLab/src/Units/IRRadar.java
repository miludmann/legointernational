package Units;

import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import Common.IRSeekerV2;
import Networking.LIMessage;
import Networking.LIMessageType;
import Networking.MessageFramework;

public class IRRadar extends Thread{
	protected IRSeekerV2 irseeker;
	protected int idIr;
	protected int[] values;
	protected int[] valuesOld = {-1, -1, -1, -1, -1};
	protected int direction;
	protected int directionOld;
	protected String valueStr;
	protected MessageFramework mfw;
	protected LIMessage msg;
	protected volatile boolean irRadarAlive;
	
	public IRRadar(IRSeekerV2 ir){
		irseeker = ir;
	    idIr = SensorPort.S1.getId();
	    direction = -1;
	    directionOld = -1;
	}
	
	public void run(){
	    irRadarAlive = true;
		mfw = MessageFramework.getInstance();
		
		while(irRadarAlive){
			values = irseeker.getSensorValues();
			direction = irseeker.getDirection();
//			for(int i = 0; i<values.length; i++)
//			{
//				if(values[i] != valuesOld[i])
//				{
//					LCD.drawString(Integer.toString(values[i]), i*3, 7);
//				}
//			}
			if(direction != directionOld)
			{
				LCD.drawString("IR direction: "+direction, 0, 7);
				msg = new LIMessage(LIMessageType.Command, "IR "+direction);
				mfw.SendMessage(msg);
			}
			valuesOld = values;
		}
	}
	
	public void stop(){
		irRadarAlive = false;
	}
}
