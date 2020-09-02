package iron;

public class B extends allShape {
	
	
	public B(double R) {
		super(R,R);
	}
	
	public double getArea() {
		return Math.PI * height * height;
	}
}
