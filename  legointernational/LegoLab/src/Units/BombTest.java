package Units;

import java.io.File;

import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
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
	protected volatile boolean bombAlive;
	private enum clawState{open, closed};	
	
	private void run() {
		bombAlive = true;
		ControlClaw claw = new ControlClaw(claw_m); 
		WatchBall watchBall = new WatchBall();
		claw.start();
		watchBall.start();
		
		Button.ESCAPE.addButtonListener( new ButtonListener() {

			public void buttonPressed(Button b) {
				stop();
			}

			public void buttonReleased(Button b) {
			}
		});

		while(bombAlive){
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
		
		claw.stop();
		watchBall.stop();
		System.exit(1);
	} 
	
	public void stop(){
		bombAlive = false;
	}
	
	public static void main(String[] args) { 
		new BombTest().run(); 
  	}

	private class ControlClaw extends Thread{
		private Motor claw;
		protected volatile boolean clawAlive;
		protected clawState state;
		
		public ControlClaw(Motor claw_m){
			claw = claw_m;
			claw.regulateSpeed(false);
			state = clawState.closed;
		}
		
		public void run(){		
			clawAlive = true;
			while(clawAlive){
				Button.ENTER.waitForPressAndRelease();
					claw.rotate(60);
					state = clawState.open;
				Button.ENTER.waitForPressAndRelease();
					claw.rotate(-60);
					state = clawState.closed;
					claw.lock(100);
			}
		}
		
		public void stop(){
			clawAlive = false;
		}
	}
	
	private class WatchBall extends Thread{
		protected volatile boolean watchBallAlive;

		public void run(){
			File f = new File("explosin.wav");
			watchBallAlive = true;
			while(watchBallAlive){
				while(touch.isPressed()){}
				LCD.clearDisplay();
				LCD.drawString("KABOOM !", 0, 1);
//				Sound.buzz();
				Sound.playSample(f, 100);
				while(!touch.isPressed()){}
				LCD.clearDisplay();
				LCD.drawString("Ball secured", 0, 1);
				Sound.beep();	
			}
		}
		
		public void stop(){
			watchBallAlive = false;
		}
	}
}
