/*
 * File: ColorfulCircles.java
 * ========================================================
 * A program that simulates a bouncing ball
 */

import java.awt.event.MouseEvent;

import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;



public class ColorfulCircles extends GraphicsProgram {

	public void init() {
		addMouseListeners();
		colorSelection();
	}
	
	private GOval oval;
	
	private int centerX , centerY;
	
	//Declare color rectangles (black, red, yellow)
	private GRect rectA, rectB, rectC;
	
	private void colorSelection() {
		//Add RectA
		GRect rectA = new GRect(0 ,0 ,60 ,60);
		rectA.setFilled(true);
		rectA.setFillColor(Color.BLACK);
		add(rectA);
		//Add RectB
		GRect rectB = new GRect(0 ,60 ,60 ,60);
		rectB.setFilled(true);
		rectB.setFillColor(Color.RED);
		add(rectB);
		//Add RectC
		GRect rectC = new GRect(0 ,120 ,60 ,60);
		rectC.setFilled(true);
		rectC.setFillColor(Color.YELLOW);
		add(rectC);
	}
	
	private Color currentColor;
	
	public void mousePressed(MouseEvent e) {
		if(getElementAt(e.getX(), e.getY()) == rectA) {
			currentColor = Color.BLACK;
		} else if(getElementAt(e.getX(), e.getY()) == rectB) {
			currentColor = Color.RED;
		} else if(getElementAt(e.getX(), e.getY()) == rectC) {
			currentColor = Color.YELLOW;
		} else {
			centerX = e.getX();
			centerY = e.getY();
			oval = new GOval(0,0);
			oval.setFilled(true);
		}
	}
	
	public void mouseDragged(MouseEvent e) {
		findRadius(e.getX(), e.getY());
		oval.setSize(r, r);
		if(e.getX() >= centerX && e.getY() >= centerY) {
			add(oval,centerX, centerY);
		} else if(e.getX() >= centerX && e.getY() < centerY) {
			add(oval,centerX , e.getY());
		} else if(e.getX() < centerX && e.getY() < centerY) {
			add(oval,e.getX(), e.getY());
		} else {
			add(oval,e.getX(), centerY);
		}
	}
	
	//Declare radius
	private double r;
	
	private void findRadius(int dragX, int dragY) {
		double x = dragX - centerX;
		double y = dragY - centerY;
		r = Math.sqrt(x*x + y*y);	
	}
	
	
}
