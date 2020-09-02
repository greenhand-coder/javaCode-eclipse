package ui;

import java.util.Scanner;

import bank.Account;
import manager.AccountManager;
import bank.CreditAccount;
import bank.SavingAccount;

public class BankUI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while(true) {
			System.out.println("1. 增加账户\n2. 删除账户\n3. 存款操作\n4. 取款操作\n5. 数据统计 \n6. 账户列表 \n0. 退出" );
			
			AccountManager man = new AccountManager();
			Scanner input = new Scanner(System.in);

			
			switch(input.nextInt()) {
			case 1:
				Account a = new SavingAccount(input.nextLine(), input.nextLine(), 0);
				man.addAccount(a);
				break;
			case 2:
				Account b = new SavingAccount(input.nextLine(), "name",  0);
				if(man.removeAccount(b) == true) {
					System.out.println("删除成功");
				}else {
					System.out.println("删除失败");
				}
			case 3:
				Account c = new SavingAccount(input.nextLine(), "name",  0);
				
				
			}
			
		}
		

		
	}

}
