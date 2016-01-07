/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {
	public void run() {
		for(int i=0; i<4; i++) {
			checkForBeeper();
			checkEast();
			if(frontIsClear()) {
				overColumn();
			}
		}
	}
	
	private void checkForBeeper() {
		positionKarel();
		check();
	}
	
	private void positionKarel() {
		while(notFacingNorth()) {
			turnRight();
		}
		if(frontIsBlocked()) {
			turnAround();
		}
	}
	
	private void check() {
		while(frontIsClear()) {
			if(noBeepersPresent()) {
				putBeeper();
			}
			move();
		}
	}
	
	private void checkEast() {
		while(notFacingEast()) {
			turnRight();
		}
	}
	
	
	private void overColumn() {
		while(notFacingEast()) {
			turnRight();
		}
		for(int i=0; i<4; i++) {
			move();
		}
		while(frontIsClear()) {
			turnRight();
		}
		turnAround();
	}
	
}
