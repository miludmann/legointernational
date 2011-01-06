package Lesson4;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import Logger.MultiLogger;

public class Sejway {

	// PID constants
	final float KP = 1.556f;
	final float KI = 0.222f;
	final float KD = 1.833f;

//	final float KP = 1.7f;
//	final float KI = 0.222f;
//	final float KD = 2.3f;

	
	// Global vars:
	int offset;
	int prev_error;
	float int_error;
	
	protected static MultiLogger logger = new MultiLogger("bal_log.txt");
	
	LightSensor ls;
	
	public Sejway() {
		ls = new LightSensor(SensorPort.S2, true);
		Motor.A.regulateSpeed(false);
		Motor.C.regulateSpeed(false);
	}
	
	public void getBalancePos() {
		// Wait for user to balance and press orange button
		while (!Button.ENTER.isPressed()) {
			// NXTway must be balanced.
			offset = ls.readNormalizedValue();
			LCD.clear();
			LCD.drawInt(offset, 2, 4);
			LCD.refresh();
		}
	}
	
	public void pidControl() {
		while (!Button.ESCAPE.isPressed()) {
			
			int normVal = ls.readNormalizedValue();
			
			// Proportional Error:
			int error = normVal - offset;
			// Adjust far and near light readings:
			//if (error < 0) error = (int)(error * 1.8F);
			
			// Integral Error:
			int_error = ((int_error + error) * 2)/3;
			
			// Derivative Error:
			int deriv_error = error - prev_error;
			prev_error = error;
			
			int pid_val = (int)(KP * error + KI * int_error + KD * deriv_error);
			
			if (pid_val > 100)
				pid_val = 100;
			if (pid_val < -100)
				pid_val = -100;

			// Power derived from PID value:
			int power = Math.abs(pid_val);
			power = 55 + (power * 45) / 100; // NORMALIZE POWER
			Motor.A.setPower(power);
			Motor.C.setPower(power);

			if (pid_val > 0) {
				Motor.A.forward();
				Motor.C.forward();
			} else {
				Motor.A.backward();
				Motor.C.backward();
			}
		}
	}
	
	public void shutDown() {
		// Shut down light sensor, motors
		Motor.A.flt();
		Motor.C.flt();
		ls.setFloodlight(false);
		logger.close();
	}
	
	public static void main(String[] args) {
		Sejway sej = new Sejway();
		sej.getBalancePos();
		sej.pidControl();
		sej.shutDown();
	}

	
	
}