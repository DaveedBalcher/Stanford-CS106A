/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {
	public void run() {
		setSouthWest();
		drawUpwardLine();
		setNorthWest();
		drawDownwardLine();
		setSouthWest();
		move();
		drawUpwardLine();
		setNorthWest();
		clearBeepers();
		setNorthWest();
		findBeeper();
	}


	private void drawUpwardLine() {
		putBeeper();
		while(frontIsClear()) {
			beeperStepUp();
		}
	}
	
	private void drawDownwardLine() {
		putBeeper();
		while(frontIsClear()) {
			beeperStepDown();
		}
	}
	
	private void beeperStepUp() {
		move();
		turnLeft();
		if(frontIsClear()) {
			move();
			turnRight();
			putBeeper();
		}
		
	}
	
	private void beeperStepDown() {
		move();
		turnRight();
		if(frontIsClear()) {
			move();
			turnLeft();
			putBeeper();
		}
		
	}
	
	private void setNorthWest() {
		while(notFacingNorth()) {
			turnRight();
		}
		while(frontIsClear()) {
			move();
		}
		while(notFacingWest()) {
			turnRight();
		}
		while(frontIsClear()) {
			move();
		}
		turnAround();
	}
	
	private void setSouthWest() {
		while(notFacingSouth()) {
			turnRight();
		}
		while(frontIsClear()) {
			move();
		}
		while(notFacingWest()) {
			turnRight();
		}
		while(frontIsClear()) {
			move();
		}
		turnAround();
	}
	

	
	private void clearBeepers() {
		clearSt();
		while(frontIsClear()) {
			moveUp();
			clearSt();
		}
	}
	
	
	private void clearSt() {
		while(frontIsClear()) {
			if(beepersPresent()) {
				pickBeeper();
			}
			move();
		}
		while(notFacingSouth()) {
			turnRight();
			if(beepersPresent()) {
				pickBeeper();
			}
		}
	}
	
	private void moveUp() {
		move();
		turnRight();
		if(frontIsBlocked()) {
			turnAround();
		}
	}
	
	private void findBeeper() {
		while(noBeepersPresent()) {
			moveDiagonal();
		}
		while(notFacingSouth()) {
			turnRight();
		}
		pickBeeper();
		while(frontIsClear()) {
			move();
		}
		putBeeper();
	}
	
	private void moveDiagonal() {
		move();
		turnRight();
		move();
		turnLeft();
	}
	
}