import java.util.*;

import acm.util.*;
import acm.program.*;


public class HowUnique extends ConsoleProgram {
	public void run() {
		askForName();
		println("Unique name list contains:");
		printList();
	}

	private ArrayList<String> list = new ArrayList<String>();
	
	private void askForName() {
		while(true) {
			String response = readLine("Enter Name: ");
			findUnique(response);
			if(response.equals("")) break;
		}
	}
	
	private void findUnique(String response) {
		int match = 0;
		for(int i=0; i<list.size(); i++) {
			if(response == list.get(i)) {
				match++;
			}
		}
		if(match == 0) {
			list.add(response);
		}
	}
	
	private void printList() {
		for(int i=0; i<list.size(); i++) {
			println(list.get(i));
		}	
	}
	
}
