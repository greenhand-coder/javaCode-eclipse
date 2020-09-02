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
		
		
		System.out.println("输入一个文件夹名或目录名字形如 (C:\\Users\\admin\\Documents\\java)");
		filePath = input.next();
		File file = new File(filePath);
		
		if(file.exists()) {
			System.out.println("目标名称：" + file.getName());
			System.out.println("----------------------------");
			if(file.isDirectory()) {
				System.out.println("目标类型：目录");
				System.out.println("所在位置：" + file.getPath());
				System.out.println("目录大小：" + getAllFilesByte(file) + "字节");
				System.out.println("修改时间：" + sdf.format(file.lastModified()));
				System.out.println("包含文件：" + getAllFiles(file) + "个");
				System.out.println("包含目录：" + getAllDirectorys(file) + "个");
				System.out.println("目标属性：");
				System.out.println("        可写：" + file.canWrite());
				System.out.println("        可读：" + file.canRead());
				System.out.println("        可运行：" + file.canExecute());
				System.out.println("        隐藏：" + file.isHidden());
			}else {
				String type = file.getName().substring(file.getName().lastIndexOf("."), file.getName().length());
				System.out.println("目标类型：" + type);
				System.out.println("所在位置：" + file.getParent());
				System.out.println("文件大小：" + file.length());
				System.out.println("修改时间：" + sdf.format(file.lastModified()));
				System.out.println("目标属性：");
				System.out.println("        可写：" + file.canWrite());
				System.out.println("        可读：" + file.canRead());
				System.out.println("        可运行：" + file.canExecute());
				System.out.println("        隐藏：" + file.isHidden());
			}
			System.out.println("----------------------------");
		}else {
			System.out.printf("[%s]不是文件或目录名称！", filePath);
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
