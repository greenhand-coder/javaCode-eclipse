package bank;

public class CreditAccount extends Account{
	
	private double creditLimit;
	

	public CreditAccount(String account, String name, double banlance, double creditLimit) {
		super(account, name, banlance);
		this.creditLimit = creditLimit;
		
	}
	
	@Override
	public boolean withdraw(double amount) {
		super.setBanlance(super.getBanlance() - amount); 
		if(super.getBanlance() >= ((-1)*(creditLimit))) return true;
		else {
			super.setBanlance(super.getBanlance() + amount);
			return false;
		}
	}
	
	

}
