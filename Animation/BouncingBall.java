/*
 * File: BouncingBall.java
 * ========================================================
 * A program that simulates a bouncing ball
 */
import acm.program.*;
import acm.graphics.*;
import acm.util.*;

public class BouncingBall extends GraphicsProgram {
	/* The size of the ball. */
	private static final double BALL_SIZE = 50;
	
	/* The amount of time to pause between frames (60 fps). */
	private static final double PAUSE_TIME = 1000.0 / 60;
	
	/* The maximum initial horizontal velocity of the ball. */
	private static final double MAX_INITIAL_SPEED = 4.0;
	
	/* Gravitational acceleration. */
	private static final double GRAVITY = 0.125;
	
	/* Elasticity. */
	private static final double ELASTICITY = 0.85;
	
	/* Our favorite random generator to do stuff for us! */
	private RandomGenerator rgen;
	
	/**
	 * Creates and simulates bouncing a ball across the screen.
	 */
	public void run() {
		rgen = new RandomGenerator();
		GOval ball = makeBall();
		add(ball);
		bounceBall(ball);
	}
	
	/**
	 * Creates and returns a ball to bounce across the screen.
	 *  
	 * @return The ball that will be bounced.
	 */
	private GOval makeBall() {
		GOval result = new GOval(0, 0, BALL_SIZE, BALL_SIZE);
		result.setFilled(true);
		result.setColor(rgen.nextColor());
		return result;
	}
	
	/**
	 * Bounces the specified ball across the screen.
	 * 
	 * @param ball The ball to bounce.
	 */
	private void bounceBall(GOval ball) {
		/* Track the ball velocity. */
		double dx = rgen.nextDouble(0.0, MAX_INITIAL_SPEED);
		double dy = 0;
		
		/* Loop, simulating bouncing the ball across the screen.
		 * 
		 * TODO: This loops infinitely. Could you stop the loop when the ball is
		 * off-screen?
		 */
		while (true) {
			/* Move the ball one step. */
			ball.move(dx, dy);
			
			/* Gravity accelerates the ball downward. */
			dy += GRAVITY;
			
			/* If the ball hit the ground and is still moving downward,
			 * reflect it back up. The check for downward motion is
			 * necessary to make sure that we don't get the ball stuck
			 * in the ground.
			 */
			if (hasBallHitBottom(ball) && dy > 0.0) {
				dy *= -ELASTICITY;
			}
			
			pause(PAUSE_TIME);
		}
	}
	
	/**
	 * Returns whether the ball has hit the bottom of the screen.
	 * 
	 * @param ball The ball to test.
	 * @return Whether it has hit the bottom of the screen.
	 */
	private boolean hasBallHitBottom(GOval ball) {
		/* Determine where the bottom of the ball is. */
		double bottomY = ball.getY() + ball.getHeight();
		
		/* Return whether it's below the bottom of the window. */
		return bottomY >= getHeight();
	}
}
