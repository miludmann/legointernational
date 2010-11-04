package Lesson6;

import java.util.ArrayList;
import java.util.Queue;

import Common.BluetoothLogger;
import Common.Car;
import Common.ILogger;
import Lesson6.TurnHandler.Direction;
import lejos.nxt.*;
import lejos.nxt.addon.RCXLightSensor;
import lejos.nxt.addon.TiltSensor;
import lejos.util.Stopwatch;

/**
 *
 */
public class Main
{
	private static int lRMin, lRMax, lMMin, lMMax, lLMin, lLMax, ldRange, ldMid ;
	private SensorPort pl = SensorPort.S3;
	private SensorPort pm = SensorPort.S2;
	private SensorPort pr = SensorPort.S1;
	
    TiltSensor tilt;
	 
	private MotorPort ml = MotorPort.C;
	private MotorPort mr = MotorPort.B;
	private RCXLightSensor lsL, lsM, lsR;
	
	private int C = 78;
	private static int EOL_MINOBSCOUNT = 5;
	private static int SPEEDHIGH = 80;
	private static int SPEEDMEDIUM = 65;
	private static int SPEEDLOW = 50;

	
	private static int SPEED_RANGE = 10;
	private static int SPEED_START = 4;
	private static int SPEED_FINALSLOW = 2;
	
	private boolean fTracking ;
	private boolean fStartTrackSegment;
	
	// Thresholds
	private int lLCrossThresh;
	private int lRCrossThresh;
	private int lsEOLThresh;
	private int lLLowThresh;
	private int lRLowThresh;
	private int lMLowThresh;
	private int lLHighThresh;
	private int lRHighThresh;
	private int lMHighThresh;
	private int lALowThresh;
	private int lAHighThresh;
	
	private ILogger il;
	private static int NONE = 0;
	private static int LEFT = -1;
	private static int RIGHT = 1;
	
	private int tiltOffset;
	
	private enum state{lookingForInclination, lookingForFlat};	
		  
	public Main()
	{
		il = new BluetoothLogger();

	    tilt = new TiltSensor(SensorPort.S4);

		  lsL = new RCXLightSensor(pl); 
		  lsM = new RCXLightSensor(pm); 
		  lsR = new RCXLightSensor(pr); 
		  
		  lMLowThresh = -1;
	}
	
	public int getlinePos()
	{
		int lLeft = lsL.getNormalizedLightValue();
		int lRight = lsR.getNormalizedLightValue();

		return lLeft - lRight + C ;     
	}
	
	public int getMidVal()
	{
		return lsM.getNormalizedLightValue();
	}
	
	public void setC()
	{
		LCD.clear();
		LCD.drawString("Enter to calibrate", 0, 0);
		while(!Button.ENTER.isPressed()){}
		int lLeft = lsL.getNormalizedLightValue();
		int lRight = lsR.getNormalizedLightValue();
		C = lRight - lLeft;
		LCD.clear();
		LCD.drawString("Constant set !", 0, 0);
	}
	
	public void setTiltOffset()
	{
		tiltOffset = tilt.getXTilt();
	}
	
	public void calibrate() throws InterruptedException{
		 Calibration cal = new Calibration(ml, mr, pl, pm, pr);
		 cal.scanLineForCal();
		 cal.calcLightParameters();
		 
		 lRMin = cal.getRightValues()[0];
		 lRMax = cal.getRightValues()[1];
		 lMMin = cal.getMiddleValues()[0];
		 lMMax = cal.getMiddleValues()[1];
		 lLMin = cal.getLeftValues()[0];
		 lLMax = cal.getLeftValues()[1];
		 
		 ldMid = cal.getLdMid();
		 ldRange = cal.getLdRange();
		 lALowThresh = cal.getAThresh()[0];
		 lAHighThresh = cal.getAThresh()[1];
		 lLLowThresh = cal.getLeftThresh()[0];
		 lLHighThresh = cal.getLeftThresh()[1];
		 lRLowThresh = cal.getRightThresh()[0];
		 lRHighThresh = cal.getRightThresh()[1];
		 lMLowThresh = cal.getMiddleThresh()[0];
		 lMHighThresh = cal.getMiddleThresh()[1];
		 
	     LCD.clear();
	     LCD.drawString("lLMin: ", 0, 1); 
	     LCD.drawString("lLMax: ", 0, 2); 
	     LCD.drawString("lMMin: ", 0, 3); 
	     LCD.drawString("lMMax: ", 0, 4); 
	     LCD.drawString("lRMin: ", 0, 5); 
	     LCD.drawString("lRMax: ", 0, 6); 
	 
	     LCD.drawInt(lLMin, 10, 1);
	     LCD.drawInt(lLMax, 10, 2);
	     LCD.drawInt(lMMin, 10, 3);
	     LCD.drawInt(lMMax, 10, 4);
	     LCD.drawInt(lRMin, 10, 5);
	     LCD.drawInt(lRMax, 10, 6);
	}
	
	
	public void trackLineSegment() throws InterruptedException
	{		
		state m_state = state.lookingForInclination;
				
		int tiltv;
		int tiltNb = 0;
		
		  int lL, lR;        
		  int ld;            
		  int ldA = 0;       
		  int icCode = 2;    
		  int r = 0;

		  int sInc = 1;
		  int sA = 0;

		  int pos, midVal;
		  float timer;
		  boolean stateChanged = true;
		  int previousState = NONE;
		  int aveTilt = 0;
		  boolean discarded = false;
		  
		  LCD.clear();
		  LCD.drawString("lMLow :", 0, 2);
		  LCD.drawString("Midval :", 0, 3);
		  
		  timer = System.currentTimeMillis();
		  
		  Queue queue = new Queue(); 
		  
		  while (!Button.ESCAPE.isPressed()) {
			  pos = getlinePos();
			  midVal = getMidVal();
			  
			  tiltv = tilt.getXTilt();
			  if(tiltv <= tiltOffset + 20 && tiltv >= tiltOffset - 20)
			  {
			  	queue.push(tiltv);
			  	discarded = false;
			  } else {
				  discarded = true;
			  }
			  if(queue.size() >= 15)
			  {
				  aveTilt = getAverage(queue);
				  if(m_state == state.lookingForInclination)
				  {
					  if(Math.abs(aveTilt - tiltOffset) >= 6)
					  {
						  m_state = state.lookingForFlat;
						  il.log("**********lookingForFlat**************");
					  }
				  } else {
					  if(Math.abs(aveTilt-tiltOffset) <= 2)
					  {
						  Car.stop();
						  il.log("=============Flat Found>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
						  break;
					  }
				  }
				  queue.pop();
			  }
			  
			  il.log(new Object[]{aveTilt, tiltv, tiltOffset, discarded, pos, previousState, stateChanged});
	
			  
			  LCD.drawInt(lMLowThresh, 10, 2);
			  LCD.drawInt(midVal, 10, 3);
			  
//			  if(!stateChanged && Math.abs(System.currentTimeMillis() - timer) >= 200)
//			  {
//				  if(previousState == LEFT)
//				  {
//					  while(Math.abs(pos) > 5)
//					  {
//						  pos = getlinePos();
//						  Car.forward(SPEEDHIGH, SPEEDLOW);
//					  }
//				  } else {
//					  if(previousState == RIGHT)
//					  {
//						  while(Math.abs(pos) > 5)
//						  {
//							  pos = getlinePos();
//							  Car.forward(SPEEDLOW, SPEEDHIGH);
//						  }
//					  } else {
////						  while(Math.abs(pos) > 5)
////						  {
////							  pos = getlinePos();
////							  Car.backward(SPEEDHIGH, SPEEDHIGH);
////						  }
//					  }
//				  }
//				  timer = System.currentTimeMillis();
//			  }
			  
//			  if(lMLowThresh != -1 && midVal < lMLowThresh )
//			  {
//				  Car.forward(SPEEDHIGH, SPEEDHIGH);
//			  } else {
			
			  
			
			
			  
			  
			  	  if(pos >= 5 ) {
					  Car.forward(SPEEDHIGH, SPEEDLOW); //fast turn right
				  } else if(pos < 5 && pos >= 3) {
					  Car.forward(SPEEDHIGH, SPEEDMEDIUM); //slow turn right
				  } else if(pos < 3 && pos > -3) {
					  Car.forward(SPEEDHIGH, SPEEDHIGH);
				  } else if(pos <= -3 && pos > -5) {
					  Car.forward(SPEEDMEDIUM, SPEEDHIGH); //slow turn left
				  } else { // Pos < -4
					  Car.forward(SPEEDLOW, SPEEDMEDIUM); //
				  }
			   					  
		  }
		  
//		  Car.stop();
//		  Thread.sleep(1000000);


		  // Main task loop
//		  while (!Button.ESCAPE.isPressed()) {
//			// flags
//		    fTracking = true;
//		    fStartTrackSegment = false;
//
//		    // Default : go ahead
//		    icCode = 2;
//		    Thread.sleep(30);
//		    r = 0;
//
//		    // Go go go
//		    Car.forward(SPEED, SPEED);
//
//		    // Boucle principale de parcours de ligne
//		    while (!Button.ESCAPE.isPressed()) {
//		      lL = lsL.getNormalizedLightValue();
//		      lR = lsR.getNormalizedLightValue();
//
//		      ld = lL-lR-ldMid;
//
//		     
//		      // Controle des moteurs pour le suivi de ligne
//		      // utilisation de la méthode PWN en utilisant des accumulateurs, lorsqu'un dépasse la limite
//		      // de l'intervalle, on arrête le moteur pour ce côté.
//		      ldA += ld;
//
//
//
//		      if (icCode == 2) {
//		        // Track full speed, not seen intersection yet
//
//
//		        sInc = r+SPEED_START;
//		        if (sInc > SPEED_RANGE)
//		          sInc = SPEED_RANGE;
//		      }
//
//		      // Motor control to track line
//		      if (ldA > ldRange) {
//		        Car.forward(0, SPEED);
//		        ldA -= ldRange;
//		      } else if (ldA < -ldRange) {
//			    Car.forward(SPEED, 0);
//		        ldA += ldRange;
//		      } else {
//		        // Control speed only while both motors are together
//		        sA += sInc;
//		        if (sA > SPEED_RANGE) {
//		          sA -= SPEED_RANGE;
//		          Car.forward(SPEED, SPEED);
//		        } else {
//		          Car.stop();
//		        }
//		      }
//		      Thread.sleep(10);
//		    }
//		  }

	}
	
	public void followLineTacho(int tachoCount)
	{
		int pos;
		int midVal; 
		float timer = System.currentTimeMillis();
		int previousState = NONE;
		boolean stateChanged = false;
		
		Motor ml = new Motor(MotorPort.B);
		ml.resetTachoCount();

		while (!Button.ESCAPE.isPressed() && ml.getTachoCount() <= tachoCount) {
			  pos = getlinePos();
			  midVal = getMidVal();
			 
			  il.log(new Object[]{pos, ml.getTachoCount()});
	
			  
			  LCD.drawInt(lMLowThresh, 10, 2);
			  
			  if(lMLowThresh != -1 && midVal < lMLowThresh )
			  {
				  Car.forward(SPEEDHIGH, SPEEDHIGH);
			  } else {
				  if(pos > 3 )
				  {
					  // turn right
					  Car.forward(SPEEDHIGH, SPEEDLOW);
					  if(previousState != RIGHT)
					  {
						  stateChanged = true;
						  timer = System.currentTimeMillis();
					  } else {
						  stateChanged = false;
					  }
					  previousState = RIGHT;
				  } else {
					  if(pos < 3)
					  {
						  // turn left
						  Car.forward(SPEEDLOW, SPEEDHIGH);
						  if(previousState != LEFT)
						  {
							  stateChanged = true;
							  timer = System.currentTimeMillis();
						  } else {
							  stateChanged = false;
						  }
						  previousState = LEFT;
					  } else {
						  Car.forward(SPEEDHIGH, SPEEDHIGH);
						  
					  }
				  }
			  }
			  
		  }
		Car.stop();
	}
	
	private int getAverage(Queue queue) {
		int sum = 0, i;
		for(i = 0; i<queue.size(); i++)
		{
			sum = sum + (Integer) queue.elementAt(i);
		}
		return sum/(i);
	}
	
	private void moveForwardTacho(int tachoCount) {
		Motor ml = new Motor(MotorPort.B);
		ml.resetTachoCount();
		while (!Button.ESCAPE.isPressed() && ml.getTachoCount() <= tachoCount) {
			Car.forward(SPEEDHIGH, SPEEDHIGH);		
		}
		Car.stop();
	}

	public static void main (String[] aArg)
	  throws Exception
  	{
		int lL = 850, lM = 800, lR = 800;

		  Main prg = new Main();
		  TurnHandler th = TurnHandler.getInstance();
//		  th.setDebugModeEnabled(true);
		  
		  //prg.calibrate();
		  prg.setC();
		  
		  while(!Button.LEFT.isPressed()){}
		  Thread.sleep(1000);
		  prg.setTiltOffset();
		  
		  Stopwatch stopwatch = new Stopwatch();
		  
		  prg.moveForwardTacho(200);
		  
		  LCD.clear();
		  prg.trackLineSegment();
		  
		  //while(!Button.ENTER.isPressed()){}		  
		  th.Turn(Direction.RIGHT, lL, lM, lR);
		  //while(!Button.ENTER.isPressed()){}
		  prg.trackLineSegment();
		  //while(!Button.ENTER.isPressed()){}
		  th.Turn(Direction.LEFT, lL, lM, lR);
		  //while(!Button.ENTER.isPressed()){}
		  prg.trackLineSegment();
		  //while(!Button.ENTER.isPressed()){}
		  prg.followLineTacho(400);
		  //while(!Button.ENTER.isPressed()){}
		  th.Turn180(lL, lM, lR);
		  //while(!Button.ENTER.isPressed()){}
		  prg.trackLineSegment();
		  //while(!Button.ENTER.isPressed()){}
		  th.Turn(Direction.RIGHT, lL, lM, lR);
		  //while(!Button.ENTER.isPressed()){}
		  prg.trackLineSegment();
		  //while(!Button.ENTER.isPressed()){}
		  th.Turn(Direction.LEFT, lL, lM, lR);
		  //while(!Button.ENTER.isPressed()){}
		  prg.trackLineSegment();
		  prg.moveForwardTacho(300);
		  
		  LCD.clear();
		  LCD.drawString("Time elapsed :", 0, 1);
		  LCD.drawInt(stopwatch.elapsed(), 0, 2);
		  Thread.sleep(100000);

	     
	     LCD.clear();
	     LCD.drawString("Program stopped", 0, 0);
	     LCD.refresh();
	     Thread.sleep(500);
	   }
}