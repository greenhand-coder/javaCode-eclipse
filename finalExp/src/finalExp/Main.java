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
			System.out.println("1.����Ŀ¼�е�Դ�����ļ�");
			System.out.println("2.�鿴�������");
			System.out.println("0.�˳�����");
			System.out.println("-------Menu--------");
			System.out.print("��ѡ��:");
			
			int x = input.nextInt();
			if(x == 1) {
				analysis();
			}else if(x == 2) {
				readResult();
			}else if(x == 0) {
				System.out.println("�������˳�");
				return;
			}
			
		}
		

	}
	
	public static void analysis() {
		Scanner input = new Scanner(System.in);
		System.out.println("����һ���ļ�������Ŀ¼�������� (C:\\Users\\admin\\Documents\\java\\finalExp\\rmi)");
		
		String filePath = input.nextLine();
		File file = new File(filePath);
		
		if(file.exists()) {
			CodeAnalysis.Analysis(filePath);
			
		}else {
			System.out.printf("����[  %s]����Ŀ¼���򲻴���", filePath);
		}
	}
	
	public static void readResult() {
		
		System.out.println("���Բ鿴�Ľ���ļ���\n--------------------------");
		
		File resultFile  = new File("result"); 
		File[] fl = resultFile.listFiles();
		Scanner input = new Scanner(System.in);
		
		if(fl != null) {
			for(int i = 0; i < fl.length; i++) {
				System.out.println((i+1) + "." + fl[i].getName());
			}
		}
		System.out.println("--------------------------\nѡ��Ҫ�鿴���ļ���0��ʾ����)");
		
		int x = input.nextInt();
		
		
		ArrayList<String> strings=  FileLooker.readFile("result\\" + fl[x-1].getName());
		for(String s : strings) {
			System.out.println(s);
		}
		
		
	}

}
