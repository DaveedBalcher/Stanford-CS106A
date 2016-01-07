/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;

	public void run() {
		setUpGame();
		addMouseListeners();
		for(int i=0; i<NTURNS; i++) {
			waitForClick();
			playGame();
		}
		gameOver();
		
	}
	
	private void setUpGame() {
		setUpBricks(); 
		setUpPaddle();
	}
	
	//Declare brick object
	private GRect brick;
	
	// Creates rows of rainbow bricks
	private void setUpBricks() {
		for(int row=0; row<NBRICK_ROWS ;row++) { //Counts each row of bricks
			for(int column=0; column<NBRICKS_PER_ROW; column++) { //Counts each column of bricks
				brick = new GRect(column*(BRICK_WIDTH + BRICK_SEP), row*(BRICK_HEIGHT + BRICK_SEP) + BRICK_Y_OFFSET, BRICK_WIDTH, BRICK_HEIGHT);
				brick.setFilled(true);
				if(row<2) {  //Determines the color for each row of blocks
					brick.setColor(Color.RED);
				} else if(row<4) {
					brick.setColor(Color.ORANGE);
				} else if(row<6) {
					brick.setColor(Color.YELLOW);
				} else if(row<8) {
					brick.setColor(Color.GREEN);
				} else {
					brick.setColor(Color.CYAN);
				}
				add(brick);	
			}	
		}
	}
	
	
	//Declare paddle object
	private GRect paddle;
	
	// Creates movable paddle
	private void setUpPaddle() {
		//get center of the screen
		double x = (getWidth() - PADDLE_WIDTH) / 2;
		double y = getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
		paddle = new GRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle);
	}
	
	
	public void mouseMoved(MouseEvent e) {
		if ((e.getX() < getWidth() - PADDLE_WIDTH/2) && (e.getX() > PADDLE_WIDTH/2)) {
			paddle.setLocation(e.getX() - PADDLE_WIDTH/2, getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
		}
	}
	
	
	//Declare Ball
	private GOval ball;
	
	private void setUpBall() {
		ball = new GOval (BALL_RADIUS*2, BALL_RADIUS*2);
		ball.setFilled(true);
		add(ball, (getWidth()+BALL_RADIUS)/2, (getHeight()+BALL_RADIUS)/2);
	}
	
	/** The amount of time to pause between frames starts at (80 fps). */
	private double PAUSE_TIME = pauseTime();
	
	private double pauseTime() {
		double time = 1000.0 / 80;
		time = time/ 80*(100-numBricks);
		return time;
	}
	
	
	//Declare ball movement variables
	private double vx, vy; 
	
	private void playGame() {
		setUpBall();
		RandomGenerator rgen = RandomGenerator.getInstance(); 
		vy = 3.0;
		vx = rgen.nextDouble(1.0, 3.0); 
		if (rgen.nextBoolean(0.5)) vx = -vx;
		while(true) {
			ball.move(vx,vy);
			if(ball.getX() >= getWidth() - BALL_RADIUS* 2 || ball.getX() <= 0) {
				vx = -vx;
			}
			if(ball.getY() >= getHeight() - BALL_RADIUS* 2 || ball.getY() <= 0) {
				vy = -vy;
			}
			checkColliding();
			boolean endTurn = checkHitBottom();
			if(endTurn) {
				remove(ball);
				break;
			}
			if(numBricks == 0) {
				winner();
				break;
			}
			pause(PAUSE_TIME);
		}
	}	
	
	//Declare number of brick left to hit
	private int numBricks = 100;
		
	private void checkColliding() {
		GObject collider = getHitObj();
		if(collider == paddle) {
			vy = -vy;
		}
		else if(collider != null) {
			vy = -vy;
			numBricks--;
			remove(collider);
		}
	}
	
	
	private GObject getHitObj() {
		if(getElementAt(ball.getX(), ball.getY()) != null) {
			GObject element = getElementAt(ball.getX(), ball.getY());
			return element;
		}
		else if(getElementAt(ball.getX(), ball.getY() + 2*BALL_RADIUS) != null) {
			GObject element = getElementAt(ball.getX(), ball.getY() + 2*BALL_RADIUS);
			return element;
		}
		else if(getElementAt(ball.getX() + 2*BALL_RADIUS, ball.getY() + 2*BALL_RADIUS) != null) {
			GObject element = getElementAt(ball.getX() + 2*BALL_RADIUS, ball.getY() + 2*BALL_RADIUS);
			return element;
		}
		else if(getElementAt(ball.getX() + 2*BALL_RADIUS, ball.getY()) != null) {
			GObject element = getElementAt(ball.getX() + 2*BALL_RADIUS, ball.getY());
			return element;
		}
		else{
			return null;
		}
	}
	
	private boolean checkHitBottom() {
		if(ball.getY() >= getHeight() - 2*BALL_RADIUS) {
			return true;
		}
		else {
			return false;
		}
	}
	
	 
	private void gameOver() {
		removeAll();
		GLabel gameOver = new GLabel("Game Over");
		double x = (getWidth() - gameOver.getWidth())/2; 
		double y = getHeight()/3;
		gameOver.setColor(Color.RED);
		add(gameOver, x, y);
	}
	
	private void winner() {
		removeAll();
		GLabel winner = new GLabel("You Win!");
		double x = (getWidth() - winner.getWidth())/2; 
		double y = getHeight()/3;
		winner.setColor(Color.BLUE);
		add(winner, x, y);
	}
}
