package main;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filePath;
		
		Scanner input = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		
		System.out.println("����һ���ļ�������Ŀ¼�������� (C:\\Users\\admin\\Documents\\java)");
		filePath = input.next();
		File file = new File(filePath);
		
		if(file.exists()) {
			System.out.println("Ŀ�����ƣ�" + file.getName());
			System.out.println("----------------------------");
			if(file.isDirectory()) {
				System.out.println("Ŀ�����ͣ�Ŀ¼");
				System.out.println("����λ�ã�" + file.getPath());
				System.out.println("Ŀ¼��С��" + getAllFilesByte(file) + "�ֽ�");
				System.out.println("�޸�ʱ�䣺" + sdf.format(file.lastModified()));
				System.out.println("�����ļ���" + getAllFiles(file) + "��");
				System.out.println("����Ŀ¼��" + getAllDirectorys(file) + "��");
				System.out.println("Ŀ�����ԣ�");
				System.out.println("        ��д��" + file.canWrite());
				System.out.println("        �ɶ���" + file.canRead());
				System.out.println("        �����У�" + file.canExecute());
				System.out.println("        ���أ�" + file.isHidden());
			}else {
				String type = file.getName().substring(file.getName().lastIndexOf("."), file.getName().length());
				System.out.println("Ŀ�����ͣ�" + type);
				System.out.println("����λ�ã�" + file.getParent());
				System.out.println("�ļ���С��" + file.length());
				System.out.println("�޸�ʱ�䣺" + sdf.format(file.lastModified()));
				System.out.println("Ŀ�����ԣ�");
				System.out.println("        ��д��" + file.canWrite());
				System.out.println("        �ɶ���" + file.canRead());
				System.out.println("        �����У�" + file.canExecute());
				System.out.println("        ���أ�" + file.isHidden());
			}
			System.out.println("----------------------------");
		}else {
			System.out.printf("[%s]�����ļ���Ŀ¼���ƣ�", filePath);
		}

	}

	static int getAllFilesByte(File directory) {
		// TODO Auto-generated method stub
		int sum = 0;
		
		File[] sonFiles = directory.listFiles();
		
		for(int i = 0; i < sonFiles.length; i++) {
			if(sonFiles[i].isDirectory()) {
				getAllFilesByte(sonFiles[i]);
			}else {
				sum += sonFiles[i].length();
			}
		}
		return sum;
	}
	
	static int getAllDirectorys(File directory) {
		int sum = 0;
		
		File[] sonFiles = directory.listFiles();
		
		for(int i = 0; i < sonFiles.length; i++) {
			if(sonFiles[i].isDirectory()) {
				sum++;
				sum += getAllDirectorys(sonFiles[i]);				
			}
		}
		return sum;
	}
	
	static int getAllFiles(File directory) {
		
		int sum = 0;
		
		File[] sonFiles = directory.listFiles();
		
		for(int i = 0; i < sonFiles.length; i++) {
			if(sonFiles[i].isFile()) {
				sum++;
			}else {
				sum += getAllFiles(sonFiles[i]);
			}
		}
		return sum;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
