import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;


public class Frogger extends GraphicsProgram {

	public static final int SQSIZE = 75;
	public static final int NCOLS = 7;
	public static final int NROWS = 3;
	public static final int APPLICATION_WIDTH = SQSIZE*NCOLS;
	public static final int APPLICATION_HEIGHT = SQSIZE*NROWS;
	public static final int centerW = (NCOLS*SQSIZE)/2;
	public static final int bottomH = NROWS*SQSIZE;
	  
	
	public void run() {
		int frogX = centerW-(SQSIZE/2);
		int frogY = bottomH-(SQSIZE);
		GImage frog = new GImage("frog.gif");
		add(frog, frogX, frogY);
		addMouseListeners();
	}
	
	
	private int frogX = centerW-(SQSIZE/2);
	private int frogY = bottomH-(SQSIZE);
	
	
	public void mouseClicked(MouseEvent e) {
		if(getElementAt(frogX, frogY) != null) {
			GObject obj = getElementAt(frogX, frogY);
			remove(obj);
		}
		if (e.getX() < frogX) {
			frogX -= SQSIZE;
		}
		else if (e.getX() > frogX + SQSIZE) {
			frogX += SQSIZE;
		}
		if (e.getY() < frogY) {
			frogY -= SQSIZE;
		}
		else if (e.getY() > frogY + SQSIZE) {
			frogY += SQSIZE;
		}
		GImage frog = new GImage("frog.gif");
		add(frog, frogX, frogY);
	}
	  
}
