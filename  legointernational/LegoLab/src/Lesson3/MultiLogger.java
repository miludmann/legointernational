package Lesson3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import lejos.nxt.LCD;
/**
 * 
 * @author  Anders Dyhrberg
 * @version 1.00.00
 * 
 */
public class MultiLogger 
{
    private File m_file;
    private FileOutputStream m_fileStream;
    private StringBuilder m_stringBuilder;
    
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
        catch(IOException e)
        {
            LCD.drawString(e.getMessage(),0,0);
            System.exit(0);
        }
        
        m_stringBuilder = new StringBuilder();
    }
	
    public void log( int sample )
    {
    	appendLogStart(m_stringBuilder);
    
    	m_stringBuilder.append(sample);
    	
    	streamToFile(m_stringBuilder);
    }
    
    public void log( Object objs[] )
    {
    	appendLogStart(m_stringBuilder);
    	
    	for(int i=0; i<objs.length; i++)
    	{
    		m_stringBuilder.append(objs[i].toString());
    		
    		//Append ',' after each value, except the last one 
    		if(i<objs.length-1)
    			m_stringBuilder.append(',');
    	}
    	
    	streamToFile(m_stringBuilder);
    }
    
    public void log( String string )
    {
    	appendLogStart(m_stringBuilder);
    	
    	m_stringBuilder.append(string);

    	streamToFile(m_stringBuilder);
    }
    
    private void appendLogStart(StringBuilder sb) {
    	m_stringBuilder.append(System.currentTimeMillis());
    	m_stringBuilder.append(':');
    }
        
    //Util function to stream the prepared log strings to the file
    private void streamToFile(StringBuilder sb)
    {
    	try
        {
            for(int i=0; i<m_stringBuilder.length(); i++) {
                m_fileStream.write((byte) m_stringBuilder.charAt(i));
            }
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
