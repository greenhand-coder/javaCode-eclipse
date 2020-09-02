package homework3;

import java.util.Scanner;




public class Main {

	  public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);

	        EvalueateExpression m = new EvalueateExpression();
	        while (sc.hasNext()) {
	            
	            System.out.println(m.calc((sc.nextLine() + ")").toCharArray()));
	        }

	    }

}
