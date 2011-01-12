package Bomb;

import lejos.nxt.Button;

public class BombTester {
	public static void main(String [] options) throws Exception{
		
//		while (!Button.ENTER.isPressed()) {
			display_timer dt = new display_timer();
//			
			dt.setBeepEnabled(false);
			dt.startCountdown();		   
//		}
	}
}
