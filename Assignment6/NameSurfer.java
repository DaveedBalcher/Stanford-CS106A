/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import acm.util.*;

public class NameSurfer extends ConsoleProgram implements NameSurferConstants {

	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base
	 * and initializing the interactors at the top of the window.
	 */
	
	public void init() {
		
		label = new JLabel("Name: ");
		add(label, NORTH);
		
		name = new JTextField(MAX_NAME);
		add(name, NORTH);
		name.addActionListener(this);
		
		graph = new JButton("Graph");
		add(graph, NORTH);
		
		clear = new JButton("Clear");
		add(clear, NORTH);
	}

	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are
	 * clicked, so you will have to define a method to respond to
	 * button actions.
	 */
	public void actionPerformed(ActionEvent e) {
		
	}
	
	
	// Instance Variables

	private JLabel label;
	private JTextField name;
	private JButton graph;
	private JButton clear;
	
}



