package data;

import java.util.Scanner;

public class Main {
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProductManager a = new ProductManager();
		Scanner input = new Scanner(System.in);
		
		
		
		while(true) {
			System.out.println("������ָ��     1.����  2. ����   3.����  4.ͳ��  5.��ȡ�ĵ�  6.�浵");
			int x = input.nextInt();
			if(x == 1) {
				a.addProduct();
				System.out.println();
				System.out.println();
			}else if(x == 2) {
				a.saleProduct();
				System.out.println();
				System.out.println();
			}else if(x == 3) {
				System.out.print("������ָ��     1.���۸�����      2.�������������     3.���ϼ���������  ");
				int y = input.nextInt();
				if(y == 1) {
					a.sortProductInPrice();
					System.out.println();
					System.out.println();
				}else if(y == 2) {
					a.sortProductInAmount();
					System.out.println();
					System.out.println();
				}else if(y == 3) {
					a.sortProducrInDate();
					System.out.println();
					System.out.println();
				}
			}else if(x == 4) {
				a.collection();
				System.out.println();
				System.out.println();
			}else if(x == 5) {
				a.fileRead();
				System.out.println("�����ɹ�");
				System.out.println();
				System.out.println();
			}else if(x == 6) {
				a.fileWrite();
				System.out.println("�浵�ɹ�");
				System.out.println();
				System.out.println();
			}
			
		}

	}

}
