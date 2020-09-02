package shape;

public class Rectangle {
	private double width;
	private double height;
	private static String color;
	
	public Rectangle() {
		this.width = 0.0;
		this.height = 0.0;
		Rectangle.color = "BLACK";
	}
	
	public Rectangle(double width,double height) {
		this.height = height ;
		this.width = width;
		Rectangle.color = "BLACK";
	}
	
	public double getArea() {
		return height*width;
	}
	
	public double getPerimeter() {
		return 2*(height + width);
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public static String getColor() {
		return color;
	}

	public static void setColor(String color) {
		Rectangle.color = color;
	}


	

}
