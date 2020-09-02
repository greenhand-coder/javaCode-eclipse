package exp09.data;

public class Triangle {
	
	private double side1, side2, side3;   //三角形三条边长度
	
	IllegalTriangleException ex = new IllegalTriangleException();
	
	//构造方法
	public Triangle(double side1, double side2, double side3) throws IllegalTriangleException{
		if((side1 + side2) < side3) {
			System.out.println(side1 + ", " + side2 + ", " + side3 + " 不能构成三角形, 原因是: " + 
					side1 + " + " + side2 + " < " + side3);
			throw ex;
		}else if((side2 + side3) < side1) {
			System.out.println(side3 + ", " + side2 + ", " + side1 + " 不能构成三角形, 原因是: " + 
					side3 + " + " + side2 + " < " + side1);
			throw ex;
		}else if((side1 + side3) < side2) {
			System.out.println(side1 + ", " + side3 + ", " + side2 + " 不能构成三角形, 原因是: " + 
					side1 + " + " + side3 + " < " + side2);
			throw ex;
		}else if(side1 < 0 || side2 < 0 || side3 < 0) {
			System.out.println(side1 + ", " + side2 + ", " + side3 + " 不能构成三角形, 原因是: " + 
					side1 + " + " + side2 + " < " + side3);
			throw ex;
		}else {
			this.side1 = side1;
			this.side2 = side2;
			this.side3 = side3;
		}
	}
	
	public double getArea() {
		
		double p = (side1 + side2 + side3) / 2;
		return (Math.sqrt(p * (p - side1) * (p - side2) * (p - side3)));
		
	}
}
