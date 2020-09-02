package finalExp;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner input = new Scanner(System.in);
		
		while(true) {
			System.out.println("-------Menu--------");
			System.out.println("1.分析目录中的源程序文件");
			System.out.println("2.查看分析结果");
			System.out.println("0.退出程序");
			System.out.println("-------Menu--------");
			System.out.print("请选择:");
			
			int x = input.nextInt();
			if(x == 1) {
				analysis();
			}else if(x == 2) {
				readResult();
			}else if(x == 0) {
				System.out.println("程序已退出");
				return;
			}
			
		}
		

	}
	
	public static void analysis() {
		Scanner input = new Scanner(System.in);
		System.out.println("输入一个文件夹名或目录名字形如 (C:\\Users\\admin\\Documents\\java\\finalExp\\rmi)");
		
		String filePath = input.nextLine();
		File file = new File(filePath);
		
		if(file.exists()) {
			CodeAnalysis.Analysis(filePath);
			
		}else {
			System.out.printf("错误：[  %s]不是目录名或不存在", filePath);
		}
	}
	
	public static void readResult() {
		
		System.out.println("可以查看的结果文件有\n--------------------------");
		
		File resultFile  = new File("result"); 
		File[] fl = resultFile.listFiles();
		Scanner input = new Scanner(System.in);
		
		if(fl != null) {
			for(int i = 0; i < fl.length; i++) {
				System.out.println((i+1) + "." + fl[i].getName());
			}
		}
		System.out.println("--------------------------\n选择要查看的文件（0表示放弃)");
		
		int x = input.nextInt();
		
		
		ArrayList<String> strings=  FileLooker.readFile("result\\" + fl[x-1].getName());
		for(String s : strings) {
			System.out.println(s);
		}
		
		
	}

}
