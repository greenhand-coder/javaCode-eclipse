package package01;
import java.util.*;

public class Location {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double x1,x2,y1,y2,r1,r2,d;
		Scanner input = new Scanner(System.in);
		x1=input.nextDouble();
		y1=input.nextDouble();
		r1=input.nextDouble();
		x2=input.nextDouble();
		y2=input.nextDouble();
		r2=input.nextDouble();
		d=Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
		if(d<Math.abs(r1-r2))
		{
			if(r1>r2) System.out.printf("(%.2f,%.2f)-%.2f包含(%.2f,%.2f)-%.2f",x1,y1,r1,x2,y2,r2);
			else System.out.printf("(%.2f,%.2f)-%.2f包含(%.2f,%.2f)-%.2f",x2,y2,r2,x1,y1,r1);
		}
		if(d>=Math.abs(r1-r2)&&d<=r1+r2) System.out.printf("(%.2f,%.2f)-%.2f与(%.2f,%.2f)-%.2f相交",x2,y2,r2,x1,y1,r1);
		if(d>r1+r2) System.out.printf("(%.2f,%.2f)-%.2f与(%.2f,%.2f)-%.2f无关",x2,y2,r2,x1,y1,r1);
	}

}
