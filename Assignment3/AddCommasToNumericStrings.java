import acm.program.*;
import java.awt.*;

public class AddCommasToNumericStrings extends ConsoleProgram {
	
	public void run() {
		while (true) {
			String digits = readLine("Enter a numeric string: ");
			if (digits.length() == 0) {
			break;
			}
			println(addCommasToNumericString(digits));
		}
	}
	
	private String addCommasToNumericString(String digits) {
		String withCommas = "";
		for(int i=0; i<digits.length(); i++) {
			char ch = digits.charAt(i);
			if((digits.length() - i - 1) % 3 == 0 && i != (digits.length() - 1)) {
				withCommas += ch + ",";
			} else {
				withCommas += ch;
			}	
		}
		return withCommas;
	}
	
}
