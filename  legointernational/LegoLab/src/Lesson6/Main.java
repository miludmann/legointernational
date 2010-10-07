package Lesson6;

import Common.Car;
import lejos.nxt.*;
import lejos.nxt.addon.RCXLightSensor;
import lejos.nxt.addon.TiltSensor;

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
	 
	private MotorPort ml = MotorPort.B;
	private MotorPort mr = MotorPort.C;
	private RCXLightSensor lsL, lsM, lsR;
	
	private int C = 78;
	private static int EOL_MINOBSCOUNT = 5;
	private static int SPEEDHIGH = 100;
	private static int SPEEDLOW = 60;

	
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
	
	private static int NONE = 0;
	private static int LEFT = -1;
	private static int RIGHT = 1;
	
	private int tiltOffset;
		  
	public Main()
	{
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
		while(!Button.ENTER.isPressed()){}
		int lLeft = lsL.getNormalizedLightValue();
		int lRight = lsR.getNormalizedLightValue();
		C = lRight - lLeft;
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
		setTiltOffset();
		
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
		  
		  LCD.clear();
		  LCD.drawString("lMLow :", 0, 2);
		  LCD.drawString("Midval :", 0, 3);
		  
		  timer = System.currentTimeMillis();
		  
		  while (!Button.ESCAPE.isPressed()) {
			  pos = getlinePos();
			  midVal = getMidVal();
			  tiltv = tilt.getXTilt();
			  

			  
			  if(Math.abs(tiltv -tiltOffset) >= 6)
			  {
				  tiltNb++;
				  if(tiltNb > 50)
				  {
					  Car.stop();
					  break;
				  }
			  } else {
				  tiltNb--;
			  }
			  
			  LCD.drawInt(lMLowThresh, 10, 2);
			  LCD.drawInt(midVal, 10, 3);
			  
			  if(!stateChanged && Math.abs(System.currentTimeMillis() - timer) >= 500)
			  {
				  if(previousState == LEFT)
				  {
					  while(Math.abs(pos) > 5)
					  {
						  pos = getlinePos();
						  Car.forward(SPEEDHIGH, SPEEDLOW);
					  }
				  } else {
					  if(previousState == RIGHT)
					  {
						  while(Math.abs(pos) > 5)
						  {
							  pos = getlinePos();
							  Car.forward(SPEEDLOW, SPEEDHIGH);
						  }
					  } else {
//						  while(Math.abs(pos) > 5)
//						  {
//							  pos = getlinePos();
//							  Car.backward(SPEEDHIGH, SPEEDHIGH);
//						  }
					  }
				  }
				  timer = System.currentTimeMillis();
			  }

			  
			  if(lMLowThresh != -1 && midVal < lMLowThresh )
			  {
				  Car.forward(SPEEDHIGH, SPEEDHIGH);
			  } else {
				  if(pos > 5)
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
					  if(pos < 5)
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
			  
		  Thread.sleep(10);
		  }

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
	
	public static void main (String[] aArg)
	  throws Exception
  	{
		  Main prg = new Main();
		  //prg.calibrate();
		  prg.setC();
		  prg.trackLineSegment();
		  
		
	     while (! Button.ESCAPE.isPressed())
	     {
	    	 
	    	 
//		     LCD.refresh();
		     
		     
		     /*
		     if ( sensor.black() )
		     {
		    	// Car.forward(power, 0);
		     	LCD.drawString(black,1,4);
		     }
		     else {
		         // Car.forward(0, power);
			     LCD.drawString(white,1,4);
		
		     }
		     */
		     Thread.sleep(10);
	     }
	     
	     LCD.clear();
	     LCD.drawString("Program stopped", 0, 0);
	     LCD.refresh();
	     Thread.sleep(500);
	   }
}