package exp09.main;

import java.util.Scanner;

import exp09.data.IllegalTriangleException;
import exp09.data.Triangle;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		
		while(true) {
			try {
				Triangle tri = new Triangle(input.nextDouble(), input.nextDouble(), input.nextDouble());
				System.out.println("����������ǣ�" + tri.getArea());
			} catch (IllegalTriangleException e) {
				
				System.out.println("��ʼ��������ʧ��");
				break;
			}catch(java.util.InputMismatchException ex) {
				System.out.println("����ı߳�����ʵ��.");
				break;
			}
		}
		
	}

}
