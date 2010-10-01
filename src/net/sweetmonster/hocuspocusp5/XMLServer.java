package net.sweetmonster.hocuspocusp5;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class XMLServer { 
	
	private int port; 
	private Socket socket; 

	XMLServer(int port) { 
		this.port = port; 
		
		
	} 
	
	public void start() { 
		try {
			InetAddress host = InetAddress.getLocalHost(); 
			socket = new Socket(host.getHostName(), port); 
			//new Sock
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Cannot start the autodiscovery service"); 
		}
	}
	
	public void send() { 
		// Send a message to the client application
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject("<ACCOUNT>" +
					"<NAME>John Doe</NAME>" +
					"<CUSTOMER_ZIP>12345</CUSTOMER_ZIP>" +
					"<CUSTOMER_STREET>1234 Main Street</CUSTOMER_STREET>" +
			"</ACCOUNT>"); 
			
			oos.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
				
	} 
	
	public void receive() { 
		
		
		// Read and display the response message sent by server application
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(socket.getInputStream());
			String message;
			try {
				message = (String) ois.readObject();
				System.out.println("Message: " + message);
				ois.close(); 
			} catch (ClassNotFoundException e) { 
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
}
