package Logger;

abstract class BaseLogger implements ILogger
{
	protected StringBuilder m_stringBuilder;
	
	public void log( int sample )
    {
    	appendLogStart(m_stringBuilder);
    
    	m_stringBuilder.append(sample);
    	
    	stream(m_stringBuilder);
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
	    	
	    	stream(m_stringBuilder);
	}

	public void log( String string )
    {
    	appendLogStart(m_stringBuilder);
    	
    	m_stringBuilder.append(string);

    	stream(m_stringBuilder);
    }
	    
    public void appendLogStart(StringBuilder sb) {
    	m_stringBuilder.append(System.currentTimeMillis());
    	m_stringBuilder.append(':');
    }
    
	public abstract void stream(StringBuilder sb);
	public abstract void close();
	
}
