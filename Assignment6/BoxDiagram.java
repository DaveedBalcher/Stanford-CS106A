import acm.graphics.*;
import acm.program.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


public class BoxDiagram extends GraphicsProgram{

	//Constants for boxes added to the screen.
	private static final double BOX_WIDTH = 120;
	private static final double BOX_HEIGHT = 50;

	private static final double BOX_X = -BOX_WIDTH/2;
	private static final double BOX_Y = -BOX_HEIGHT/2;
	
	private static final int MAX_NAME = 25;
	
	
	public void init() {
		mapBoxes = new HashMap<String, GObject>();
		setupControllers();
		addActionListeners();
		addMouseListeners();
		addKeyListeners();
		
	}
	
	private void setupControllers() {
		nameBox = new JTextField(MAX_NAME);
		nameBox.addActionListener(this);
		nameBox.setActionCommand("Add");
		add(new JLabel("Name "), SOUTH);
		add(nameBox, SOUTH);
		add(new JButton("Add"), SOUTH);
		add(new JButton("Remove"), SOUTH);
		add(new JButton("Clear"), SOUTH);
	}
	
	private void addBox(String text) {
		GRect box = new GRect(BOX_WIDTH, BOX_HEIGHT);
		GLabel label = new GLabel(text);
		double textX = -label.getWidth()/2;
		double textY = label.getAscent()/2;
		
		GCompound textBox = new GCompound();
		textBox.add(box, BOX_X, BOX_Y);
		textBox.add(label, textX, textY);
		
		Double canvasX = (double) (getWidth()/2);
		Double canvasY = (double) (getHeight()/2);
		add(textBox, canvasX, canvasY);
		
		mapBoxes.put(text, textBox);
	}
	
	
	private void removeBox(String name) {
		GObject obj = mapBoxes.get(name);
		if (obj != null) {
		remove(obj);
		mapBoxes.remove(name);
		}
	}
	
	private void clear() {
		removeAll();
		mapBoxes.clear();
		nameBox.setText("");
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
		String cmd = e.getActionCommand();
		if(cmd.equals("Add")) {
			addBox(nameBox.getText());
			
		} else if(cmd.equals("Remove")) {
			removeBox(nameBox.getText());
			
		} else if(cmd.equals("Clear")) {
			clear();
		}
	}
	
	/* IVARs */
	private JTextField nameBox;
	private HashMap<String,GObject> mapBoxes;
	//private JButton add;
	//private JButton remove;
	//private JButton clear;
}
