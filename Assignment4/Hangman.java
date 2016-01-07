/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;
import java.util.*;
import java.io.*;

//Name: 
//Section Leader:

public class Hangman extends ConsoleProgram {

	private HangmanLexicon wordList;
	
	private HangmanCanvas canvas;
	
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
    public void run() {
    	canvas = new HangmanCanvas();
		setLayout(new GridLayout(1, 2));
    	add(canvas);
    	validate();
    	println("Welcome to hangman!");
    	while(true) {
    		canvas.reset();
    		gameSetUp();
       		userChar();
       		println("");
       		newGame();
    		resetVariables();
    	}
    	
	}

    
    //This method selects a random word for the game
    private String chooseWord() {
    	wordList = new HangmanLexicon();
    	int wordN = rgen.nextInt(0, wordList.getWordCount()); 
    	return wordList.getWord(wordN);
    }
    
    //Declare Hangman Word
    private String word = "";
    
    //Declare number of guesses left in the game
    private int numGuesses = 8;
    
    private void gameSetUp() {
    	word = chooseWord();
    	String hiddenWord = hideWord(word, "0");
    	println("The word now looks like this: " + hiddenWord);
    	println("You have " + numGuesses + " guesses left.");
    }
    
	//Keeps track of previous correctly guess characters
	private String charMemory = "0";
	private String allMemory = "";
	private String wrongLetters = "";
    
    //This method processes user input
	private void userChar() {
		while(numGuesses > 0) {
			char userChar = askUser();
			boolean charRepeated = repeatChar(userChar);
			allMemory += userChar;
			int charsMatched = 0;
			for(int i=0; i<word.length(); i++) { //Check if the user's guess is correct
				char ch = word.charAt(i);
				if(ch == userChar && charRepeated) {
					println("That guess was correct.");
					i = word.length();
					charsMatched++;
					charMemory += userChar;
					}	
				}
			if (charsMatched < 1 && charRepeated) {  //Check if the user's guess is wrong
				numGuesses--;
				wrongLetters += userChar;
				canvas.noteIncorrectGuess(wrongLetters, numGuesses);
				println("There are no " + userChar + "'s in the word"); 
				println("You have " + numGuesses + "guesses left.");
			}
			String hiddenWord = hideWord(word, charMemory);
			canvas.displayWord(hiddenWord);
			println("The word now looks like this: " + hiddenWord);
			if(charsMatched<word.length() && numGuesses < 1) {
				println("The word was " + word + ". Better luck next time.");
			}
			if(checkFinished(hiddenWord)) {
				numGuesses = 0;
				println("You completed the word!");
			}
		}
	}
	
	
	//Ask user for response
	private char askUser() {
		while(true) {
			String userResponse = readLine("Your guess:");        //Check if the user's input is one character
			if(userResponse.length() > 0) {
				char userChar = userResponse.charAt(0);
				if(Character.isLowerCase(userChar)) {          //Check if the user's input is an upper case letter
					userChar = Character.toUpperCase(userChar);
				}
				if(userResponse.length() > 1 || userChar < 'A' || userChar > 'Z') {
					println("Please enter a single letter.");
				}
				return userChar;
			}	
		}
	}		
	
	
	//This method hides and reveals characters in the words
	private String hideWord(String wordToHide, String previousChars) {
		String hiddenWord = "";
		for (int i=0; i<wordToHide.length(); i++) {
			char ch = wordToHide.charAt(i);
			boolean charFound = true;
			for(int j=0; j<previousChars.length(); j++ ) {
				char userChar = previousChars.charAt(j);
				if(ch == userChar) {
					charFound = false;
					hiddenWord += userChar;
				}
			}
			if(charFound) {
				hiddenWord += '-';
			}
		}	
		return hiddenWord;
	}
	
	//This method check if a character has been guess more than once.
	private boolean repeatChar(char userChar) {
		for(int i=0; i<allMemory.length(); i++) {
			char ch = allMemory.charAt(i);
			if(ch == userChar) {
				println("You have entered " + userChar + " previously.");
				return false;
			}
		}
		return true;
	}
	
	//Check if the user has guessed all the characters in time.
	private boolean checkFinished(String hiddenWord) {
	for(int i=0; i<hiddenWord.length(); i++) {
		char ch = hiddenWord.charAt(i);
		if(ch == '-') {
			return false;
			}
		}
		return true;
	}

	
	//Start a new game
	private void newGame() {
		readLine("Hit enter to start a new game.");
		canvas.removeAll();
		println();
	}
	
	//Reset variables to start a fresh game
	private void resetVariables() {
		rgen = RandomGenerator.getInstance();
		numGuesses = 8;
		word = "";
		charMemory = "0";
		allMemory = "";
		wrongLetters = "";
	}
}