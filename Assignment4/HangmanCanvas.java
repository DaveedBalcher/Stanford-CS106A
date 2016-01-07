/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */


import acm.graphics.*;

//Name: 
//Section Leader: 

public class HangmanCanvas extends GCanvas {

	/* Constants for the simple version of the picture (in pixels) */
		private static final int SCAFFOLD_HEIGHT = 360;
		private static final int BEAM_LENGTH = 144;
		private static final int ROPE_LENGTH = 18;
		private static final int HEAD_RADIUS = 36;
		private static final int BODY_LENGTH = 144;
		private static final int ARM_OFFSET_FROM_HEAD = 28;
		private static final int UPPER_ARM_LENGTH = 72;
		private static final int LOWER_ARM_LENGTH = 44;
		private static final int HIP_WIDTH = 36;
		private static final int LEG_LENGTH = 108;
		private static final int FOOT_LENGTH = 28;

	
	/** HangmanCanvas constructor. You can do initialization (if needed) here. */
	public HangmanCanvas() {

	}
	
	private int cW = 0, cH = 0; 
	
/** Resets the display so that only the scaffold appears */
	public void reset() {
		cW = getWidth()/2;
		cH = getHeight()/2 - 30;
		GLine scaffold = new GLine(cW-BEAM_LENGTH, cH-SCAFFOLD_HEIGHT/2, cW-BEAM_LENGTH, cH+SCAFFOLD_HEIGHT/2);
		GLine beam = new GLine(cW-BEAM_LENGTH, cH-SCAFFOLD_HEIGHT/2, cW, cH-SCAFFOLD_HEIGHT/2);
		GLine rope = new GLine(cW, cH-SCAFFOLD_HEIGHT/2, cW, cH-(SCAFFOLD_HEIGHT/2)+ROPE_LENGTH);
		add(scaffold);
		add (beam);
		add(rope);
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String hiddenWord) {
		
		double x = cW-50;
		double y = cH*2;
		GLabel word = new GLabel(hiddenWord, x, y);
		if (getElementAt(x,y) != null){
			remove(getElementAt(x,y));
		}
		add(word);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(String allMemory, int numGuesses) {
		
		double x = cW-50;
		double y = cH*2.15;
		GLabel wrongLetters = new GLabel(allMemory, x, y);
		if (getElementAt(x,y) != null){
			remove(getElementAt(x,y));
		}
		add(wrongLetters);
		int p1 = cH-(SCAFFOLD_HEIGHT/2)+ROPE_LENGTH;
		int HEAD_DIAMETER = HEAD_RADIUS*2;
		int p2 = p1+HEAD_DIAMETER+ARM_OFFSET_FROM_HEAD;
		int p3 = p1+HEAD_DIAMETER+BODY_LENGTH;
		if(numGuesses < 8) {
			GOval head = new GOval(cW-HEAD_RADIUS, p1, HEAD_DIAMETER, HEAD_DIAMETER);
			add(head);
		}
		if(numGuesses < 7) {
			GLine body = new GLine(cW, p1+HEAD_DIAMETER, cW, p1+HEAD_DIAMETER+BODY_LENGTH);
			add(body);
		}
		if(numGuesses < 6) {
			GLine leftArm1 = new GLine(cW, p2, cW-UPPER_ARM_LENGTH, p2);
			GLine leftArm2 = new GLine(cW-UPPER_ARM_LENGTH, p2, cW-UPPER_ARM_LENGTH, p2+LOWER_ARM_LENGTH);
			add(leftArm1);
			add(leftArm2);
		}
		if(numGuesses < 5) {
			GLine rightArm1 = new GLine(cW, p2, cW+UPPER_ARM_LENGTH, p2);
			GLine rightArm2 = new GLine(cW+UPPER_ARM_LENGTH, p2, cW+UPPER_ARM_LENGTH, p2+LOWER_ARM_LENGTH);
			add(rightArm1);
			add(rightArm2);
		}
		if(numGuesses < 4) {
			GLine leftLeg1 = new GLine(cW, p3, cW-HIP_WIDTH, p3);
			GLine leftLeg2 = new GLine(cW-HIP_WIDTH, p3, cW-HIP_WIDTH, p3+LEG_LENGTH);
			add(leftLeg1);
			add(leftLeg2);
		}
		if(numGuesses < 3) {
			GLine rightLeg1 = new GLine(cW, p3, cW+HIP_WIDTH, p3);
			GLine rightLeg2 = new GLine(cW+HIP_WIDTH, p3, cW+HIP_WIDTH, p3+LEG_LENGTH);
			add(rightLeg1);
			add(rightLeg2);
		}
		if(numGuesses < 2) {
			GLine leftFoot = new GLine(cW-HIP_WIDTH, p3+LEG_LENGTH, cW-HIP_WIDTH-FOOT_LENGTH , p3+LEG_LENGTH);
			add(leftFoot);
		}
		if(numGuesses < 1) {
			GLine rightFoot = new GLine(cW+HIP_WIDTH, p3+LEG_LENGTH, cW+HIP_WIDTH+FOOT_LENGTH , p3+LEG_LENGTH);
			add(rightFoot);
		}
	}

}
