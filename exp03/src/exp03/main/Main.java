package exp03.main;
import java.util.*;

import exp03.shape.RegularPolygon;

public class Main {

	public static void main(String[] args) {
		
		double maxArea = 0 ;
		int maxAreaI = 0;
		double minDistance = 50 ;
		int minDistanceI = 0;

		Scanner input = new Scanner(System.in);
		
		System.out.println("输入多边形个数");
		int n = input.nextInt();  //多边形数量
		RegularPolygon[] regular = new RegularPolygon[n];
		
		for(int i = 0 ; i < n ; i++ ) {
			System.out.println("请依次输入 边数，边长，x，y");
			RegularPolygon a = new RegularPolygon(input.nextInt(),input.nextDouble(),input.nextDouble(),input.nextDouble());
			regular[i] = a;
	//		System.out.print(regular.rp2s());
			
	//		area = regular[i-1].getArea();
	//		distance = regular[i-1].getDistance();
		}
		
		for(int i = 0; i < n ; i++) {
			System.out.println(regular[i].rp2s());

			if(regular[i].getArea() > maxArea) {
				maxArea = regular[i].getArea();
				maxAreaI = i;
			}
			
			if(regular[i].getDistance() < minDistance) {
				minDistance = regular[i].getDistance();
				minDistanceI = i;
			}
			
	
		
		}
		System.out.println("面积最大：");
		System.out.println(regular[maxAreaI].rp2s() +"面积： "+ regular[maxAreaI].getArea());
		System.out.println("距离原点最近");
		System.out.println(regular[minDistanceI].rp2s() +"距离原点距离 "+ regular[minDistanceI].getDistance());
	
		
		
		
	}


	}


