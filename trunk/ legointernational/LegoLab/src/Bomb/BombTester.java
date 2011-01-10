package Bomb;

public class BombTester {
	public static void main(String [] options) throws Exception{
		display_timer dt = new display_timer();
		
		dt.setBeepEnabled(false);
		dt.startCountdown();		   
	}
}
