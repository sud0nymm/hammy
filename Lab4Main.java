package lab4;

public class Lab4Main {

	public static void main(String[] args) {
		
		Bank theBank = new Bank("Great Northern Bank");
		
		System.out.println("Setting up the accounts");
		System.out.println(theBank.createAccount("Peter", 2000.0, 2000.0));
		System.out.println(theBank.createAccount("Sofia", 2000.0, 2000.0));		
		System.out.println(theBank.createAccount("Olga", 2000.0, 2000.0));
		System.out.println(theBank.createAccount("Alex", 2000.0));
		System.out.println(theBank);

		System.out.println("\nTransfer below maximum of current account\n");
		theBank.transfer("Peter", "Sofia", 1000);
		System.out.println(theBank.checkPerson("Peter"));
		System.out.println(theBank.checkPerson("Sofia"));
		
		System.out.println("\nTransfer of remainder of current account\n");
		theBank.transfer("Peter", "Sofia", 1000);
		System.out.println(theBank.checkPerson("Peter"));
		
		System.out.println("\nTransfer of more money than on current account\n");
		
		theBank.transfer("Sofia", "Peter", 100);
		theBank.transfer("Peter", "Sofia", 1000);
		System.out.println(theBank.checkPerson("Peter"));
		
		System.out.println("\nTransfer of more money than on current + savings accounts\n");
		
		theBank.transfer("Peter", "Sofia", 1500);
		System.out.println(theBank.checkPerson("Peter"));
		System.out.println(theBank.checkPerson("Sofia"));
		
		System.out.println(theBank);
		
		System.out.println("\nTaking the second loan\n");
		theBank.transfer("Peter", "Sofia", 1500);
		System.out.println(theBank.checkPerson("Peter"));
		System.out.println(theBank.checkPerson("Sofia"));
		
		System.out.println(theBank);
		
		System.out.println("\nTesting debt for account without savings account\n");
		theBank.transfer("Alex", "Sofia", 5000);
		System.out.println(theBank.checkPerson("Alex"));
		System.out.println(theBank.checkPerson("Sofia"));
				
		System.out.println("\nPay off Peter's loans\n");
		
		System.out.println(theBank.checkPerson("Peter"));
		theBank.cashPayment("Peter", 300);
		System.out.println(theBank.checkPerson("Peter"));
		theBank.cashPayment("Peter", 300);
		System.out.println(theBank.checkPerson("Peter"));	
		theBank.cashPayment("Peter", 1400);
		System.out.println(theBank.checkPerson("Peter"));
		
		System.out.println(theBank);
		
		System.out.println("\nTesting annualChange() on current and savings accounts\n");
		System.out.println(theBank.checkPerson("Olga"));
		theBank.computeAnnualChange();
		System.out.println(theBank.checkPerson("Olga"));		
		theBank.transfer("Olga", "Sofia", 1990);
		System.out.println(theBank.checkPerson("Olga"));
		theBank.computeAnnualChange();
		System.out.println(theBank.checkPerson("Olga"));
		
	}

}
