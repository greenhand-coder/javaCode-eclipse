package main;

import java.util.Scanner;

import iron.A;
import iron.B;
import iron.C;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double height;
		double width;
		
		Scanner input = new Scanner(System.in);

		System.out.println("������ְ�� ��͸ߣ��Կո���Ϊ�ָ���");
		height = input.nextDouble();
		width = input.nextDouble();
		
		while(true) {
			
			System.out.println("������Ҫ���е���״��A������� B����Բ��  C�������Σ�");
			String shape = input.next();
			if(shape.equals("A")) {
				System.out.print("��������εĿ�Ⱥ͸߶ȣ��Կո�ָ���:");
				A a = new A(input.nextDouble(), input.nextDouble());
				System.out.println("�������Ϊ" + a.getArea());
				System.out.println("�ְ�������Ϊ" + a.getArea() / (height * width));
			}else if(shape.equals("B")) {
				System.out.print("������Բ�İ뾶:");
				B b = new B(input.nextDouble());
				System.out.println("Բ���Ϊ" + b.getArea());
				System.out.println("�ְ�������Ϊ" + b.getArea() / (height * width));
			}else if(shape.equals("C")) {
				System.out.print("���������ε��µ��ϵ׺͸ߣ��Կո�ָ�����");
				C c = new C(input.nextDouble(), input.nextDouble(), input.nextDouble());
				System.out.println("�������Ϊ" + c.getArea());
				System.out.println("�ְ�������Ϊ" + c.getArea() / (height * width));
			}else {
				break;
			}
			
		}


	}

}
