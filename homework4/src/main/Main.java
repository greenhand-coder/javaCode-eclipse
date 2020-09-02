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

		System.out.println("请输入钢板的 宽和高（以空格作为分隔）");
		height = input.nextDouble();
		width = input.nextDouble();
		
		while(true) {
			
			System.out.println("请输入要裁切的形状（A代表矩形 B代表圆形  C代表梯形）");
			String shape = input.next();
			if(shape.equals("A")) {
				System.out.print("请输入矩形的宽度和高度（以空格分隔）:");
				A a = new A(input.nextDouble(), input.nextDouble());
				System.out.println("矩形面积为" + a.getArea());
				System.out.println("钢板利用率为" + a.getArea() / (height * width));
			}else if(shape.equals("B")) {
				System.out.print("请输入圆的半径:");
				B b = new B(input.nextDouble());
				System.out.println("圆面积为" + b.getArea());
				System.out.println("钢板利用率为" + b.getArea() / (height * width));
			}else if(shape.equals("C")) {
				System.out.print("请输入梯形的下底上底和高（以空格分隔）：");
				C c = new C(input.nextDouble(), input.nextDouble(), input.nextDouble());
				System.out.println("梯形面积为" + c.getArea());
				System.out.println("钢板利用率为" + c.getArea() / (height * width));
			}else {
				break;
			}
			
		}


	}

}
