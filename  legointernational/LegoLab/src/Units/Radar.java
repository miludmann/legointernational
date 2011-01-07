package Units;

import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.UltrasonicSensor;

public class Radar extends Thread {
	protected Motor radar_m;
	protected UltrasonicSensor radar_us;

	public Radar(Motor motor, UltrasonicSensor sonic) {
		radar_us = sonic;
		radar_m = motor;
	} 

	public void run() {
		radar_m.regulateSpeed(true);
		radar_m.setSpeed(300);
	    Thread listenUS = new Thread(new ListenUS()); 
	    listenUS.start();

		while(true){
			radar_m.rotate(360);
			radar_m.rotate(-360);
		}
	}
	
	private class ListenUS extends Thread {
		public void run(){
			int dist;
			while(true){
				dist = radar_us.getDistance();
				LCD.drawInt(radar_m.getTachoCount(), 3, 0, 4);
				if(dist == 255){
					LCD.drawString("No obstacle", 0, 3);
				} else {
					LCD.drawString(getCoordinates(dist, radar_m.getTachoCount()), 0, 3);
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					
				}				
			}
		}
		
		public String getCoordinates(int dist, int angle){
			double angleRad = angle * Math.PI / 180;
			double xCoord = Math.cos(angleRad)* dist;
			double yCoord = Math.sin(angleRad)* dist;
			// Rotation of Pi/2 to get the axis in the right position (US sensor facing forward)
			double xCoord_rot = -yCoord;
			double yCoord_rot = xCoord;
			
			String xStr = Double.toString(xCoord_rot); 
			String yStr = Double.toString(yCoord_rot); 
			if(xStr.length()>5)
				xStr = xStr.substring(0, 4);
			if(yStr.length()>5)
				yStr = yStr.substring(0, 4);
			return "RS:"+xStr+";"+yStr;
		}
	}
}
