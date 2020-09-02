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

public class Thumbnail extends Label{
	private MainUIController mainScene;
	private FolderFile pictureFile; 
	private Image image;
	private ImageView imageView;
	private Text pictureName;
	private Thumbnail pictureNode = this;
	
	public BooleanProperty selected = new SimpleBooleanProperty();
	
	protected static ArrayList<File> selectedPictureFiles = new  ArrayList<>();
	protected static ArrayList<Thumbnail> selectedPictures = new ArrayList<>();
	protected static ArrayList<Thumbnail> cutedPictures = new ArrayList<>();
	
	public Thumbnail(FolderFile pictureFile,MainUIController mainUIController) throws MalformedURLException {	
		this.pictureFile = pictureFile;
		this.mainScene = mainUIController;
		initData();
		addPictureNodeListener();
		new MyContextMenu(this,mainScene,true);
	}
	private void initData() throws MalformedURLException{
		this.setGraphicTextGap(5);
		this.setPadding(new Insets(1, 1, 1, 1));
		this.setContentDisplay(ContentDisplay.TOP);
		this.setPrefSize(110,110);
		
		this.image = new Image(pictureFile.getImageFile().toURI().toURL().toString(),100,100,true,true);
		this.imageView = new ImageView(image);
		this.pictureName = new Text(pictureFile.getImageName());
		this.setText(pictureName.getText());
		this.setGraphic(imageView);
	}
	
	

	public Image getImage() {
		try {
			return image = new Image(pictureFile.getImageFile().toURI().toURL().toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return image;
		
	}
	public File getImageFile() {
		return this.pictureFile.getImageFile();
	}
	public FolderFile getPictureFile() {
		return pictureFile;
	}
	public String getURL() {
		try {
			return this.pictureFile.getImageFile().toURI().toURL().toString();
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
	}
	public static ArrayList<Thumbnail> getSelectedPictures() {
		return selectedPictures;
	}
	public static void setCutedPictures(ArrayList<Thumbnail> cutedPictures) {
		Thumbnail.cutedPictures = cutedPictures;
	}
	public static void addCutedPictures(Thumbnail pNode){
		Thumbnail.cutedPictures.add(pNode);
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
	
	private void addPictureNodeListener() {
		selected.addListener(new InvalidationListener() {
			@Override
			public void invalidated(Observable observable) {
				if(selected.get()) {
					pictureNode.setStyle("-fx-background-color:lightblue;");
//					mainScene.getText().setText("");
					mainScene.getTextTwo().setText("已选中 0  张图片");
				}else {
					pictureNode.setStyle("-fx-background-color:transparent;");
//					System.out.println(selectedPictures.size()+"--");
					mainScene.getTextTwo().setText("已选中 0  张图片");
				}
			}
		});
		this.setOnMouseEntered((MouseEvent e) -> {
			if (!selected.get())
				this.setStyle("-fx-background-color:PaleTurquoise;"); 
//			    mainScene.getText().setText("");
			    
		});
		this.setOnMouseExited((MouseEvent e) -> {
			if (!selected.get())
				this.setStyle("-fx-background-color:transparent;");
			    
		});
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, new MouseEvenHandler(this,pictureFile));
	}
	
	public void openAction() {
		mainScene.setList();
		new OpenAction();
//	  System.out.println(selectedPictureFiles.size()+"--");
	}

	
}