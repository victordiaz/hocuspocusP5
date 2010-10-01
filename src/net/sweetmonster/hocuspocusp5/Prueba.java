package net.sweetmonster.hocuspocusp5;


import processing.core.PApplet;

public class Prueba extends PApplet {

	/** 
	 * 
	 * 
	 * 
	 */ 

	private static final long serialVersionUID = 122988805059255114L;
	Hocuspocus manager;
	private int qq2 = 12; 
	
	//@PruebaAnnotation(name = "txt1")
	//int var1 = 10; 
	//@PruebaAnnotation(name = "txt2")
	//int var2; 
	
	//@PruebaAnnotation float qq1; 

	public static void main(String args[]) {
		PApplet.main(new String[] { "--present",
				net.sweetmonster.hocuspocusp5.Prueba.class.getName() });
	}

	public void setup() { 

		size(300, 500, JAVA2D);
		background(0);
		noStroke();
		smooth();

		frameRate(25);

		textMode(SCREEN);

		textFont(createFont("Arial", 12));


		manager = new Hocuspocus(this); 
		//manager.addObject(this); 
		manager.addObject(this); 
		manager.addValue(qq2 ); 
		//manager.addObject(manager); 
		//manager.addObject(Prueba.class); 

		println(qq2); 
	
	} 

	public void draw() {
		background(0);
		stroke(255);

		fill(255); 
		
		//println(qq1); 

	} 

	public void keyReleased() {


		
	} 	


}
