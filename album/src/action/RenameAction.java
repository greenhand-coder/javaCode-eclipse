package action;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;

import controller.MainUIController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.FolderFile;
import model.Thumbnail;

public class RenameAction {
	private MainUIController mainUIController;
	private Stage renameStage;
	private Label tip;										//标签
	private Button submit;									//提交按钮
	private GridPane grid;									//布局表格
	private boolean single;									//单张改名 or 多张改名
	final private TextField name = new TextField();			//名称
	final private TextField startNum = new TextField();		//起始编号
	final private TextField bitNum = new TextField();		//编号位数
															
	public RenameAction(MainUIController mainUI) {			//ReRenameAction(MainUIController mainUI)
		this.mainUIController = mainUI;
		isSingle();
		renameStage = new Stage();
		grid = new GridPane();            
		tip = new Label();
		submit = new Button("完成");
		setStage();
	}
	
	private void isSingle() {
		if(Thumbnail.getSelectedPictures().size()==1) 
			 single = true;
		else 
			 single = false;
	}
	
	private void setStage() {							//重命名stage setStage()
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);
		Label label1 = new Label("名称");
		GridPane.setConstraints(label1, 0, 0);
		name.setPromptText("请输入新名字");
		name.setPrefColumnCount(20);
		name.getText();
		GridPane.setConstraints(name, 1, 0);
		grid.getChildren().addAll(label1,name);
		
		if(single) {										//单张图片改名
			GridPane.setConstraints(tip, 0, 1);				//空行
			GridPane.setConstraints(submit, 0, 2);			//完成按钮
			grid.getChildren().addAll(submit,tip);
		}else {												//多张图片改名
			Label label2 = new Label("起始编号");
			GridPane.setConstraints(label2, 0, 1);
			startNum.setPromptText("请输入起始编号");
			startNum.setPrefColumnCount(15);
			startNum.getText();
			GridPane.setConstraints(startNum, 1, 1);
			
			Label label3 = new Label("编号位数");
			GridPane.setConstraints(label3, 0, 2);
			bitNum.setPromptText("请输入编号位数");
			bitNum.setPrefColumnCount(10);
			bitNum.getText();
			GridPane.setConstraints(bitNum, 1, 2);
			
			GridPane.setConstraints(tip, 1, 3);				//空行
			GridPane.setConstraints(submit, 0, 4);			//完成按钮
			grid.getChildren().addAll(label2,startNum,label3,bitNum,submit,tip);
		}
		
		submit.setOnAction(new EventHandler<ActionEvent>() {		//submit
			@Override
			    public void handle(ActionEvent e) {
					if(single) {
						if ((name.getText() != null && !name.getText().isEmpty())) {
				        	if(renameSingle()) {						//renameSingle()
				        		renameStage.close();
				        	}else{
				        		tip.setText("图片文件重命名失败，请检查是否有同名文件！");
				        	}
				        } else {
				            tip.setText("没有检测到输入，请重新输入!");
				        }
					}else {
						if ((name.getText() != null && !name.getText().isEmpty())
							&&(startNum.getText() != null && !startNum.getText().isEmpty())
							&&(bitNum.getText() != null && !bitNum.getText().isEmpty())) {
				        	if(renameMore()) {							//renameMore()
				        		renameStage.close();
				        	}else {
				        		tip.setText("错误!请使用其他参数再试一次。");
				        	}
				        } else {
				            tip.setText("您还没有完成信息!");
				        }
					}
			     }
			 });
		 Scene scene=new Scene(grid);
		 renameStage.setTitle("重命名");
		 renameStage.setScene(scene);
		 renameStage.show();
	}
	
	private boolean renameSingle() {
		Thumbnail pNode = Thumbnail.getSelectedPictures().get(0);
		File file = pNode.getImageFile();
		String prefix = file.getParent();
    	String[] strings = file.getName().split("\\.");
    	String suffix = strings[strings.length-1];					
    	File tmpfile = new File(prefix+"\\"+name.getText()+"."+suffix);
    	if(!file.renameTo(tmpfile)) {
			tmpfile.delete();
			return false;
		}
		pNode.setSelected(false);
		mainUIController.removePictures(pNode);
		Thumbnail aNode;
		try {
			aNode = new Thumbnail(new FolderFile(tmpfile), mainUIController);
			aNode.setSelected(true);
		    mainUIController.addPictures(aNode);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		mainUIController.showPicture();
		return true;
	}
	
	private boolean renameMore() {
		File file;
		int id = Integer.valueOf(startNum.getText());
		int bit = Integer.valueOf(bitNum.getText());
		if( id<0 || (id + Thumbnail.getSelectedPictures().size()) >= (int)Math.pow(10, bit)) 
			return false;
		ArrayList<Thumbnail> oldList = new ArrayList<>();
		ArrayList<Thumbnail> newList = new ArrayList<>();
		for (Thumbnail image : Thumbnail.getSelectedPictures()) {
			file = image.getImageFile();
			String prefix = file.getParent();
	    	String[] strings = file.getName().split("\\.");
	    	String suffix = strings[strings.length-1];
	    	String newName = name.getText();
	    	int tid=id,count=0;
			if(id!=0) {
				while(tid!=0) {
					count++;
	    			tid=tid/10;
				}
			}
			else 
				count++;
			while(bit>count) {
				newName+=0;
				count++;
			}
			newName+=id;
	    	File tmpfile = new File(prefix+"\\"+newName+"."+suffix);
	    	if(!file.renameTo(tmpfile)){//可能存在失败的情况,如名字重复
				tmpfile.delete();
				return false;
			}
			oldList.add(image);	
			Thumbnail newImage;
			try {
				newImage = new Thumbnail(new FolderFile(tmpfile), mainUIController);
				newList.add(newImage);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			
	    	id++;
		}
		for(int i=0;i<oldList.size();i++) {
			oldList.get(i).setSelected(false);
			mainUIController.removePictures(oldList.get(i));
			newList.get(i).setSelected(true);
			mainUIController.addPictures(newList.get(i));
		}
		mainUIController.showPicture();
		return true;
	}

}
