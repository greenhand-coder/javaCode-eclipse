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
		
		System.out.println("���ÿ�����");
		System.out.println("�������������");
		cre.deposit(input.nextDouble());
		System.out.println("��������" + cre.getBanlance() + "Ԫ");
		System.out.println("ȡ�������ȡ�����");
		if(cre.withdraw(input.nextDouble()) == true) {
			System.out.println("��������" + cre.getBanlance() + "Ԫ");
		}
		else {
			System.out.println("���ѳ���");
		}
		
		System.out.println("�������");
		System.out.println("�������������");
		sav.deposit(input.nextDouble());
		System.out.println("��������" + sav.getBanlance() + "Ԫ");
		System.out.println("ȡ�������ȡ�����");
		if(sav.withdraw(input.nextDouble()) == true) {
			System.out.println("��������" + sav.getBanlance() + "Ԫ");
		}
		else {
			System.out.println("����");
		}
		

	}

}
