package main;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		int n = 0;
		
		
		Scanner input = new Scanner(System.in);
		System.out.print("输入二维坐标中点的个数");
		n = input.nextInt();
		System.out.printf("输入%d个点的横坐标和纵坐标:", n);
		
		double x[] = new double[n];
		double y[] = new double[n];
		for(int i = 0; i < n; i++ ) {
			x[i] = input.nextDouble();
			y[i] = input.nextDouble();
		}
		
		closestPoints(x, y, n);
		

	}
	
	static void closestPoints(double x[], double y[], int n) {
		double x1 = 0, x2 = 0, y1 = 0, y2 = 0;
		double dist;
		double minDist = 9999;
		
		for(int i = 0; i < n; i++) {
			for(int j = i+1; j < n; j++) {
				dist = Math.sqrt(( x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]));
				if(dist < minDist) {
					minDist = dist;
					x1 = x[i]; y1 = y[i];
					x2 = x[j]; y2 = y[j];
				}
			}
		}
		System.out.printf("最近点对为(%.1f , %.1f) 和 (%.1f , %.1f), 距离是: %.2f\n" , x1, y1, x2, y2, minDist);
	}

}
