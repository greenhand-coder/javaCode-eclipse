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
		
		System.out.println("�������θ���");
		int n = input.nextInt();  //���������
		RegularPolygon[] regular = new RegularPolygon[n];
		
		for(int i = 0 ; i < n ; i++ ) {
			System.out.println("���������� �������߳���x��y");
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
		System.out.println("������");
		System.out.println(regular[maxAreaI].rp2s() +"����� "+ regular[maxAreaI].getArea());
		System.out.println("����ԭ�����");
		System.out.println(regular[minDistanceI].rp2s() +"����ԭ����� "+ regular[minDistanceI].getDistance());
	
		
		
		
	}


	}


