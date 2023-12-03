package lab4;
import java.util.ArrayList;

public class Bank {

	public final String NAME;
	public ArrayList<Account> theAccounts;
	public ArrayList<Loan> theLoans;
	//you can initialize it when you declare it
	
	public Bank(String arg) {
		NAME = arg;
		Account.setBank(this); // copies its address to theBank in Account, no idea if this works
		theAccounts  = new ArrayList<Account>();
		theLoans = new ArrayList<Loan>();
	}
	
	public CurrentAccount searchAccount(String arg) {
		
		for (int i = 0; i < this.theAccounts.size(); i++) {
			Account account = theAccounts.get(i);
			if (account.getCustomer().equals(arg) && account instanceof CurrentAccount) {
				return (CurrentAccount) account;
			}
		}
		return null;
	}
	
	public String createAccount(String arg1, double arg2, double arg3) {
		if(searchAccount(arg1)!=null){
			return "Account(s) already exists for " + arg1;
		}
		CurrentAccount newacc = new CurrentAccount(arg1, arg2, arg3);
		theAccounts.add(newacc);
		theAccounts.add(newacc.otherAccount);
		return "Current and savings account created for " + arg1;
	}
	
	public String createAccount(String arg1, double arg2) {
		if(searchAccount(arg1)!=null){
			return "Account(s) already exists for " + arg1;
		}
		CurrentAccount newacc = new CurrentAccount(arg1, arg2);
		theAccounts.add(newacc);
		return "Current account created for " + arg1;
	}
	
	public void currentToSavings(String arg1, double arg2) {
		if (searchAccount(arg1) != null) {
			searchAccount(arg1).savings(arg2);
		}
	}
	
	public String checkPerson(String arg) {
		
		CurrentAccount thisacc = searchAccount(arg);
		String s;
		
		if (thisacc==null) {
			return "Person does not exist\n";
		}
		else {

			s = thisacc.getCustomer() + "\n";
			s = s + thisacc.toString() + "\n";
			if (thisacc.otherAccount!= null) {
				s += thisacc.otherAccount.toString();
			} 
		}
		
		
		
		return s;
	}
	
	public void transfer(String arg1, String arg2, double arg3) {
		
		if (searchAccount(arg1) == null || searchAccount(arg2) == null ) {
			;
		} else {
			searchAccount(arg1).send(arg3, searchAccount(arg2));
		}
	}
	
	public void getLoan(CurrentAccount arg) {
		Loan newLoan = new Loan(arg);
		theLoans.add(newLoan);
	}
	
	public void cashPayment(String arg1, double arg2) {

		for (int i = 0; i < theLoans.size(); i++) {
			if (arg1 == theLoans.get(i).getCustomer()) {
				arg2 = theLoans.get(i).payoff(arg2);
				
				if (arg2 >= 0){
					theLoans.remove(i);
				} else if (arg2 < 0) {
					break;
				}
			}
		}
		if (arg2>0) {
			searchAccount(arg1).recieve("Cash Payment", arg2);
		}
	}
	
	public void computeAnnualChange() {
		for (int i = 0; i < theAccounts.size(); i++) {
			theAccounts.get(i).annualChange();
		}
		for (int i = 0; i < theLoans.size(); i++) {
			theLoans.get(i).annualChange();
		}
	}
	
	public String toString() {
		String s = "BANK: " + NAME + "\nAccounts: " + theAccounts.size() + "\nLoans: " + theLoans.size();
		double currenttotal = 0;
		double savingstotal = 0;
		double loanstotal = 0;
		
		for (int i = 0; i < theAccounts.size(); i++) {
			if(theAccounts.get(i) instanceof CurrentAccount) {
				currenttotal += theAccounts.get(i).getBalance();
			}
			if (theAccounts.get(i).getSavingsAccount() != null) {
				savingstotal += theAccounts.get(i).getSavingsAccount().getBalance();
			}
		}
		for (int i = 0; i < theLoans.size(); i++) {
			 loanstotal += theLoans.get(i).getBalance();
		}
		
		s += "\nammount in current accounts, savings accounts and loans: " + currenttotal + " / " + savingstotal + " / " + loanstotal;
		return s;
	}
}








