/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.*;
import java.util.*;

import acm.util.*;

//Name: 
//Section Leader: 

public class HangmanLexicon {

	/** Declare Instance Variables you need here */
	
	
	/** HangmanLexicon constructor. Do any initialization of your lexicon here. */
	public HangmanLexicon() {

	}
	
	//Number of words imported
	private int numWords;
	
/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return 100000;
	}

/** Returns the word at the specified index. */
	public String getWord(int wordN) {
    	ArrayList<String> wordList = new ArrayList<String>();
    	try{
    		BufferedReader rd = new BufferedReader(new FileReader("HangmanLexicon.txt"));
    		while(true){
        		String line = rd.readLine();
        		if(line == null) break;
        		wordList.add(line);
    		}
    	} catch(IOException ex)	{
    	}
    	String word = wordList.get(wordN);
    	numWords = wordList.size();
    	return word;
	}
}
