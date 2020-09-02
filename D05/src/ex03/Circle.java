package ex03;

public class Circle {
	
	private Point center;
	private double radius;
	
	public Circle() {
		this.center = new Point();
		this.radius = 1;
	}
	public Circle(Point center, double radius) {
		this.center = center;
		this.radius = radius;
	}
	
	public Point getCenter() {
		return center;
	}
	public void setCenter(Point center) {
		this.center = center;
	}
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	//圆的面积
	public double getArea() {
		return Math.PI * radius * radius;
	}
	//相交
	public boolean isIntersected(Circle other) {
		if(((radius + other.radius) > center.distance(other.center)) &&
				Math.abs(radius - other.radius) < center.distance(other.center)) {
			return true;
		}else {
			return false;
		}
	}
	//包含
	public boolean contains(Circle other) {
		if(Math.abs(radius - other.radius) > center.distance(other.center)) {
			return true;
		}else {
			return false;
		}
	}
	
	public String getCircle() {
		return "[Circle( " + center.getX() + " , " + center.getY() + ") - " + radius + " ]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
