package MessageComponent;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTInfo;


public class MessageFrameworkTester implements MessageListenerInterface
{
	MessageFrameworkTester m_instance;
	
	private JFrame m_frame;
	
	protected final JPanel buttonPanel = new JPanel(); 
	protected final JButton TestButton = new JButton("Test");
	protected final JTextArea m_logTextArea = new JTextArea();
	protected final JTextArea m_commandTextArea = new JTextArea();

	protected static String title = "NXJHost";
	protected boolean m_btOpen = false;
	
	protected NXTInfo m_info = new NXTInfo(NXTCommFactory.BLUETOOTH, "NXT", "001653007B78");  //School "00165309782B"
	
	public static void main(String args[]) {
		try	{
			MessageFrameworkTester m_instance = new MessageFrameworkTester();
			m_instance.run(args);
		} catch (Throwable t) {
			t.printStackTrace();
			System.err.println("Error: " + t.getMessage());
			System.exit(1);
		}
	}
	
	public MessageFrameworkTester()
	{	
		MessageFramwork.getInstance().addMessageListener(this);
		MessageFramwork.getInstance().ConnectToNXT(m_info);
	
		LIMessage msg = new LIMessage(LIMessageType.Command, "Hello World");
		MessageFramwork.getInstance().SendMessage( msg );
	}
	
	public void run(String[] args)  { 
		
		    m_frame = new JFrame(title);
		    
		    Dimension d = new Dimension(800, 600);
		    m_frame.setSize(d);
		    m_frame.setResizable(false);
		
		    WindowListener listener = new WindowAdapter() {
		      public void windowClosing(WindowEvent w) {
		    	MessageFramwork.getInstance().close();
		      }
		    };		    
		    
		    m_frame.addWindowListener(listener);
		    
		    TestButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ae) {
		    		
		    	m_commandTextArea.append("Sending test msg to Brick");
		    	
		    	LIMessage msg = new LIMessage(LIMessageType.Command, "TEST MESSAGE");
		    	MessageFramwork.getInstance().SendMessage(msg);
		    	
		    	m_commandTextArea.append("Sent");
			    
		      }
		    });
		    
		    buttonPanel.add(TestButton);
		    
		    m_logTextArea.setEditable(false);
		    final JScrollPane logPane = new JScrollPane(m_logTextArea);
			 
			m_commandTextArea.setEditable(false);
			final JScrollPane commandPane = new JScrollPane(m_commandTextArea);
			
			final JPanel textPanel = new JPanel(new GridLayout(1,2));
			textPanel.add(logPane);
			textPanel.add(commandPane);
			
			m_frame.getContentPane().add(new JScrollPane(buttonPanel), BorderLayout.NORTH);
		    m_frame.getContentPane().add(new JScrollPane(textPanel), BorderLayout.CENTER);		
		    
		     //m_frame.pack();
		    m_frame.setVisible(true);
	}

	@Override
	public void recievedNewMessage(LIMessage msg) {
		System.out.println("Recieved: " + new String(msg.getEncodedMsg()) );		
	}	
}