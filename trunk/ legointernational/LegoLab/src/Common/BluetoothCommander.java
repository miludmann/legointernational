package Common;

import java.io.IOException;

import lejos.nxt.LCD;

public class BluetoothCommander {
	
	protected BluetoothHandler m_bth;
	protected boolean m_keepRunning = true;
	
	public BluetoothCommander()
	{
		m_bth = BluetoothHandler.getInstance();
	}
	
	public void SendMessage(LIMessage msg)
	{
		try {
		
			m_bth.getOutputStream().write(msg.getEncodedMsg());
			m_bth.getOutputStream().flush();
		
		} catch (IOException e) {
			LCD.drawString(e.getMessage(), 0, 0);
		}
	}
	
	public void StartListen()
	{
		m_keepRunning = true;
		
		while(m_keepRunning)
		{
			try {
				byte[] buffer = new byte[100];
				
				m_bth.getInputStream().read(buffer, 0, 20);
				
				LCD.drawString("IN: " + new String(buffer), 0, 0);
				
			} catch (IOException e) {
				LCD.drawString(e.getMessage(), 0, 0);
			}
			
			try {
				
				Thread.sleep(50); // Just check for commands 20 times pr. second.
			
			} catch (InterruptedException e) {
				LCD.drawString(e.getMessage(), 0, 0);
			} 
		}
	}
	
	public void StopListen()
	{
		m_keepRunning = false;
	}
}
