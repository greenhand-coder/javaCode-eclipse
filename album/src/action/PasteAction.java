package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import controller.MainUIController;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
import model.FolderFile;
import model.Thumbnail;

public class PasteAction {
	MainUIController mainUIController;
	public PasteAction(MainUIController mainUI) {
		this.mainUIController = mainUI;
		Clipboard clipboard = Clipboard.getSystemClipboard();
		List<File> files = (List<File>) (clipboard.getContent(DataFormat.FILES));	//把剪切板上的内容放到files
		if (files.size() <= 0) {
			return;
		}
		if (Thumbnail.getCutedPictures().size() > 0) {
			File first = files.get(0);
			if(first.getParentFile().getAbsolutePath().compareTo(MainUIController.theFilePath) == 0){
				for(Thumbnail pNode : Thumbnail.getCutedPictures()) {
					pNode.getImageView().setEffect(null);
				}
				Thumbnail.getCutedPictures().clear();
				return;	
			}
		}
		Thumbnail.clearSelected();
		Thumbnail.getSelectedPictureFiles().clear();
		clipboard.clear();
		for(File oldFile : files) {
			String newName = Pasterename(MainUIController.theFilePath,oldFile.getName());
			File newFile = new File(MainUIController.theFilePath+File.separator+newName);
			try {
				newFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(newFile.exists()) {
				try {
					copyFile(oldFile,newFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			try {
				mainUI.getPictures().add(new Thumbnail(new FolderFile(newFile), mainUIController));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(Thumbnail.getCutedPictures().size()>0) {
				oldFile.delete();
			}
			
			mainUIController.showPicture();
		}
		clipboard.clear();
	}
	
	private void copyFile(File fromFile, File toFile) throws IOException {
		FileInputStream inputStream = new FileInputStream(fromFile);
		FileOutputStream outputStream = new FileOutputStream(toFile);
		byte[] b = new byte[1024];
		int byteRead;
		while ((byteRead = inputStream.read(b)) > 0) {
			outputStream.write(b, 0, byteRead);
		}
		inputStream.close();
		outputStream.close();
	}
	
	private String Pasterename(String theFilePath, String oldname) {
		String newName = oldname;
		File fatherPathFile = new File(theFilePath);
		File[] filesInFatherPath = fatherPathFile.listFiles();
		for (File fileInFatherPath : filesInFatherPath) {
			String fileName = fileInFatherPath.getName();
			if ((newName.compareTo(fileName))==0) {										
				String str = null;						//中间变量
				int start = newName.lastIndexOf("_副本");
				int end = newName.lastIndexOf(".");
				if (start != -1) {
					str = newName.substring(start, end);
					int num = 0;
					try {
						num = Integer.parseInt(str.substring(str.lastIndexOf("_副本") + 3)) + 1;
						int cnt = 0, d = num - 1;
						while (d != 0) {
							d /= 10;
							cnt++;
						}
						newName = newName.substring(0, end - cnt) + num + newName.substring(end);
					} catch (Exception e) {
						newName = newName.substring(0, end) + "_副本1" + newName.substring(end);
					}

				} else {
					newName = newName.substring(0, end) + "_副本1" + newName.substring(end);
				}
			}
		}
		return newName;
	}
	
}
