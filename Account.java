package lab4;
import java.util.ArrayList;

public class Account {

	private static int accountNumbers = 1;
	private String customer;
	private int thisAccountNumber;
	private double balance;
	protected static Bank theBank;
	protected Account otherAccount;
	protected ArrayList<String> transactions;
	
	public Account(CurrentAccount arg) {
		otherAccount = arg;
		balance = arg.getBalance();
		transactions = new ArrayList<String>();
	}
	
	public Account(String arg1, double arg2) {
		customer = arg1;
		balance = arg2;
		thisAccountNumber = accountNumbers;
		accountNumbers +=1;
		transactions = new ArrayList<String>();
	}
	
	public Account(String arg1, double arg2, double arg3) {
		customer = arg1;
		balance = arg2;
		thisAccountNumber = accountNumbers;
		accountNumbers +=1;
		SavingsAccount savingsaccount = new SavingsAccount(arg1, arg3);
		otherAccount = savingsaccount;
		transactions = new ArrayList<String>();
	}
	
	public int getAccountNumber() {
		return thisAccountNumber;
	}
	
	public String getCustomer() {
		return customer;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double arg) {
		balance = arg;
	}
	
	public static void setBank(Bank arg) {
		theBank = arg;
	}
	
	public SavingsAccount getSavingsAccount() {
		
		 if (otherAccount != null && otherAccount instanceof SavingsAccount) {
		        return (SavingsAccount) otherAccount;
		    } else {
		        return null; 
	    }
	}

	public void annualChange() {
		if (this instanceof SavingsAccount) {
			balance *= 1.01;
		}
		if (this instanceof Loan) {
			balance *= 1.05;
		}
		if (this instanceof CurrentAccount) {
			if (balance - 10 < 0) {
				CurrentAccount CA =  new CurrentAccount(getCustomer(), getBalance()-10);
				theBank.getLoan(CA);
				balance = 0;
			}
			else {
				balance -= 10;
			}
		}
	}
	
	public String toString() {
		
		String s = "";
		
		if (this instanceof CurrentAccount) {	
			s = "Current Account " + ": " + balance +"\n"; 
		} else if (this instanceof SavingsAccount){
			s = "Savings Account " + ": " + balance +"\n"; 
		} 
		
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < transactions.size(); i++) {
			String addon = transactions.get(i) + "\n";
			builder.append(addon);
		}
		
		s+= builder;
		
		if (this instanceof SavingsAccount){
			for (int i = 0; i < theBank.theLoans.size(); i++) {
				if (this.getCustomer().equals(theBank.theLoans.get(i).otherAccount.getCustomer())) {
					s += "\nLoan: " + theBank.theLoans.get(i).getBalance() +"\n"; 
					for (String string: theBank.theLoans.get(i).transactions) {
						s += string;
					}
				}
			}
		}
		
		return s;
	}
		
}



