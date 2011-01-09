package Units;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.nxt.addon.CompassSensor;
import Common.IRSeekerV2;
import Common.IRSeekerV2.Mode;

public class LocalRemote { 
  private UltrasonicSensor sonic = new UltrasonicSensor(SensorPort.S3); 
  private IRSeekerV2 ir = new IRSeekerV2(SensorPort.S1, Mode.AC); 
  private CompassSensor cp = new CompassSensor(SensorPort.S2);
  private Motor motor = new Motor(MotorPort.A);
  
//  Java exception
//  class : 16
//  method : 199
//  PC 17503
  
  public void run() { 
    // Create thread, make it a daemon and start it 
    RemoteControl remote = new RemoteControl(); 
    Radar radar = new Radar(motor, sonic);
    IRRadar irRadar = new IRRadar(ir);
    Compass compass = new Compass(cp);
    remote.setDaemon(true); 
    remote.start(); 
    radar.start();
    irRadar.start();
    compass.start();

    while(!Button.ESCAPE.isPressed()) {} 
    radar.stop();
    irRadar.stop();
    remote.stop();
    //Wait for the radar returning to its starting position
    while(!radar.isDoneRunning()) {}
    System.exit(1);
  } 

  public static void main(String[] args) { 
	  new LocalRemote().run();
  } 


}