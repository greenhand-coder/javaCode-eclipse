package iron;

public class C extends allShape{
	
	private double topWidth;
	
	public C(double bottomWidth, double topWidth, double height) {
		super(bottomWidth, height);    //�����ȼ�Ϊ�׿�
		this.topWidth = topWidth;
	}
	
	public double getArea() {
		return ((topWidth + width) * height / 2);
	}

}
