package Units;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.TouchSensor;

public class BombTest {
	private Motor redLight = new Motor(MotorPort.C);
	private Motor greenLight = new Motor(MotorPort.B);	
	private Motor claw_m = new Motor(MotorPort.A);
	private TouchSensor touch = new TouchSensor(SensorPort.S1);
	private static int FLASHTIME = 200;
	
	private void run() {
		Thread claw = new Thread(new controlClaw(claw_m)); 
		Thread stopPrg = new Thread(new stopProgram());
		Thread watchBall = new Thread(new watchBall());
		claw.start();
		stopPrg.start();
		watchBall.start();

		while(true){
			redLight.forward();
			try {
				Thread.sleep(FLASHTIME);
			} catch (InterruptedException e) {}
			redLight.stop();
			greenLight.forward();
			try {
				Thread.sleep(FLASHTIME);
			} catch (InterruptedException e) {}
			greenLight.stop();
		}
	} 
	
	public static void main(String[] args) { 
		new BombTest().run(); 
  	}

	private class controlClaw extends Thread{
		private Motor claw;
		
		public controlClaw(Motor claw_m){
			claw = claw_m;
			claw.regulateSpeed(false);
		}
		
		public void run(){			
			while(true){
				Button.ENTER.waitForPressAndRelease();
					claw.rotate(60);
				Button.ENTER.waitForPressAndRelease();
					claw.rotate(-60);
					claw.lock(80);
			}
		}
	}
	
	private class watchBall extends Thread{
		public void run(){
			while(true){
				while(touch.isPressed()){}
				LCD.clearDisplay();
				LCD.drawString("KABOOM !", 0, 1);
				Sound.buzz();
				while(!touch.isPressed()){}
				LCD.clearDisplay();
				LCD.drawString("Ball secured", 0, 1);
				Sound.beep();	
			}
		}
	}
	
	private class stopProgram extends Thread{
		public void run(){
			while(!Button.ESCAPE.isPressed()){}
			System.exit(1);
		}
	}

}
