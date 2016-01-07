
import stanford.karel.*;


public class Problem1 extends SuperKarel {
	public void run() {
		moveUpOne();
		for(int i=0; i<4; i++) {
			rowOfBeepers();
			reposition();
		}
	}
	
	private void moveUpOne() {
		turnLeft();
		move();
		turnRight();
		move();
	}
	
	private void rowOfBeepers() {
		while(frontIsClear()) {
			if(noBeepersPresent()) {
				putBeeper();
			}
			move();
		}
	}
	
	private void reposition() {
		backUp();
		turnLeft();
		move();
	}
	
	private void backUp() {
		turnAround();
		move();
		turnAround();
	}
	
}
