package sandbox;
import Common.Car;
import lejos.nxt.*;

public class Race {
	
    private static MotorPort leftMotor = MotorPort.B;
    private static MotorPort rightMotor = MotorPort.C;

	public static void main (String[] aArg)
	throws Exception
	{
		
		// Forward
		Car.forward(100,100);
		Thread.sleep(3200);
		
		// Right-Turn
		Car.forward(0,100);
		Thread.sleep(1100);
	
		
		// Forward
		Car.forward(100,100);
		Thread.sleep(3200);

		// Left-Turn
		Car.forward(100,0);
		Thread.sleep(1100);
	
		// Forward
		Car.forward(100,100);
		Thread.sleep(3200);
/*		
		// U-Turn
		Car.forward(-100,100);
		Thread.sleep(800);
		
		// Forward
		Car.forward(100,100);
		Thread.sleep(2000);
		
		// Right-Turn
		Car.forward(0,100);
		Thread.sleep(800);	
		
		// Forward
		Car.forward(100,100);
		Thread.sleep(2000);
			
		// Left-Turn
		Car.forward(100,0);
		Thread.sleep(800);
		
		// Forward
		Car.forward(100,100);
		Thread.sleep(2000);
		
		Car.stop();
*/
	}
}