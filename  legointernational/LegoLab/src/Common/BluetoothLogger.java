package Common;

import java.io.IOException;

import lejos.nxt.LCD;

/**
 * 
 * @author  Anders Dyhrberg
 * @version 1.01.00
 * 
 */
public class BluetoothLogger extends BaseLogger
{
	protected BluetoothHandler m_bth = null;
    
	public BluetoothLogger()
	{
		m_bth = BluetoothHandler.getInstance();		
		m_stringBuilder = new StringBuilder();
	}
	
	
	public void stream(StringBuilder sb) {
		try {
			m_bth.getOutputStream().writeBytes(sb.toString());
			m_bth.getOutputStream().writeByte('\r');
			m_bth.getOutputStream().writeByte('\n');
			
			m_bth.getOutputStream().flush();
		}
		catch (IOException e) {
			LCD.drawString(e.getMessage(), 0, 0);
			System.exit(0);
		}
		
		//Cleaning up the string builder contents. Avoiding the overhead of creating new stringbuilder objects. 
        m_stringBuilder.delete(0, m_stringBuilder.length()-1);
	}
	

	public void close() {
		 try
        {
			m_bth.getOutputStream().flush();
			m_bth.getOutputStream().close();	
        }
        catch(IOException e)
        {
            LCD.drawString(e.getMessage(),0,0);
            System.exit(0);
        }		
	}
}
