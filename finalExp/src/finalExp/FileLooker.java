package finalExp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileLooker {
	
	/*
	 * 函数功能：读取分析结果
	 */
	
	public static ArrayList<String> readFile(String filePath) {
		
		ArrayList<String> strings = new ArrayList<>();
		File file = new File(filePath);
		
		if(!file.exists()) {
			return null;
		}
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))){
			String line = "";
			while ((line = br.readLine()) != null) {
				strings.add(line);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return strings;
	}
	

}
