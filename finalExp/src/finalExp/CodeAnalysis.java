package finalExp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
 

public class CodeAnalysis {
	/*
     * 总数据大小
     */
	private static int bytes = 0;
	/*
	 * 定义一个标记数记录string行数
	 */
	private static int levelOfString = 1;
 
    /*
     * 统计的文件数量
     */
    private static long files = 0;
    /**
     * 代码行数
     */
    private static int codeLines = 0;
    /*
     * 注释行数
     */
    private static int commentLines = 0;
    /*
     * 空行数量
     */
    private static int blankLines = 0;
    /*
     * 文件数组
     */
    private static ArrayList<File> fileArray = new ArrayList<File>();
    public static ArrayList<String> stringArray = new ArrayList<String>();
 
    /*
     * 函数功能：统计指定目录下(文件夹中)java文件中的代码行数
     */
    public static void Analysis(String filePath) {
        String path = filePath;
        
 
        ArrayList<File> al = getFile(new File(path));  //生成文件数组
        ArrayList<String> strings = formatPrint(new File(path));
        al.add(0, new File(path));   //加上因为递归引起的第一个没有加入进ArrayList
        strings.add(0, "+" + new File(path).getName());
        strings.add(0, "Files detail");
        strings.add(0, String.format("[%s] Result", path));
        strings.add("Total:");
        for (File f : al) {       	
        	
            if (f.getName().matches(".*\\.java$")) {
            	int a[] = count(f);
//            	System.out.println("Total:     "+ (a[0] + a[1] + a[2]) + ", Blank:     " 
//            	+ a[2] + ",        "+ f.length() + "Bytes");  //输出文件名后面的东西
                bytes += f.length();   //在此求得文件总大小
            }
            
        }
        

        strings.add(String.format("%8d Java文件数", files / 2));
        strings.add(String.format("%8d 空白行数" , blankLines / 2));
        strings.add(String.format("%8d 总行数" , (codeLines + commentLines + blankLines) / 2));
        strings.add(String.format("%8d 文件总大小" , bytes ));

//        System.out.println("代码行数：" + codeLines / 2);
//        System.out.println("注释行数：" + commentLines / 2);

//        
//        for(int i = 0; i <stringArray.size(); i++) {
//        	System.out.println(stringArray.get(i));
//        }
       //取消注释可以查看strings里面的储存情况
        
        outputResult(filePath, strings);
        
    }
    
    /*
     * 函数功能：递归打印文件
     */
    public static ArrayList<String> formatPrint(File f) {
//    	for(int i = 0; i < fileArray.size(); i++) {    //解除注释可以查看文件List收纳顺序
//    		System.out.println(fileArray.get(i).getName());
//    	}
// 
    	
    	ArrayList<File> fl = new ArrayList<>(Arrays.asList(f.listFiles()));
    	Collections.sort(fl, comparator);

    	
    	if(fl != null) {
    		String space = "";

    		for(File child : fl) {
    			
    			if(child.isDirectory()) {
    				space = "";
    				for(int i = 0; i < 4 * levelOfString; i++) {
    	    			space += " "; 
    	    		}
    				levelOfString ++;
    				stringArray.add(space + "+" + child.getName());
    				formatPrint(child);
    				levelOfString --;
    			}else {
    				space = "";
    				for(int i = 0; i < 4 * levelOfString; i++) {
    	    			space += " "; 
    	    		}
    				int a[] = count(child);
    				stringArray.add(space + "-" + String.format("%-40s", child.getName()) + "Total:     "+ String.format("%5d", (a[0] + a[1] + a[2])) + ", Blank:     " 
    		            	+ String.format("%3d", a[2]) + ",        "+ child.length() + "Bytes");
    			}
    		}
    	}
    	
    	
    	return stringArray;
    	
    }
    
 
    /*
     * 函数功能：获得目录下的文件和子目录下的文件，仅仅生成文件数组，无任何其他操作
     */
    private static ArrayList<File> getFile(File f) {
    	 
    	
        
        List<File> fl = new ArrayList<File>(Arrays.asList(f.listFiles()));
        Collections.sort(fl, comparator);
        //no bug 
        
        if (fl != null) {
            for (File child : fl) {
                if (child.isDirectory()) {
                	fileArray.add(child);   //顺序增加，非层级,formatPrint中的注释代码可以验证
                    getFile(child);   //递归生成文件树
                } else {
                    fileArray.add(child);
                }
            }
        }
        return fileArray;
    }
 
    /*
     * 函数功能：统计具体java文件中的代码行数
     */
    private static int[] count(File f) {
    	int a[] = new int[3];//a[0]放Code  a[1]放comment  a[2]放Blank
        BufferedReader br = null;
        boolean flag = false;  //用于标记不同行的/* */注释
        try {
            br = new BufferedReader(new FileReader(f));
            String line = "";
            while ((line = br.readLine()) != null) {
                // 除去注释前的空格
                line = line.trim();
                // 匹配空行
                if (line.matches("^[ ]*$")) {
                    blankLines++;a[2]++;                    
                } else if (line.startsWith("//")) {
                    commentLines++;a[1]++;
                } else if (line.startsWith("/*") && !line.endsWith("*/")) {
                    commentLines++;a[1]++;
                    flag = true;
                } else if (line.startsWith("/*") && line.endsWith("*/")) {
                    commentLines++;a[1]++;
                } else if (flag) {
                    commentLines++;a[1]++;
                    if (line.endsWith("*/")) {
                        flag = false;
                    }
                } else {
                    codeLines++;a[0]++;
                }
            }
            files++;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return a;
    }
    
    /*
     * 函数功能：输出strings到文件
     */
    public static void outputResult(String originFilePath, ArrayList<String> strings) {    //已验证可以使用
		
		File file = new File("result");   //用result目录存档分析结果
		
		if(file.mkdir()) {
			System.out.println("result目录创建成功");
		}else if(file.isDirectory()) {
			System.out.println("result目录已存在,请直接选择2选项");
		}else {
			System.out.println("创建失败，result可能不是目录");
		}
		
		String goalFilePath = "result\\" + new File(originFilePath).getName() + ".txt";
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(goalFilePath))){
			for(String s:strings) {
				bw.write(s);
				bw.newLine();
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		

		
	}
    
    static Comparator<File> comparator = new Comparator<File>() {   //内部类比较器
    	
     	  @Override
     	  public int compare(File o1, File o2) {
     	   /*
     	    * 根据File是 路径和字典优先 进行排序
     	    * 
     	    */
     	   if (o1.isDirectory() && o2.isFile())
     	    return -1;
     	   if (o1.isFile() && o2.isDirectory())
     	    return 1;
     	   return o1.getName().compareTo(o2.getName());
     	  }
     	 };
    

}
