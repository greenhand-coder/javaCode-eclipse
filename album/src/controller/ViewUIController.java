package controller;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Button;

import javafx.scene.text.Text;
import model.Thumbnail;
import service.ChangeService;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.ws.Holder;

import action.SlideShowAction;
import action.FilterAction;
import action.EnlargeSmallAction;
import action.MainAction;
import action.OpenAction;
import action.PageTurningAction;
import action.RotateAction;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.control.Menu;
import javafx.scene.control.ToolBar;

public class ViewUIController implements Initializable{
	@FXML
	private HBox hbox;
	@FXML
	private ImageView imageView;
	@FXML
	private Button enlargeBtn;
	@FXML
	private Button smallBtn;
	@FXML
	private Button rotateBtn;
	@FXML
	private Button filterBtn;
	@FXML
	private Menu MenuMore;
	@FXML
	private Menu MenuHelp;
	@FXML
	private Button forwardImageBtn;
	@FXML
	private Button nextImageBtn;
	@FXML
	private ToolBar toolbar;
	private ViewUIController viewUIController;
	private Image image;
	private File[] file;
	private int changenumber = 0;
	private int page = 0;

    public ViewUIController() {
		viewUIController = this;

	}
    @Override
	public void initialize(URL location, ResourceBundle resources) {//初始化
		initData();
	}
    private void initData() {
    	
		image = new Image(Thumbnail.getSelectedPictures().get(0).getURL());
		
		ChangeService.origin=new ImageView(image);
		ChangeService.change=new ImageView(image);
		imageView.setPreserveRatio(true);//保持缩放比例
		imageView.setSmooth(true);//
    	imageView.setImage(image);
    	toolbar.setVisible(true);
    }
    
    public ImageView getImageView() {
		return imageView;
	}
    @FXML
    private void Back(ActionEvent e) {
    	new  MainAction();
    }
    @FXML
    private void toolBarShowHide() {//出现还是隐藏工具栏
    	if(toolbar.isVisible()) {
    		toolbar.setVisible(false);
    	}
    	else {
    		toolbar.setVisible(true);
    	}
    }
    @FXML
    private void SildeShowAction(ActionEvent e) {
    	new SlideShowAction();
    }
    @FXML
    private void invert(ActionEvent e) {
    	if (imageView.getNodeOrientation().name().equals("RIGHT_TO_LEFT")) {
			imageView.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
		} else {
			imageView.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		}
    }
	// Event Listener on Button[#enlargeBtn].onAction
	@FXML
	public void enlargeAction(ActionEvent event) {
		EnlargeSmallAction.enlarge(imageView);
		
	}
	// Event Listener on Button[#smallBtn].onAction
	@FXML
	public void smallAction(ActionEvent event) {
		EnlargeSmallAction.small(imageView);
	}
	@FXML
	public void rotateAction(ActionEvent event) {
		new RotateAction(imageView);
	}
	@FXML
	public void filterAction(ActionEvent event) {
		ChangeService.change=this.imageView;
		new FilterAction();
		
	}
	@FXML
	public void forwardAction(ActionEvent event) {
		PageTurningAction.changePicture(imageView, false);
	}
	@FXML
	public void nextAction(ActionEvent event) {
	   PageTurningAction.changePicture(imageView, true);
	}
}
