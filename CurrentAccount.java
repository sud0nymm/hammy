package lab4;

public class CurrentAccount extends Account {

	public CurrentAccount(String arg1, double arg2) {
		super(arg1,arg2);
		this.otherAccount = null;
	}
	
	public CurrentAccount(String arg1, double arg2, double arg3) {
		super(arg1,arg2,arg3);
	}
		
	public void savings (double arg) {
		
		if (this.getSavingsAccount()!=null) {
			double currentMoney = getBalance();
			double savingsMoney = otherAccount.getBalance();
			
			if (arg>0) {
				if (currentMoney - arg> 0) {
					setBalance(getBalance()-arg);
					otherAccount.setBalance(otherAccount.getBalance()+arg);
					otherAccount.transactions.add("To savings account: " + arg);
					transactions.add("From current account: "+ arg);
				}
				else {
					arg = currentMoney;
					setBalance(getBalance()-arg);
					otherAccount.setBalance(otherAccount.getBalance()+arg);
					otherAccount.transactions.add("To savings account: " + arg);
					transactions.add("From current account: "+ arg);
				}
			} 
			if (arg<=0) {
				arg *= -1;
				if(savingsMoney - arg > 0) {
					otherAccount.setBalance(otherAccount.getBalance()-arg);
					setBalance(getBalance()+arg);
					transactions.add("To current account: " + arg);
					otherAccount.transactions.add("From savings account: "+ arg);
				}
				else {
					arg = savingsMoney;
					otherAccount.setBalance(otherAccount.getBalance()-arg);
					setBalance(getBalance()+arg);
					transactions.add("To current account: " + arg);
					otherAccount.transactions.add("From savings account: "+ arg);
				}
			}
		}
		else {
			return;
		}
		
	}
	
	public void recieve(String arg1, double arg2) {
		
		if(arg1=="Cash Payment") {
			
			transactions.add(arg1+ " : " +arg2);
			// distinguish between two cases
			for (int i = 0; i < theBank.theLoans.size(); i++) {
				// 1, if loan exists (matching customer)
				if (getCustomer().equals(theBank.theLoans.get(i).otherAccount.getCustomer())) {
					// code here, basically pay off theloans(i) and arg2 += theloans(i) until arg2 becomes 0 then break
					// use payoff function (payoff returns the balance after paying off)
					arg2 += theBank.theLoans.get(i).payoff(arg2);
			}
		}
			//2 if loan doesn't exist or is paid off (break statement or arg is 0)
			setBalance(getBalance() + arg2);
		} 		
		else {
			setBalance(getBalance() + arg2);
			transactions.add("Recieved from account of " + arg1 + " : " + arg2);
		}
	}
	
	public void send(double arg1, CurrentAccount arg2) {
		arg2.recieve(this.getCustomer(), arg1);
		setBalance(getBalance() - arg1);
		transactions.add("Send to account of " + arg2.getCustomer() + " : " + arg1);	
	
		if (getBalance()<0) {
			double debt = getBalance();
			if (otherAccount instanceof SavingsAccount) {
				if (otherAccount.getBalance() + debt < 0) {
					setBalance(getBalance() + otherAccount.getBalance());
					theBank.getLoan(this);
					double difference = getBalance();
					if (otherAccount.getBalance()>0) {
						transactions.add("From Savings Account : "+otherAccount.getBalance());
						otherAccount.transactions.add("To Current Account : " + otherAccount.getBalance());
					}
					otherAccount.setBalance(0);
					setBalance(0);
					transactions.add("Covered by loan : " + -difference);
				}else {
					otherAccount.setBalance(otherAccount.getBalance()+debt);
					transactions.add("From Savings Account : " + -debt);	
					otherAccount.transactions.add("To Current Account : " + -debt);
					setBalance(getBalance() - debt);
				}
			} else if (otherAccount == null) {
				theBank.getLoan(this);
				double difference = getBalance();
				setBalance(0);
				transactions.add("Covered by Loan : " + -difference);
				
				
			}
		}
	}
	
	
}









