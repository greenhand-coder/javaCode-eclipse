package calender;
import java.util.*;
public class Calneder {
	static int month[]= {31,28,31,30,31,30,31,31,30,31,30,31}; 
	static String days[]=  {"Sun","Mon","Tue","Wen","Thu","Fri","Sat"};
	
		 static void printCanlendar(int day,int year){ 
		  
		  int count = 0; //下个月留的空格数
		  int blankDay = day; // 第一个月留的空格数
		  
		  for(int i = 0 ; i < 12 ;i++)  //12个月份，利用数组month
		 {  
		     System.out.printf("%d年 %d月\n",year,i+1);
		     System.out.printf("----------------------------\n");
		  
		     for(int today = 0; today < 7 ; today++) { System.out.printf("%s ",days[today]);} //输出星期
		     System.out.printf("\n");
		     System.out.printf("----------------------------\n");
	//------------------------------------------这个月的日历--------------------------------------------------------	   
		     for(int j = 1 ;j <= month[i]; j++, count++)  //每个月的天数
		    {
		    	 for(int k = 0; k < blankDay ;blankDay--)   System.out.printf("    ");  //print blank day
		    
		    	 System.out.printf("%3d ",j);//今天日子
		    
		    	 if((day + count + 1) % 7 == 0) {  System.out.print('\n'); }  //满一星期就换行
		    	 
		    	 if(j == month[i]) { System.out.printf("\n"); count = ((count + day) % 7);  }// 计算最后一天是星期几
		   }
	//------------------------------------------下个月的日历的准备---------------------------------------------------

		   if(count == 7) count = 0; //最后一天星期六，不留空格
		   
		   System.out.print('\n');
		   blankDay = count; // 输出空格
		   day = 0;
		   
		}  
	  }


//--------------------------------------------------主函数----------------------------------------------------------	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  int year;  //年份
		  int day; //第一天是星期几
		  
		  Scanner input = new Scanner(System.in);
		  System.out.println("请输入年份：");
		  year = input.nextInt();
		  
		  if((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)) {month[1]++;}  //leap year

		  System.out.println("请输入该年第一天是星期几：");
		  day = input.nextInt();

		  printCanlendar(day,year); //输出日历
		  
		 }
}

		

