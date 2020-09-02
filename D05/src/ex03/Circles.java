package ex03;

public class Circles {
	
	static public Circle[] createCircles (int n){
		Circle circles[] = new Circle[n];
		for(int i = 0; i  < n ; i++) {
			Point tPoint = new Point(Math.random() * 20 + 1, Math.random() * 20 + 1);
			Circle tCircle = new Circle(tPoint, Math.random() * 5 + 1);  //1-5
			circles[i] = tCircle;
		}
	return circles;
	}
	
	static public void outputCircles(Circle[] circles) {
		for(int i = 0; i < circles.length; i++) {
			System.out.println(circles[i].getCircle());
		}
	}
	
	static public Circle[] getIsolatedCircles(Circle[] circles) {
		boolean sign = true;
		int k = 0;
		Circle isolatedCircles[] = new Circle[circles.length];
		for(int i = 0; i < circles.length; i++) {
			for(int j = 0; j < circles.length && i != j; j++) {
				if(circles[i].isIntersected(circles[j]) || circles[i].contains(circles[j])) {  
					sign = false;              //不相交包含标记失效
					break;
				}
			}
			if(sign == true) {
				isolatedCircles[k++] = circles[i];
			}else {
				sign = true;
			}
			
		}
		return isolatedCircles;
	}

}
