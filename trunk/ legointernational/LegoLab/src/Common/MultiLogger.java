package Common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import lejos.nxt.LCD;
/**
 * 
 * @author  Anders Dyhrberg
 * @version 1.01.00
 * 
 */
public class MultiLogger extends AbstractLogger 
{
	//File Logging
	protected File m_file;
    protected FileOutputStream m_fileStream;
    
    public MultiLogger (String fileName)
    {
        try
        {	        
            m_file = new File(fileName);
            if( ! m_file.exists() )
            {
                m_file.createNewFile();
            }
            else
            {
                m_file.delete();
                m_file.createNewFile();
            }
             
            m_fileStream = new  FileOutputStream(m_file);
            
        }
        catch(IOException x)
        {
            LCD.drawString(x.getMessage(),0,0);
            System.exit(0);
        }
        
        m_stringBuilder = new StringBuilder();
    }
        
    //Util function to stream the prepared log strings to the file
    @Override
	public void stream(StringBuilder sb) {
    	try
        {
            for(int i=0; i<m_stringBuilder.length(); i++) {
                m_fileStream.write((byte) m_stringBuilder.charAt(i));
            }
            
            //New line
            m_fileStream.write('\r');
            m_fileStream.write('\n');
        }
        catch(IOException e)
        {
            LCD.drawString(e.getMessage(),0,0);
            System.exit(0);
        }
        
        //Cleaning up the string builder contents. Avoiding the overhead of creating new stringbuilder objects. 
        m_stringBuilder.delete(0, m_stringBuilder.length()-1);
    }
	
    public void close()
    {
        try
        {
        	m_fileStream.flush();
            m_fileStream.close();
        }
        catch(IOException e)
        {
            LCD.drawString(e.getMessage(),0,0);
            System.exit(0);
        }		 
    }
}
