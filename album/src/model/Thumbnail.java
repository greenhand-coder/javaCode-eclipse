package model;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;

import action.OpenAction;
import controller.*;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import service.MouseEvenHandler;

public class Thumbnail extends Label{       //图片缩略图类
	private MainUIController mainScene;
	private FolderFile folderFile; 
	private Image image;
	private ImageView imageView;
	private Text pictureName;
	private Thumbnail thumbnail = this;
	
	public BooleanProperty selected = new SimpleBooleanProperty();
	
	protected static ArrayList<File> selectedPictureFiles = new  ArrayList<>();
	protected static ArrayList<Thumbnail> selectedPictures = new ArrayList<>();
	protected static ArrayList<Thumbnail> cutedPictures = new ArrayList<>();
	
	public Thumbnail(FolderFile folderFile,MainUIController mainUIController) throws MalformedURLException {	
		this.folderFile = folderFile;
		this.mainScene = mainUIController;
		initData();
		addThumbnailListener();
		new MyContextMenu(this,mainScene,true);
	}
	private void initData() throws MalformedURLException{
		this.setGraphicTextGap(5);
		this.setPadding(new Insets(1, 1, 1, 1));
		this.setContentDisplay(ContentDisplay.TOP);
		this.setPrefSize(110,110);
		
		this.image = new Image(folderFile.getfolderFile().toURI().toURL().toString(),100,100,true,true);
		this.imageView = new ImageView(image);
		this.pictureName = new Text(folderFile.getfolderName());  //pictureName 就是最下级的folderName
		this.setText(pictureName.getText());
		this.setGraphic(imageView);
	}
	
	

	public Image getImage() {
		try {
			return image = new Image(folderFile.getfolderFile().toURI().toURL().toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return image;
		
	}
	public File getImageFile() {
		return this.folderFile.getfolderFile();
	}                                              
	public FolderFile getPictureFile() {
		return folderFile;
	}
	public String getURL() {
		try {
			return this.folderFile.getfolderFile().toURI().toURL().toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public ImageView getImageView() {
		return this.imageView;
	}
	
	public static ArrayList<File> getSelectedPictureFiles() {
		return selectedPictureFiles;
	}               //返回被选中的照片，用来复制
	public static ArrayList<Thumbnail> getSelectedPictures() {
		return selectedPictures;
	}               
	public static void setCutedPictures(ArrayList<Thumbnail> cutedPictures) {
		Thumbnail.cutedPictures = cutedPictures;
	}
	public static void addCutedPictures(Thumbnail thumbnail){
		Thumbnail.cutedPictures.add(thumbnail);
	}
	public static ArrayList<Thumbnail> getCutedPictures() {
		return cutedPictures;
	}
	public static void clearCutedPictures() {
		cutedPictures.removeAll(cutedPictures);
	}
	
	
	public void setSelected(boolean value) {
		boolean istrue = selected.get();
		selected.set(value);
		if (selected.get() && !istrue)
			selectedPictures.add(this);
		else if (istrue && !selected.get())
			selectedPictures.remove(this);
		System.out.println(selectedPictures.size());
		mainScene.getTextTwo().setText("已选中 "+selectedPictures.size()+" 张图片");
	}
	
	public static void clearSelected() {
		for (Thumbnail pNode : selectedPictures) {
			pNode.selected.set(false);
		}
		selectedPictures.removeAll(selectedPictures);
	}
	
	private void addThumbnailListener() {
		selected.addListener(new InvalidationListener() {
			@Override
			public void invalidated(Observable observable) {
				if(selected.get()) {
					thumbnail.setStyle("-fx-background-color:lightgray;");
					mainScene.getTextTwo().setText("已选中 0  张图片");
				}else {
					thumbnail.setStyle("-fx-background-color:transparent;");
					mainScene.getTextTwo().setText("已选中 0  张图片");
				}
			}
		});
		this.setOnMouseEntered((MouseEvent e) -> {
			if (!selected.get())
				this.setStyle("-fx-background-color:lightgray;"); 
			    
		});
		this.setOnMouseExited((MouseEvent e) -> {
			if (!selected.get())
				this.setStyle("-fx-background-color:transparent;");
			    
		});
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseEvenHandler(this,folderFile));
	}
	
	public void openAction() {
		mainScene.setList();
		new OpenAction();
	}

	
}