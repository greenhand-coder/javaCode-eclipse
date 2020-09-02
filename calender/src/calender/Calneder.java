package calender;
import java.util.*;
public class Calneder {
	static int month[]= {31,28,31,30,31,30,31,31,30,31,30,31}; 
	static String days[]=  {"Sun","Mon","Tue","Wen","Thu","Fri","Sat"};
	
		 static void printCanlendar(int day,int year){ 
		  
		  int count = 0; //�¸������Ŀո���
		  int blankDay = day; // ��һ�������Ŀո���
		  
		  for(int i = 0 ; i < 12 ;i++)  //12���·ݣ���������month
		 {  
		     System.out.printf("%d�� %d��\n",year,i+1);
		     System.out.printf("----------------------------\n");
		  
		     for(int today = 0; today < 7 ; today++) { System.out.printf("%s ",days[today]);} //�������
		     System.out.printf("\n");
		     System.out.printf("----------------------------\n");
	//------------------------------------------����µ�����--------------------------------------------------------	   
		     for(int j = 1 ;j <= month[i]; j++, count++)  //ÿ���µ�����
		    {
		    	 for(int k = 0; k < blankDay ;blankDay--)   System.out.printf("    ");  //print blank day
		    
		    	 System.out.printf("%3d ",j);//��������
		    
		    	 if((day + count + 1) % 7 == 0) {  System.out.print('\n'); }  //��һ���ھͻ���
		    	 
		    	 if(j == month[i]) { System.out.printf("\n"); count = ((count + day) % 7);  }// �������һ�������ڼ�
		   }
	//------------------------------------------�¸��µ�������׼��---------------------------------------------------

		   if(count == 7) count = 0; //���һ���������������ո�
		   
		   System.out.print('\n');
		   blankDay = count; // ����ո�
		   day = 0;
		   
		}  
	  }


//--------------------------------------------------������----------------------------------------------------------	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  int year;  //���
		  int day; //��һ�������ڼ�
		  
		  Scanner input = new Scanner(System.in);
		  System.out.println("��������ݣ�");
		  year = input.nextInt();
		  
		  if((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)) {month[1]++;}  //leap year

		  System.out.println("����������һ�������ڼ���");
		  day = input.nextInt();

		  printCanlendar(day,year); //�������
		  
		 }
}

		

