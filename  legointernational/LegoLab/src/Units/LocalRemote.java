package Units;

import lejos.nxt.*; 
import lejos.nxt.comm.*; 
import lejos.robotics.navigation.TachoPilot;

import java.io.*; 

public class LocalRemote { 
  private TachoPilot pilot = new TachoPilot(5.6f,16.0f,Motor.B, Motor.C,true); 
  private UltrasonicSensor sonic = new UltrasonicSensor(SensorPort.S3); 
  private Motor motor = new Motor(MotorPort.A);
  
  public void run() { 
    // Create thread, make it a daemon and start it 
    Thread remote = new Thread(new RemoteControl()); 
    Thread radar = new Thread(new Radar(motor, sonic));
    remote.setDaemon(true); 
    remote.start(); 
    radar.start();
    
    // Drive forward 
//    pilot.forward(); 
    
    // Avoid obstacles 
    while(!Button.ESCAPE.isPressed()) { 
    } 
    
    System.exit(1);
  } 

  public static void main(String[] args) { 
	  new LocalRemote().run(); 
  } 


}