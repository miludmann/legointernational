package Lesson3;

import lejos.nxt.*;
import lejos.util.Timer;
import lejos.util.TimerListener;

import java.io.*;
import java.util.Date;
/**
 * 
 * @author  Anders Dyhrberg based on Datalogger from Ole Caprani
 * @version 2.09.08 Ole Caparni base verson
 * @version 1.00.00 Anders Dyhrberg
 * 
 */
public class MultiDataLogger 
{
    private File f;
    private FileOutputStream fos;
    //private int itemsOnThisLine = 0; 
    //private int itemsPerLine = 20;
    private StringBuilder sb; 
    
    public MultiDataLogger (String fileName)
    {
        try
        {	        
            f = new File(fileName);
            if( ! f.exists() )
            {
                f.createNewFile();
            }
            else
            {
                f.delete();
                f.createNewFile();
            }
             
            fos = new  FileOutputStream(f);
            
        }
        catch(IOException e)
        {
            LCD.drawString(e.getMessage(),0,0);
            System.exit(0);
        }
    }
	
    public void writeSample( int samples[] )
    {
    	sb = new StringBuilder(); //It would be better to clean up and reuse. But with what function?

    	//building string;
    	sb.append(System.currentTimeMillis());
    	sb.append(":");

    	for(int i=0; i<samples.length; i++)
    	{
    		sb.append(samples[i]);
    		
    		if(i<samples.length-1)
    			sb.append(',');
    	}
    	
    	//new line
    	sb.append('\r');
    	sb.append('\n');
    	
        //Integer sampleInt = new Integer(sample); interesting ???
        //String sampleString = sampleInt.toString();

        try
        {
            for(int i=0; i<sb.length(); i++)
                fos.write((byte) sb.charAt(i));
           
	        //itemsOnThisLine = itemsOnThisLine + 1;
//	        if ( itemsOnThisLine  == itemsPerLine )
//	        {
//                // New line
//                fos.write((byte)('\r'));
//                fos.write((byte)('\n'));
//				
//                itemsOnThisLine = 0;
//            }
        }
        catch(IOException e)
        {
            LCD.drawString(e.getMessage(),0,0);
            System.exit(0);
        } 
    }
	
    public void close()
    {
        try
        {
        	fos.flush();
            fos.close();
        }
        catch(IOException e)
        {
            LCD.drawString(e.getMessage(),0,0);
            System.exit(0);
        }		 
    }
}
