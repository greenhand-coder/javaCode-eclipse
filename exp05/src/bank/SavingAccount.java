package bank;

public class SavingAccount extends Account {

	public SavingAccount(String account, String name, double banlance) {
		super(account, name, banlance);
		
	}
	
	@Override
	public boolean withdraw(double amount) {
		super.setBanlance(super.getBanlance() - amount); 
		if(super.getBanlance() >= 0) return true;
		else {
			super.setBanlance(super.getBanlance() + amount);
			return false;
		}
	}

}
