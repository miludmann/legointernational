package MessageFramework;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTConnector;
import lejos.pc.comm.NXTInfo;


public class MessageFramwork {
	
	private static MessageFramwork m_instance = new MessageFramwork();
	private Object m_guard;
	
	protected ArrayList<MessageListenerInterface> m_messageListeners; 	
	protected NXTConnector m_connecter;
	protected InputStream m_is;
	protected OutputStream m_os;
	protected boolean m_connected;
	
	private Reader m_reader;
	
	private MessageFramwork() {
		
		m_messageListeners = new ArrayList<MessageListenerInterface>();
		m_guard = new Object();
		m_connecter = new NXTConnector();
		m_connected = false;
		
		//instance here
		m_reader = new Reader(); 
    	m_reader.setDaemon(true);
    	m_reader.start(); //Start to listen for incomming messages
	}
	
	public static synchronized MessageFramwork getInstance() {
		return m_instance;
	}
	
	protected boolean ConnectToNXT(NXTInfo info)
	{    
        if (!m_connecter.connectTo(info.name, info.deviceAddress, NXTCommFactory.BLUETOOTH))
        {
            return false;
        }
        m_is = m_connecter.getInputStream();
        m_os = m_connecter.getOutputStream();
        
        //We could implement a handshake to verify the connection before we approve the connection. 
        m_connected = true;
        
        if (m_is == null || m_os == null)
        	return false;
        
        return m_connected;
	}
	
	public void SendMessage(LIMessage msg)
	{
		try  // handshake
        {
			byte[] outgoing = msg.getEncodedMsg();
            
            m_os.write(outgoing);
            m_os.flush();
            
            m_connected = true;
        } catch (IOException e) {
        	System.err.println("SendMessage failed: " + e.getMessage());
        	//m_connected = false;
        }		
	}
	
	public void addMessageListener(MessageListenerInterface msgListener)
	{
		m_messageListeners.add(msgListener);
	}
	
    private class Reader extends Thread
    {
        public void run()
        {
            while (true)
            {              
                if (m_connected)
                {
                    try {
                        int input;
                        while ((input = m_is.read()) >= 0)
                        {
                        	System.out.print((char)input);
                        	
                        	//Notify all listeners of a new message
                        	for(int i=0; i<m_messageListeners.size(); i++)
                        	{
                        		synchronized (m_guard) {
                        			m_messageListeners.get(i).recievedNewMessage(new LIMessage(MessageType.Command, "TEST MESSAGE"));
                        		}
                        	}
                        }
                        close();
                        
                    }
                    catch (IOException e) {
                        close();
                    }
                }               
                Thread.yield();
            }
        }
    }
    
    public void close() {
    	try {
    		if (m_connecter != null) m_connecter.close();
    	} catch (IOException e) {}
    	m_connected = false;
    }
}
