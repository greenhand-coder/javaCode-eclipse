package model;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class FolderFile {  //文件数组，将普通文件数组内的属性变为此类数组的属性
	
	private String folderName;
	private File folderFile;
	private URL folderURL;
	
	public FolderFile(File folderFile) {
		this.folderFile = folderFile;
		folderName = folderFile.getName();
		if(folderName.equals("")) {  //盘符没有文件名 Name = 盘符路径
			folderName = folderFile.getPath();
		}
	}
	
    public FolderFile(String folderPath) {  //如果接收的是文件路径，那么把文件路径转化成文件再用第一个构造方法
    	this(new File(folderPath));
    }
    
    /*
             *       创建folderFile下的子目录数组
             *       详解 ：此类初始化后属性File folderFile 有值 ，创建此属性下级（先判断是否有下级才调用）的目录数组，再把此数组转化成FolderFile数组，
             *       后续可以再把数组里面每个组员再调用此方法，形成递归  可单独拿出来放一个FolderFiles类里
     */
    public FolderFile[] listFolderFiles() {     //创建folderFile下的子目录数组，
		File[] files = this.folderFile.listFiles();  //listFile:返回某个目录下所有文件和目录的绝对路径，返回的是File数组
		if(files == null||files.length == 0) {
			return null;
		}
		FolderFile[] folderFiles = new FolderFile[files.length];
		for(int i = 0; i<files.length;i++) {
			folderFiles[i] = new FolderFile(files[i]);
		}
		return folderFiles;
	}
    
    public URL toURL() {
    	return folderURL;
    }
    
    public boolean isPicture() {
    	if(folderName.toLowerCase().endsWith(".jpg")||
    	   folderName.toLowerCase().endsWith(".jpge")||
    	   folderName.toLowerCase().endsWith(".png")||
    	   folderName.toLowerCase().endsWith(".bmp")||
    	   folderName.toLowerCase().endsWith(".gif")	) {
    		return true;
    	}
    	return false;
    }
    
    public boolean isDirectory() {
    	return folderFile.isDirectory();
    }
    
    public boolean isFile() {
    	return folderFile.isFile();
    }
    
    public boolean isHidden() {
    	return folderFile.isHidden();
    }
    
    public long length() {
    	return folderFile.length();
    }
    
    public String toString() {
		return folderName;
	}   
    
 //------------------getting and setting-----------

    public File getfolderFile() {
		return folderFile;
	}
    public String getfolderName() {
		return folderName;
	}
}

