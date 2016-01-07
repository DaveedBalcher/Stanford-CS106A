/*
 * File: GrowingCircles.java
 * ========================================================
 * A program that allows the user to click on the canvas and
 * add/grow circles. If the user clicks on a blank space, a
 * new circle is added; otherwise the circle is enlarged
 */
import acm.program.*;
import acm.graphics.*;

import java.awt.event.*;

public class GrowingCircles extends GraphicsProgram {
	
	/* Initial size of the circles we create */
	private static final int DEFAULT_SIZE = 50;
	
	/* Size to grow the circles upon each click */
	private static final int GROWTH_SIZE = 10;
	
	public void run() {
		//Once we add the mouse listener, it does all of the work
		addMouseListeners();
	}
	
	public void mouseClicked(MouseEvent evt) {
		//Get the location of the click
		int x = evt.getX();
		int y = evt.getY();
		
		//Get the GObject at that location; cast it to a GOval given that
		//a) we know it's a GOval and b) we want to change its size
		GOval circle = (GOval)getElementAt(x, y);
		
		if (circle == null) {
			//No GOval, so go ahead and add one
			GOval newCircle = new GOval(
				x - DEFAULT_SIZE/2,
				y - DEFAULT_SIZE/2,
				DEFAULT_SIZE,
				DEFAULT_SIZE
			);
			add(newCircle);
		} else {
			//We have a circle, so increase its size
			double diameter = circle.getWidth();
			circle.setSize(diameter + GROWTH_SIZE, diameter + GROWTH_SIZE);
			//Not necessary, but we can re-center the circle
			circle.move(-GROWTH_SIZE / 2, -GROWTH_SIZE / 2);
		}
	}
}
