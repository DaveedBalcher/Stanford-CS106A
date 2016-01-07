import acm.program.*;
import java.awt.*;



public class AlternatingCapitalLetters extends ConsoleProgram {

	public void run() {
		println(altCaps("hello"));
		println(altCaps("section is awesome"));
	}
	
	private String altCaps(String str) {
		String newString = "";
		int counter = 0;
		for(int i=0; i<str.length(); i++) {
			char ch = str.charAt(i);
			if(Character.isLetter(ch)) {
				counter++;
				if(counter % 2 == 0) {
					newString += Character.toUpperCase(ch);
				} else {
					newString += Character.toLowerCase(ch);
				}
			} else {
				newString += ch;
			}
		}
		
		return newString;
	}
	
}
