package net.sweetmonster.hocuspocusp5;

public class HocuspocusDiscovery {

	
	XMLServer xmlserver;
	private int port;
	private HocuspocusType data; 
	
	HocuspocusDiscovery(int port) { 
		this.port = port; 
	
	} 
	
	public void start() { 
		System.out.println(port); 
		//xmlserver = new XMLServer(port); 
		//xmlserver.start(); 
		//xmlserver.receive(); 
		//xmlserver.send(); 
		
	} 
	
	public void setData(HocuspocusType data) { 
		this.data = data; 
		generateXML(data); 
		xmlserver.send(); 
	}
	
	private String generateXML(HocuspocusType data) { 
		
		String xml = "qq"; 
		
		
		return xml; 
		
		
	}
	
}
