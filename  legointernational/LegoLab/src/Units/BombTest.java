package Units;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.MotorPort;

public class BombTest {
	private Motor redLight = new Motor(MotorPort.C);
	private Motor greenLight = new Motor(MotorPort.B);	
	private Motor claw_m = new Motor(MotorPort.A);
	private static int FLASHTIME = 200;
	
	private void run() {
		Thread claw = new Thread(new controlClaw(claw_m)); 
		Thread stopPrg = new Thread(new stopProgram());
		claw.start();
		stopPrg.start();

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
//			Thread openClaw = new Thread(new openClaw());
//			Thread closeClaw = new Thread(new closeClaw());
//			openClaw.start();
//			closeClaw.start();
			
			while(true){
				Button.ENTER.waitForPressAndRelease();
					claw.rotate(60);
				Button.ENTER.waitForPressAndRelease();
					claw.rotate(-60);
					claw.lock(80);
			}
		}
		
		private class openClaw extends Thread{
			public void run(){
				while(true){
					if(Button.LEFT.isPressed())
					{
						claw.rotate(60, true);
					}
				}
			}
		}
		
		private class closeClaw extends Thread{
			public void run(){
				while(true){
					if(Button.RIGHT.isPressed())
					{
						claw.rotate(-60, true);
					}
				}
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
