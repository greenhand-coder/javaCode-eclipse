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
			System.out.println("1. �����˻�\n2. ɾ���˻�\n3. ������\n4. ȡ�����\n5. ����ͳ�� \n6. �˻��б� \n0. �˳�" );
			
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
					System.out.println("ɾ���ɹ�");
				}else {
					System.out.println("ɾ��ʧ��");
				}
			case 3:
				Account c = new SavingAccount(input.nextLine(), "name",  0);
				
				
			}
			
		}
		

		
	}

}
