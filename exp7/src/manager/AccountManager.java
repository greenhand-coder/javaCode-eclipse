package manager;

import java.util.ArrayList;
import java.util.List;
import bank.Account;
import bank.CreditAccount;
import bank.SavingAccount;
import java.util.Scanner;

public class AccountManager {
	private ArrayList<Account> accountList = new ArrayList<Account>();
	Scanner input = new Scanner(System.in);
	
	public boolean addAccount(Account account) {
		for(int i = 0; i < accountList.size(); i++) {
			if(accountList.get(i) == account) {
				return false;
			}			
		}
		this.accountList.add(account);
		return true;
	}
	
	public String getAccount(String id) {
		for(int i = 0; i < accountList.size(); i++) {
			if(id.equals(accountList.get(i).getAccount())) {
				return accountList.get(i).getAccount();
				
			}
		}
		return null;
	}
	
	public boolean removeAccount(Account account) {
		for(int i = 0; i < accountList.size(); i++) {
			if(accountList.get(i).getAccount().equals(account.getAccount())) {
				System.out.println("是否删除此账号（Y/N）");
				if(input.next().equals("Y")) {
					if(accountList.get(i).getBalance() == 0) {
						this.accountList.remove(i);
						return true;
					}
					else return false;
				}
				else {
					break;
				}
			}
		}
		return false;  //都找不到返回FALSE
	}
	
	public double getTotalBalance() {
		double totalBalance = 0;
		for(int i = 0; i < accountList.size(); i++) {
			totalBalance += accountList.get(i).getBalance();
		}
		return totalBalance;
	}
	
	public double getTotalOverdraft() {
		double totalOverdraft = 0;
		for(int i = 0; i < accountList.size(); i++) {
			if(accountList.get(i).getBalance() < 0) {
				totalOverdraft += accountList.get(i).getBalance();
			}
		}
		return totalOverdraft;
	}
	
	public int getNumberOfAccount() {
		return accountList.size();
	}
	
	public int getNumberOfCreditAccount() {
		int count = 0;
		for(int i = 0; i < accountList.size(); i++) {
			if(accountList.get(i) instanceof CreditAccount) {
				count++;
			}
		}
		return count;
	}
	
	public int getNumberOfSavingAccount() {
		int count = 0;
		for(int i = 0; i < accountList.size(); i++) {
			if(accountList.get(i) instanceof SavingAccount) {
				count++;
			}
		}
		return count;
	}
}
