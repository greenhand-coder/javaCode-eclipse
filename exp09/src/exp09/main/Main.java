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
				System.out.println("三角形面积是：" + tri.getArea());
			} catch (IllegalTriangleException e) {
				
				System.out.println("初始化三角形失败");
				break;
			}catch(java.util.InputMismatchException ex) {
				System.out.println("输入的边长不是实数.");
				break;
			}
		}
		
	}

}
