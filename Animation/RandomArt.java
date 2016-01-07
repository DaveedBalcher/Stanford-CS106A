/*
 * File: RandomArt.java
 * ========================================================
 * A program that adds random shapes to a canvas; there's an equal
 * chance that it'll be either a rectangle or an oval
 */
import acm.program.*;
import acm.graphics.*;
import acm.util.*;

public class RandomArt extends GraphicsProgram {

	/* The minimum dimension of the shape */
	private static final int MIN_SIZE = 20;
	
	/* The maximum dimension of the shape */
	private static final int MAX_SIZE = 30;
	
	/* The number of shapes to draw on the screen */
	private static final int NUM_SHAPES = 200;
	
	/* RandomGenerator instance to perform random calculations */
	private RandomGenerator rgen;

	public void run() {
		rgen = new RandomGenerator();
		for(int i = 0; i < NUM_SHAPES; i++) {
			// 50% of the time create a rectangle, and 50% of the time
			// create an oval
			if(rgen.nextBoolean()) {
				GRect myRect = getRandomRect();
				myRect.setLocation(rgen.nextInt(0, getWidth()), rgen.nextInt(0, getHeight()));
				add(myRect);
			} else {
				GOval myOval = getRandomOval();
				myOval.setLocation(rgen.nextInt(0, getWidth()), rgen.nextInt(0, getHeight()));
				add(myOval);
			}
		}
	}
	
	/**
	 * Returns a rectangle of random size (within MIN_SIZE and MAX_SIZE)
	 * and random color
	 */
	private GRect getRandomRect() {
		GRect rect = new GRect(rgen.nextInt(MIN_SIZE, MAX_SIZE), rgen.nextInt(MIN_SIZE, MAX_SIZE));
		rect.setFillColor(rgen.nextColor());
		rect.setFilled(true);
		return rect;
	}

	/**
	 * Returns an oval of random size (within MIN_SIZE and MAX_SIZE)
	 * and random color
	 */
	private GOval getRandomOval() {
		GOval oval = new GOval(rgen.nextInt(MIN_SIZE, MAX_SIZE), rgen.nextInt(MIN_SIZE, MAX_SIZE));
		oval.setFillColor(rgen.nextColor());
		oval.setFilled(true);
		return oval;
	}
}
