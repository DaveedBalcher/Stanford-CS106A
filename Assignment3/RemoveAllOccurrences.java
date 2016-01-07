import acm.program.*;
import java.awt.*;


public class RemoveAllOccurrences extends ConsoleProgram {

		public void run() {
			println(removeAllOccurrences("This is a test", 't'));
			println(removeAllOccurrences("Summer is here!", 'e'));
			println(removeAllOccurrences("---0---", '-'));
		}
		
		public String removeAllOccurrences(String str, char ch) {
			String newString = "";
			for(int i=0; i<str.length(); i++) {
				char chA = str.charAt(i);	
				if(chA != ch) {
					newString += chA;
				}
			}
			return newString;
		}
}
