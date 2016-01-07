import java.util.*;

import acm.util.*;
import acm.program.*;
import java.util.HashMap;
import java.util.Map;



public class BankAccounts extends ConsoleProgram{
	
	private static final double startBalance = 100.00;
	
	public void run() {
		accounts = new HashMap<String, Double>();
		while(true) {
			from = readLine("From: ");
			if(from.equals("")) break;
			to = readLine("  To: ");
			amt = readDouble(" Amt: $");
			println("");
			
			openAccount(from);
			openAccount(to);
			updateAccount(from);
			updateAccount(to);
		}
		printAccounts();
	}
	
	private void openAccount(String account) {
		notFound = true;
		for(String name: accounts.keySet()) {
			if(account == name) {
				notFound = false;
			}
		}
		if(notFound) {
			accounts.put(account, startBalance);
		}
	}
	
	private void updateAccount(String account) {
		double newBalance = accounts.get(account);
		if(account == from) {
			newBalance -= amt;
		} else {
			newBalance += amt;
		}
		accounts.remove(account);
		accounts.put(account, newBalance);
	}
	
	private void printAccounts() {
		for(String name: accounts.keySet()) {
			println("Account " + "[" + name + "]" + " has $" + accounts.get(name));
		}
	}
	
	
	/* Local Instance Variables */
	private Map<String, Double> accounts;
	private String from;
	private String to;
	private double amt;
	private boolean notFound;
}
