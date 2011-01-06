package MessageFramework;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTInfo;

import MessageFramework.Message;



public class MessageFrameworkTester implements MessageListenerInterface
{
	MessageFrameworkTester m_instance;
	
	private JFrame m_frame;
	
	protected final JPanel buttonPanel = new JPanel(); 
	protected final JButton connectButton = new JButton("Connect");
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
			System.err.println("Error: " + t.getMessage());
			System.exit(1);
		}
	}
	
	public MessageFrameworkTester()
	{
		MessageFramwork.getInstance().addMessageListener(this);
	
		Message msg = new Message(MessageType.Command, "Hello World");
		MessageFramwork.getInstance().SendMessage( msg );
		
		
//		Message result = Message.setEncodedMsg(encoded);
//		System.out.println("Payload: " + result.m_payload.toString());
//		System.out.println("MsgType: " + result.m_msgType);
	}
	
	public void run(String[] args)  { 
		
		    m_frame = new JFrame(title);
		    
		    Dimension d = new Dimension(800, 600);
		    m_frame.setSize(d);
		    m_frame.setResizable(false);
		
		    WindowListener listener = new WindowAdapter() {
		      public void windowClosing(WindowEvent w) {
		    	//nxtCommand.close();
		    	// System.exit(0); if close throws exception
		      }
		    };
		    
		    
		    m_frame.addWindowListener(listener);
		    
		    //Read command line variables
		    //String name = commandLine.getOptionValue("n");
		    //boolean blueTooth = commandLine.hasOption("b");
		    //boolean usb = commandLine.hasOption("u");
		    //String address = commandLine.getOptionValue("d");
		    
		    connectButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ae) {
//--->		    	m_btOpen = ConnectToNXT(m_info);
		    	
		    	if (!m_btOpen) {
			    	JOptionPane.showMessageDialog(m_frame, "Failed to connect");
			    	m_commandTextArea.append("FAILED connecting to " + m_info.name );
			    }
			    else {
			    	connectButton.setEnabled(false);
			    	m_commandTextArea.append("Connection to " + m_info.name );
			    	
//--->			    	m_reader.start();

			    	//			    	for(int i=0; i<10; i++)
//			    	{
//			    		try {
//
//			    			String result =  Character.toString( m_dis.readChar() );
//							m_logTextArea.append(result );
//							System.out.println(result);
//							
//							//m_logTextArea.repaint();
//							
//							//Thread.currentThread();
//							Thread.sleep(500);
//						} catch (IOException e) {
//							e.printStackTrace();
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//			    	}
			    }
		      }
		    });
		    
		    buttonPanel.add(connectButton);
		    
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
	public void recievedNewMessage(Message msg) {
		System.out.println("Recieved: " + new String(msg.getEncodedMsg()) );		
	}
	

	
	
	
	
}

//
//public class NXJBrowser {
//  public static final int MAX_FILES = 30;
//  private NXTCommand nxtCommand;

//  private NXJBrowserCommandLineParser fParser;
//  

//  
//  ...run
//    nxtCommand = NXTCommand.getSingleton();
//    
//    CommandLine commandLine = fParser.parse(args);
//    
//   
//	
//    int protocols = 0;
//    
//	if (blueTooth) protocols |= NXTCommFactory.BLUETOOTH;
//	if (usb) protocols |= NXTCommFactory.USB;
//	
//	if (protocols == 0) protocols = NXTCommFactory.USB | NXTCommFactory.BLUETOOTH;
//	
//	NXTConnector conn = new NXTConnector();
//	conn.addLogListener(new ToolsLogger());
//	
//	final NXTInfo[] nxts = conn.search(name, address, protocols);
//	
//    if (nxts.length == 0) {
//        System.err.println("No NXT found - is it switched on and plugged in (for USB)?");
//        System.exit(1);
//    }
//    
//    // Display a list of NXTs
//    
//    final NXTConnectionModel nm = new NXTConnectionModel(nxts, nxts.length);
//    
//    
//    
//    
//
//   
//  }
//  
//  private void showFiles(final JFrame frame, NXTInfo nxt) {
//	  
//	try {
//		frame.setTitle(title + " : " + nxtCommand.getFriendlyName());
//	} catch (IOException ioe) {
//		showMessage("IOException getting friendly name");
//	}
//	
//    frame.getContentPane().removeAll();
//
//    final ExtendedFileModel fm = new ExtendedFileModel(nxtCommand);
//      
//    final JTable table = new JTable(fm);
//    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//    
//    TableColumn col = table.getColumnModel().getColumn(0);
//    col.setPreferredWidth(300);
//
//    final JScrollPane tablePane = new JScrollPane(table);
//    tablePane.setPreferredSize(new Dimension(605, 500));
//
//    frame.getContentPane().add(tablePane, BorderLayout.CENTER);
//
//    JPanel buttonPanel = new JPanel();
//
//    JButton deleteButton = new JButton("Delete Files");
//    JButton uploadButton = new JButton("Upload file");
//    JButton downloadButton = new JButton("Download file");
//    JButton runButton = new JButton("Run program");
//    JButton defragButton = new JButton("Defrag");
//    JButton nameButton = new JButton("Set Name");
//    
//    buttonPanel.add(deleteButton);
//    buttonPanel.add(uploadButton);
//    buttonPanel.add(downloadButton);
//    buttonPanel.add(runButton);
//    buttonPanel.add(defragButton);
//    buttonPanel.add(nameButton);
//
//    frame.getContentPane().add(new JScrollPane(buttonPanel), BorderLayout.SOUTH);
//
//    deleteButton.addActionListener(new ActionListener() {
//      public void actionPerformed(ActionEvent ae) {
//    	frame.setCursor(hourglassCursor);
//        
//    	try {
//	        for(int i=0;i<fm.getRowCount();i++) {
//	          Boolean b = (Boolean) fm.getValueAt(i,4);
//	          boolean deleteIt = b.booleanValue();
//	          String fileName = (String) fm.getValueAt(i,0);
//	          if (deleteIt) {
//	            //System.out.println("Deleting " + fileName);
//	            nxtCommand.delete(fileName); 
//		        fm.delete(fileName, i);
//		        i--;
//		        table.invalidate();
//		        tablePane.revalidate();   
//	          }
//	        }
//        } catch (IOException ioe) {
//        	showMessage("IOException deleting files");
//        }
//        frame.setCursor(normalCursor);
//      }
//    });
//
//    uploadButton.addActionListener(new ActionListener() {
//      public void actionPerformed(ActionEvent ae) {       
//        JFileChooser fc = new JFileChooser();
//
//        int returnVal = fc.showOpenDialog(frame);
//        if (returnVal == JFileChooser.APPROVE_OPTION) {
//          frame.setCursor(hourglassCursor);
//          try {
//        	  File file = fc.getSelectedFile();
//        	  if (file.getName().length() > 20) {
//        		  showMessage("File name is more than 20 characters");
//        	  } else {   	
//		          nxtCommand.uploadFile(file, file.getName());
//		          String s = fm.fetchFiles();
//		          if (s != null) throw new IOException();
//		          table.invalidate();
//		          tablePane.revalidate();
//        	  }
//          } catch (IOException ioe) {
//        	  showMessage("IOException uploading file");
//          }
//          frame.setCursor(normalCursor);
//        }
//      }
//    });
//    
//    downloadButton.addActionListener(new ActionListener() {
//      public void actionPerformed(ActionEvent ae) {
//        int i = table.getSelectedRow();
//        if (i<0) return;
//        String fileName = fm.getFile(i).fileName;
//        int size = fm.getFile(i).fileSize;
//        JFileChooser fc = new JFileChooser();
//        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
//        fc.setSelectedFile(new File(fileName)); 
//	    int returnVal = fc.showSaveDialog(frame);
//        if (returnVal == 0) {
//          File file = fc.getSelectedFile();
//          frame.setCursor(hourglassCursor);
//          getFile(file, fileName, size);
//          frame.setCursor(normalCursor);
//        }
//      }
//    });
//
//    defragButton.addActionListener(new ActionListener() {
//        public void actionPerformed(ActionEvent ae) {
//          frame.setCursor(hourglassCursor);
//          try {
//        	  nxtCommand.defrag();
//	          String s = fm.fetchFiles();
//	          if (s != null) throw new IOException();
//	          table.invalidate();
//	          tablePane.revalidate();
//	          tablePane.repaint();
//          } catch (IOException ioe) {
//        	  showMessage("IOException during defrag");
//          }
//          frame.setCursor(normalCursor);
//        }
//      });
//    
//    runButton.addActionListener(new ActionListener() {
//        public void actionPerformed(ActionEvent ae) {
//      	  int i = table.getSelectedRow();
//      	  if (i<0) return;
//      	  String fileName = fm.getFile(i).fileName;
//          try {
//        	  runProgram(fileName);
//        	  System.exit(0);
//          } catch (IOException ioe) {
//        	  showMessage("IOException running program");
//          }
//        }
//      });
//    
//    nameButton.addActionListener(new ActionListener() {
//        public void actionPerformed(ActionEvent ae) {
//          String name = JOptionPane.showInputDialog(frame,"New Name");
//          
//          if (name != null && name.length() <= 16) {
//        	  frame.setCursor(hourglassCursor);        
//              try {
//            	  nxtCommand.setFriendlyName(name);
//            	  frame.setTitle(title + " : " + name);
//              } catch (IOException ioe) {
//            	  showMessage("IOException setting friendly name");
//              }
//        	  frame.setCursor(normalCursor);
//          }
//        }
//      });
//
//
//    frame.pack();
//    frame.setVisible(true);
//  }
//
//
//  public void getFile(File file, String fileName, int size) {
//    FileOutputStream out = null; 
//    int received = 0;
//
//    try {
//      out = new FileOutputStream(file);
//    } catch (FileNotFoundException e) {}
//
//    try {  	
//      nxtCommand.openRead(fileName);
//      do
//      {
//        byte [] data = nxtCommand.readFile((byte) 0,(size-received < 51 ? size-received : 51));
//        //System.out.println("Received " + data.length + " bytes");
//        received += data.length;
//      
//        out.write(data);
//
//      } while (received < size);
//
//      //System.out.println("Received " + received + " bytes");
//      nxtCommand.closeFile((byte) 0);
//      out.close();
//    } catch (IOException ioe) {
//    	showMessage("IOException downloading file");
//    }
//  }
//
//  public void runProgram(String fileName) throws IOException {
//    nxtCommand.startProgram(fileName);
//  }
//  
//  public void showMessage(String msg) {
//	  JOptionPane.showMessageDialog(frame, msg);
//  }
//}
//
//
