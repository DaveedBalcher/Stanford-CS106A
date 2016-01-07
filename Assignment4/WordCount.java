import java.io.*;
import java.util.*;

import acm.util.*;
import acm.program.*;


public class WordCount extends ConsoleProgram {
	public void run() {
		String fileName = "Lear.txt";
		readFile(fileName);
		printValues(fileName);
		
	}
	
	
	//Number of lines in the file
	private int numLines = 0;
	
	//Number of words in the file
	private int numWords = 0;
	
	//Number of characters in the file
	private int numChars = 0;
	
	
	private void readFile(String fileName) {
		try{
			BufferedReader rd = new BufferedReader(new FileReader(fileName));
			while(true) {
				String line = rd.readLine();
				if(line == null) break;
				else {
					numLines++;
					numWords += countWords(line);
					numChars += line.length();
				}
			}
		} catch (IOException e) {
		}
	}
	
	private int countWords(String line) {
		int words = 0;
		Boolean inWord = false;
		for(int i=0; i<line.length(); i++) {
			char ch = line.charAt(i);
			if (Character.isLetterOrDigit(ch)) {
				inWord = true;
				} else {
				if (inWord) words++;
				inWord = false;
			}
		}
		if(inWord) words++;
		return words;
	}
	
	private void printValues(String fileName) {
		println("File: " + fileName);
		println("Lines = " + numLines);
		println("Words = " + numWords);
		println("Chars = " + numChars);
	}
}
