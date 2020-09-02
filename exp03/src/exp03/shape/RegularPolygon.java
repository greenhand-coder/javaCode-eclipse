package exp03.shape;

public class RegularPolygon {
	private int numberOfSides;
	private double lengthOfSide;
	private double x;
	private double y;
	
	public RegularPolygon() {
		this.numberOfSides = 3;
		this.lengthOfSide = 1.0;
		this.x = 0.0;
		this.y = 0.0;
	}
	
	public RegularPolygon(int newNumberOfSides , double newLengthOfSide){
		this.numberOfSides = newNumberOfSides;
		this.lengthOfSide = newLengthOfSide;
		this.x = 0.0;
		this.y = 0.0;
	}
	
	public RegularPolygon(int newNumberOfSides,double newLengthOfSide,double newX,double newY){
		this.numberOfSides = newNumberOfSides;
		this.lengthOfSide = newLengthOfSide;
		this.x = newX;
		this.y = newY;
	}
	
	public int getNumberOfSides() {
		return numberOfSides;
	}
	
	public double getLengthOfSide() {
		return lengthOfSide;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}

	public void setNumberOfSides(int newNumberOfSides) {
		this.numberOfSides = newNumberOfSides;
	}
	
	public void setLengthOfSides(int newLengthOfSides) {
		this.numberOfSides = newLengthOfSides;
	}
	
	public void setX(double newX) {
		this.x = newX;
	}
	
	public void setY(double newY) {
		this.y = newY;
	}

	public  double getArea() {
		return (numberOfSides*lengthOfSide*lengthOfSide)/(4*Math.tan(Math.PI/numberOfSides));
	}
	
	public double getDistance() {
		return Math.sqrt(x*x+y*y);
	}
	
	public String rp2s() {
		return "["+ numberOfSides + "," + lengthOfSide + "]" + "@" + "(" + x + "," + y +")" ;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	}

