package Main;

import java.util.Scanner;

import ex03.Circle;
import ex03.Circles;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n;
		
		Scanner input = new Scanner(System.in);
		
		
		System.out.println("输入要生成圆的个数");
		n = input.nextInt();
		
		Circle[] circles = Circles.createCircles(n);
		Circles.outputCircles(circles);
		System.out.println();
		
		Circle[] isolatedCircles = Circles.getIsolatedCircles(circles);
		if(isolatedCircles.length == 0) {
			System.out.println("没有孤立圆");
		}else {
			try {
				System.out.println("孤立圆：");
				for(int i = 0; i < isolatedCircles.length ; i++) {
					System.out.println(isolatedCircles[i].getCircle());
				}
			}catch(NullPointerException e) {
				System.out.println("孤立圆数组过大,应该用线性表");
			}
		}
		

	}

}
