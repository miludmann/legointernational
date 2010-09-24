package Common;

import java.io.DataOutputStream;
import java.io.IOException;

import lejos.nxt.LCD;
import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;

/**
 * 
 * @author  Anders Dyhrberg
 * @version 1.01.00
 * 
 */
public class BluetoothLogger extends AbstractLogger
{
	protected BTConnection m_btc = null;
    //protected DataInputStream m_dis = null;
    protected DataOutputStream m_dos = null;
    
	public BluetoothLogger()
	{
		//Blocking call waiting for PC to connect.
		m_btc = Bluetooth.waitForConnection();
		m_dos = m_btc.openDataOutputStream();
		
		m_stringBuilder = new StringBuilder();
	}
	
	@Override
	public void stream(StringBuilder sb) {
		try {
			m_dos.writeBytes(sb.toString());
			m_dos.writeByte('\r');
            m_dos.writeByte('\n');
			
            m_dos.flush();
		}
		catch (IOException e) {
			LCD.drawString(e.getMessage(), 0, 0);
			System.exit(0);
		}
		
		//Cleaning up the string builder contents. Avoiding the overhead of creating new stringbuilder objects. 
        m_stringBuilder.delete(0, m_stringBuilder.length()-1);
	}
	
	@Override
	public void close() {
		 try
        {
			m_dos.flush();
			m_dos.close();	
        }
        catch(IOException e)
        {
            LCD.drawString(e.getMessage(),0,0);
            System.exit(0);
        }		
	}
}
