package main;

import java.util.Scanner;

import bank.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String myAccount = "1258740087";
		String name = "jh";
		
		
		Scanner input = new Scanner(System.in);
		CreditAccount cre = new CreditAccount(myAccount, name, 500, 5000);
		SavingAccount sav = new SavingAccount(myAccount, name, 500);
		
		System.out.println("信用卡操作");
		System.out.println("存款：（输入存款数额）");
		cre.deposit(input.nextDouble());
		System.out.println("您现在有" + cre.getBanlance() + "元");
		System.out.println("取款：（输入取款数额）");
		if(cre.withdraw(input.nextDouble()) == true) {
			System.out.println("您现在有" + cre.getBanlance() + "元");
		}
		else {
			System.out.println("您已超额");
		}
		
		System.out.println("储蓄卡操作");
		System.out.println("存款：（输入存款数额）");
		sav.deposit(input.nextDouble());
		System.out.println("您现在有" + sav.getBanlance() + "元");
		System.out.println("取款：（输入取款数额）");
		if(sav.withdraw(input.nextDouble()) == true) {
			System.out.println("您现在有" + sav.getBanlance() + "元");
		}
		else {
			System.out.println("余额不足");
		}
		

	}

}
