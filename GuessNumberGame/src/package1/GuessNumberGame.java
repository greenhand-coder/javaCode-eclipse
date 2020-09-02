package package1;
import java.util.*;


public class GuessNumberGame {
	static int gameTimes=0;
	static int winGameTimes=0;
	static int loseGameTimes=0;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while(true)
		{
			System.out.println("第"+gameTimes+"次游戏，程序生成了【0，9】之间的数字");
			Random ran = new Random();
			int ranNum = ran.nextInt(10)+1;  //随机数
			
			Scanner input = new Scanner(System.in);
			
			//game start
			int eachGameCount=3;
			while(eachGameCount!=0){
				System.out.println("你还有"+eachGameCount+"次机会，请输入数字");
				int num = input.nextInt();
				
				// too large
				if(num>ranNum) {				
					System.out.print("数字太大了");		
					if(eachGameCount==1) {
						System.out.println("本次游戏结束，正确数字是："+ranNum);
						loseGameTimes++;
					}
					eachGameCount--;
				}
				
				//         success
				if(num==ranNum) {					
					System.out.println("猜对了，加油");		
					winGameTimes++;
					break;
				}
				
				// too small
				if(num < ranNum) {                  
					System.out.print("数字太小了");
					if(eachGameCount==1) {
						System.out.println("本次游戏结束，正确数字是："+ranNum);
						loseGameTimes++;
					}
					eachGameCount--;
				}
				}  //小轮游戏结束
			
			System.out.println("还要继续吗（Y/N）");
			String judge = input.next();
			if(judge!="N") {System.out.printf("你一共进行了%d次游戏，成功%d次，失败%d次",winGameTimes+loseGameTimes,winGameTimes,loseGameTimes);break;}
			
			
		}
				
		
		

	}

}
