package Units;

import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
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
			for(int i = 0; i<values.length; i++)
			{
				if(values[i] != valuesOld[i])
				{
//					LCD.drawString(Integer.toString(values[i]), i*3, 7);
					msg = new LIMessage(LIMessageType.Command, "IRV "+i+" "+values[i]);
					mfw.SendMessage(msg);
				}
			}
			if(direction != directionOld)
			{
				LCD.drawString("IR direction: "+direction, 0, 7);
				msg = new LIMessage(LIMessageType.Command, "IRD "+direction);
				mfw.SendMessage(msg);
			}
			if(direction != 0){
				if((values[0]>=204 && values[0]<=255) || (values[1]>=204 && values[1]<=255) || (values[2]>=204 && values[2]<=255) ||
						(values[3]>=204 && values[3]<=255) || (values[4]>=204 && values[4]<=255))
				{
					Sound.playTone(1400, 20, 100);	
				} else if((values[0]>=153 && values[0]<204) || (values[1]>=153 && values[1]<204) || (values[2]>=153 && values[2]<204) ||
						(values[3]>=153 && values[3]<204) || (values[4]>=153 && values[4]<204))
				{
					Sound.playTone(1400, 20, 80);
				} else if((values[0]>=102 && values[0]<153) || (values[1]>=102 && values[1]<153) || (values[2]>=102 && values[2]<153) ||
						(values[3]>=102 && values[3]<153) || (values[4]>=102 && values[4]<153))
				{
					Sound.playTone(1400, 20, 60);
				} else if((values[0]>=51 && values[0]<102) || (values[1]>=51 && values[1]<102) || (values[2]>=51 && values[2]<102) ||
						(values[3]>=51 && values[3]<102) || (values[4]>=51 && values[4]<102))
				{
					Sound.playTone(1400, 20, 40);
				} else if((values[0]>=0 && values[0]<51) || (values[1]>=0 && values[1]<51) || (values[2]>=0 && values[2]<51) ||
				   (values[3]>=0 && values[3]<51) || (values[4]>=0 && values[4]<51))
				{
					Sound.playTone(1400, 20, 20);
				}  
		
			}
			valuesOld = values;
			directionOld = direction;
		}
	}
	
	public void stop(){
		irRadarAlive = false;
	}
}
