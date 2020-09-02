package mian;
import java.util.*;

import shape.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Rectangle rectangles[] = new Rectangle[10];
		for(int i = 0; i < 10; i++) {
			Rectangle a = new Rectangle(Math.random()*100 ,Math.random()*100);
			rectangles[i] = a;
		}
		Utility.output(rectangles);
		System.out.println("最大的矩形是");
		System.out.printf("[%.2f,%.2f] - %.2f\n", Utility.getMaxRectangle(rectangles).getWidth() , 
				Utility.getMaxRectangle(rectangles).getHeight() , Utility.getMaxRectangle(rectangles).getArea());
		
		Utility.sort(rectangles);

		Rectangle.setColor("RED");
			
		Utility.output(rectangles);
		

	}

}
