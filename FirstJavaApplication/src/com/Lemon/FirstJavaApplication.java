package com.Lemon;
import java.util.Scanner;

public class FirstJavaApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a,b,c;  //a��λ bʮλ c ��λ
		Scanner input = new Scanner(System.in);
		int x =input.nextInt();
		a=x/100;
		b=x/10-a*10;
		c=x-10*b-100*a;
		System.out.println(a+b+c);	
	}

}
