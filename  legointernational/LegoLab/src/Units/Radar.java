package Units;

import Networking.LIMessage;
import Networking.LIMessageType;
import Networking.MessageFramework;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.UltrasonicSensor;

public class Radar extends Thread {
	protected Motor radar_m;
	protected UltrasonicSensor radar_us;
	private String radarMsg;
	protected volatile boolean radarAlive;
	protected boolean isDoneRunning;
	
	public Radar(Motor motor, UltrasonicSensor sonic) {
		radar_us = sonic;
		radar_m = motor;		
	} 

	public void run() {
		isDoneRunning = false;
		radarAlive = true;
		radar_m.regulateSpeed(true);
		radar_m.setSpeed(150);
	    ListenUS listenUS = new ListenUS(); 
	    listenUS.start();

		while(radarAlive){
			radar_m.rotateTo(360);
			radar_m.rotateTo(0);
		}
		listenUS.stop();
		goToDefaultPosition();
		isDoneRunning = true;
	}
	
	public void stop() {
		radarAlive = false;
	}
	
	public boolean isDoneRunning() {
		return isDoneRunning;
	}
	
	public void goToDefaultPosition(){
		int currentAngle = radar_m.getTachoCount();
		
		radar_m.stop();
		radar_m.rotate(-currentAngle);
	}
	
	private class ListenUS extends Thread {
		protected LIMessage msg;
		protected MessageFramework mfw;
		protected volatile boolean radarAliveListen;
		
		public void run(){
			radarAliveListen = true;
			int dist;
			mfw = MessageFramework.getInstance();
			
			while(radarAliveListen){
				dist = radar_us.getDistance();
//				LCD.drawInt(radar_m.getTachoCount(), 3, 0, 4);
				if(dist == 255){
//					LCD.drawString("No obstacle", 0, 3);
				} else {
					radarMsg = getCoordinates(dist, radar_m.getTachoCount());
//					LCD.drawString(radarMsg, 0, 3);
					msg = new LIMessage(LIMessageType.Command, radarMsg);
					mfw.SendMessage(msg);
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					
				}				
			}
		}
		
		public void stop(){
			radarAliveListen = false;
		}
		
		public String getCoordinates(int dist, int angle){
			double angleRad = angle * Math.PI / 180;
			double xCoord = Math.cos(angleRad)* dist;
			double yCoord = Math.sin(angleRad)* dist;
			// Rotation of Pi/2 to get the axis in the right position (US sensor facing forward)
			int xCoord_rot = (int) -yCoord;
			int yCoord_rot = (int) xCoord;
			
			String xStr = Integer.toString(xCoord_rot); 
			String yStr = Integer.toString(yCoord_rot); 
			
//			for(int i = 0; i<4 ; i++)
//			{
//				if(xStr.length() < 4)
//					xStr = "0"+xStr;
//				if(yStr.length() < 4)
//					yStr = "0"+yStr;
//			}

			return "RC "+xStr+" "+yStr;
		}
	}
}
