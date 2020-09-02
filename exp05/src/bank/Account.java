package bank;

public class Account {
	
	private String account;
	private String name;
	private double banlance;
	
	public Account(String account, String name, double banlance) {
		this.account = account;
		this.name = name;
		this.banlance = banlance;
	}


	public void deposit(double amount) {
		this.banlance += amount;
	}
	
	public boolean withdraw(double amount) {
		this.banlance -= amount;
		if(this.banlance >= 0) return true;
		else {
			this.banlance += amount;
			return false;
		}
	}
	
	public double getBanlance() {
		return banlance;
	}


	public void setBanlance(double banlance) {
		this.banlance = banlance;
	}


	@Override
	public String toString() {
		return "[" + account + "," + name + "]" + "-" + banlance;		
	}



}
