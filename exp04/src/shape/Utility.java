package shape;

public class Utility {
	
	public static int compare(Rectangle rect1, Rectangle rect2) {
		if(rect1.getArea() < rect2.getArea()) {
			return -1;
		}
		else if(rect1.getArea() > rect2.getArea()) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	public static void sort(Rectangle[] rectangles) {
		for(int i = 0; i < rectangles.length; i++) {
			for(int j = 0; j < rectangles.length - i - 1; j++) {
				if(rectangles[j].getArea() > rectangles[j].getArea()) {
					Rectangle t = rectangles[j];
					rectangles[j] = rectangles[j+1];
					rectangles[j+1] = t;
					}
			}
		}
	}
	
	public static Rectangle getMaxRectangle(Rectangle[] rectangles) {
		Rectangle maxRectangle = rectangles[0];
		for(int i = 0; i < rectangles.length; i++) {
			if(maxRectangle.getArea() < rectangles[i].getArea()) {
				maxRectangle = rectangles[i];
			}
		}
		return maxRectangle;
	}
	
	public static void output(Rectangle[] rectangles) {
		System.out.printf("共有%d个矩形对象，颜色是%s\n" , rectangles.length , Rectangle.getColor());
		for(int i = 0; i < rectangles.length; i++) {
			System.out.printf("[%.2f,%.2f] - %.2f\n", rectangles[i].getWidth() , rectangles[i].getHeight() , rectangles[i].getArea());
		}
	}
	
	

}
