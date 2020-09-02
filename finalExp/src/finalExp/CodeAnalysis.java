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
     * �����ݴ�С
     */
	private static int bytes = 0;
	/*
	 * ����һ���������¼string����
	 */
	private static int levelOfString = 1;
 
    /*
     * ͳ�Ƶ��ļ�����
     */
    private static long files = 0;
    /**
     * ��������
     */
    private static int codeLines = 0;
    /*
     * ע������
     */
    private static int commentLines = 0;
    /*
     * ��������
     */
    private static int blankLines = 0;
    /*
     * �ļ�����
     */
    private static ArrayList<File> fileArray = new ArrayList<File>();
    public static ArrayList<String> stringArray = new ArrayList<String>();
 
    /*
     * �������ܣ�ͳ��ָ��Ŀ¼��(�ļ�����)java�ļ��еĴ�������
     */
    public static void Analysis(String filePath) {
        String path = filePath;
        
 
        ArrayList<File> al = getFile(new File(path));  //�����ļ�����
        ArrayList<String> strings = formatPrint(new File(path));
        al.add(0, new File(path));   //������Ϊ�ݹ�����ĵ�һ��û�м����ArrayList
        strings.add(0, "+" + new File(path).getName());
        strings.add(0, "Files detail");
        strings.add(0, String.format("[%s] Result", path));
        strings.add("Total:");
        for (File f : al) {       	
        	
            if (f.getName().matches(".*\\.java$")) {
            	int a[] = count(f);
//            	System.out.println("Total:     "+ (a[0] + a[1] + a[2]) + ", Blank:     " 
//            	+ a[2] + ",        "+ f.length() + "Bytes");  //����ļ�������Ķ���
                bytes += f.length();   //�ڴ�����ļ��ܴ�С
            }
            
        }
        

        strings.add(String.format("%8d Java�ļ���", files / 2));
        strings.add(String.format("%8d �հ�����" , blankLines / 2));
        strings.add(String.format("%8d ������" , (codeLines + commentLines + blankLines) / 2));
        strings.add(String.format("%8d �ļ��ܴ�С" , bytes ));

//        System.out.println("����������" + codeLines / 2);
//        System.out.println("ע��������" + commentLines / 2);

//        
//        for(int i = 0; i <stringArray.size(); i++) {
//        	System.out.println(stringArray.get(i));
//        }
       //ȡ��ע�Ϳ��Բ鿴strings����Ĵ������
        
        outputResult(filePath, strings);
        
    }
    
    /*
     * �������ܣ��ݹ��ӡ�ļ�
     */
    public static ArrayList<String> formatPrint(File f) {
//    	for(int i = 0; i < fileArray.size(); i++) {    //���ע�Ϳ��Բ鿴�ļ�List����˳��
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
     * �������ܣ����Ŀ¼�µ��ļ�����Ŀ¼�µ��ļ������������ļ����飬���κ���������
     */
    private static ArrayList<File> getFile(File f) {
    	 
    	
        
        List<File> fl = new ArrayList<File>(Arrays.asList(f.listFiles()));
        Collections.sort(fl, comparator);
        //no bug 
        
        if (fl != null) {
            for (File child : fl) {
                if (child.isDirectory()) {
                	fileArray.add(child);   //˳�����ӣ��ǲ㼶,formatPrint�е�ע�ʹ��������֤
                    getFile(child);   //�ݹ������ļ���
                } else {
                    fileArray.add(child);
                }
            }
        }
        return fileArray;
    }
 
    /*
     * �������ܣ�ͳ�ƾ���java�ļ��еĴ�������
     */
    private static int[] count(File f) {
    	int a[] = new int[3];//a[0]��Code  a[1]��comment  a[2]��Blank
        BufferedReader br = null;
        boolean flag = false;  //���ڱ�ǲ�ͬ�е�/* */ע��
        try {
            br = new BufferedReader(new FileReader(f));
            String line = "";
            while ((line = br.readLine()) != null) {
                // ��ȥע��ǰ�Ŀո�
                line = line.trim();
                // ƥ�����
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
     * �������ܣ����strings���ļ�
     */
    public static void outputResult(String originFilePath, ArrayList<String> strings) {    //����֤����ʹ��
		
		File file = new File("result");   //��resultĿ¼�浵�������
		
		if(file.mkdir()) {
			System.out.println("resultĿ¼�����ɹ�");
		}else if(file.isDirectory()) {
			System.out.println("resultĿ¼�Ѵ���,��ֱ��ѡ��2ѡ��");
		}else {
			System.out.println("����ʧ�ܣ�result���ܲ���Ŀ¼");
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
    
    static Comparator<File> comparator = new Comparator<File>() {   //�ڲ���Ƚ���
    	
     	  @Override
     	  public int compare(File o1, File o2) {
     	   /*
     	    * ����File�� ·�����ֵ����� ��������
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
