package Networking;

public class LIMessage {
	
	public static byte STX = 2;
	public static byte ETX = 3;
	
	protected LIMessageType m_msgType; 
	protected String m_payload;
	
	public LIMessage(LIMessageType msgType, String payload){
		m_msgType = msgType;
		m_payload = payload;
	}
	
	//MSG Example: <STX>0:somepayload<ETX>
	public byte[] getEncodedMsg()
	{
		byte[] result = new byte[4+m_payload.length()];
		
		result[0] = STX; //Start of new frame
		result[1] = (byte)m_msgType.ordinal();
		result[2] = ':';
		
		for(int i=0; i<m_payload.length(); i++)
		{
			result[i+3] = (byte)m_payload.charAt(i);	
		}
		
		result[3+m_payload.length()] = ETX; //End of new frame
		
		return result;		
	}
	
	public static LIMessage setEncodedMsg(byte[] msg) {
		
		LIMessageType msgType = (msg[1]==0?LIMessageType.Command:LIMessageType.Debug);  //  MessageType.values()[msg[0]];  // MessageType.class.getEnumConstants()[msg[0]]; //Need better solution.
		byte[] payloadBytesOnly = new byte[msg.length-4]; //4 since this is <STX>, MegType, :, and ETX, that we remove
		for(int i=0; i<payloadBytesOnly.length; i++)
		{
			payloadBytesOnly[i] = msg[i+3];
		}
		
		String payload = new String(payloadBytesOnly);
		
		return new LIMessage(msgType, payload);
	}
	
	public LIMessageType getMsgType() {
		return m_msgType;
	}

	public void setMsgType(LIMessageType type) {
		m_msgType = type;
	}

	public String getPayload() {
		return m_payload;
	}

	public void setPayload(String m_payload) {
		this.m_payload = m_payload;
	}
}