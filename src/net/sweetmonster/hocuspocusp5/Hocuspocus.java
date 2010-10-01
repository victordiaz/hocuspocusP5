package net.sweetmonster.hocuspocusp5;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;

import oscP5.OscMessage;
import oscP5.OscP5;
import processing.core.PApplet;

public class Hocuspocus {


	private Class cls; 
	
	static int P5GUI = 0; 
	static int EXTGUI = 1; 
	static int OSC = 2; 
	static int UDP = 3; 
	static int TCP = 4; 
	static int XMPP = 5; 
	static int ARDUINO = 6; 
	static int AUTODISCOVERABLE = 8; 
	
	private int inOSCPort; 
	private int outOSCPort; 
	private int autoDiscoveryPort; 
	
	private HocuspocusDiscovery autodiscovery; 
	private OscP5 osc; 
	//private UDP udp; 
	//private TCP tcp; 
	//private XMPP xmpp; 
	
	
	private boolean useAutoDiscovery; 
	private boolean useOSC; 
	private boolean useUDP; 
	private boolean useTCP; 
	private boolean useXMPP; 
	private boolean useARDUINO; 
	private HashMap<String, Object> objects; 
	
	

	Hocuspocus(PApplet p) { 
		inOSCPort = 8000; 
		outOSCPort = 8001; 
		autoDiscoveryPort = 8002; 
		
		autodiscovery = new HocuspocusDiscovery(autoDiscoveryPort); 
		osc = new OscP5(this, inOSCPort); 
		objects = new HashMap<String, Object>(); 
		
	//	autodiscovery.setData(data); 
	//	autodiscovery.start(); 
		
		// PackageUtils.getClasseNamesInPackage(jarName, packageName)
		// System.out.println("qq " +
		// java.lang.Class.class.getClasses().toString());
	} 
	
	/* incoming osc message are forwarded to the oscEvent method. */
	public void oscEvent(OscMessage theOscMessage) { 
	  /* print the address pattern and the typetag of the received OscMessage */
	  System.out.print("### received an osc message.");
	  System.out.print(" addrpattern: "+theOscMessage.addrPattern()); 
	  String var = theOscMessage.addrPattern().substring(1); 
	  System.out.println(" typetag: "+theOscMessage.typetag()); 
	  
	  //TODO: add type check 
	  //modify variables values 
	  if (objects.containsValue(var) == true) { 
		  Object q = objects.get(var); 
		  System.out.println("qq"); 
		  
		  try { 
			q.getClass().getDeclaredField(var).set(q, theOscMessage.get(0));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		  
	  }
	  
	  
	}


	public void addObject(Object obj) { 
		
		cls = obj.getClass(); 
		
		System.out.println("----------- "); 
		System.out.println("Class" + cls.getName() + " " + cls.getSimpleName()); 

		objects.put(cls.getSimpleName(), obj); 
		extractInfoObject(obj);
	} 
	
	//TODO: mirar como puedo acceder por referencia a un valor en java 
	public void addValue(Object obj) { 
		cls = obj.getClass(); 
		
		System.out.println("----------- "); 
		System.out.println("Class" + cls.getName() + " " + cls.getSimpleName() + " " + obj.toString()); 
	
		//obj.getClass(). obj = 15; 
		//obj.getClass().
		
		
		//objects.put(cls.getSimpleName(), obj); 
		//cls.get
		//extractInfoObject(obj); 
		
	}

	private void extractInfoObject(Object obj) { 

		// ClassShow.dump(soundManager, 1);
		// System.out.println(cls.getClass().getAnnotation(PruebaAnnotation.class));
		// soundManager.getClass(). 
		//System.out.println(obj.getClass().getDeclaredClasses()); 
		
		
		//searching fields with annotations 
		Field attr[] = cls.getDeclaredFields(); 
		for (int i = 0; i < attr.length; i++) { 
			String nombre = attr[i].getName(); 
			
			//foreach annotation 
			Annotation a[] = attr[i].getAnnotations();
			for (int j = 0; j < a.length; j++) {
				System.out.println("found variables: " + attr[i]); 
				System.out.println("annotation: " + a[j]); 

				System.out.println("1: " + attr[i].getName()); 
				System.out.println("2: " + attr[i].getType()); 
				
//				try {
//					attr[i].set(obj, 12);
//				} catch (IllegalArgumentException e) { 
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IllegalAccessException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} 
//				
				
				try {
					System.out.println("field value: " + attr[i].get(obj)); 
				} catch (IllegalArgumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
//				
//				try { 
//			//		System.out.println("5: " + obj.getDeclaredField("var1").get(obj)); 
//					System.out.println("5: " + obj.getClass().getField("var1")); 
//				} catch (SecurityException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (NoSuchFieldException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}  
				
				System.out.println("name: " + ((PruebaAnnotation) a[j]).name()); 
				System.out.println("min: " + ((PruebaAnnotation) a[j]).min()); 
				System.out.println("max: " + ((PruebaAnnotation) a[j]).max()); 
				

				/*
				 * try { String nombre = attr[i].getName();
				 * 
				 * nombre = String.valueOf( nombre.charAt(0)).toUpperCase() +
				 * nombre.substring(1); System.out.println("Variable name: " +
				 * nombre);
				 * 
				 * //invoke getters //Method getter =
				 * soundManager.getClass().getMethod("get"+nombre); //String
				 * value = getter.invoke(c, new Object[0]).toString();
				 * 
				 * //System.out.println("Valor del campo: "+value); }
				 * catch(Exception e) { e.printStackTrace(); }
				 */

			}

		}

	}

}
