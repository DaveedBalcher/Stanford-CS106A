import acm.program.*;
import acm.util.*;

import java.awt.*;
import java.util.*;


public class RemoveDoubleLetters extends ConsoleProgram {

	public void run() {
		String word = askUser();	
		String newWord = removeDoubleLetters(word);
		println("Your new word is " + newWord);
	}
	
	
	//Ask user for response
	private String askUser() {
		while(true) {
			String word = "";
			String userResponse = readLine("Your word:");        //Check if the user's input is one character
			for(int i=0; i<userResponse.length(); i++) {
				char ch = userResponse.charAt(i);
				if(ch >= 'A' && ch <= 'Z') {
					Character.toLowerCase(ch);
				}
				word += ch;
			}
			return word;
		}
	}		
	
	private String removeDoubleLetters(String word) {
		char previousCh = word.charAt(0);
		String newWord = "";
		newWord += previousCh;
		for(int i=1; i<word.length(); i++) {
			char ch = word.charAt(i);
			if(ch != previousCh) {
				newWord += ch;
			}
			previousCh = ch;
		}
		return newWord;
	}
	
}
