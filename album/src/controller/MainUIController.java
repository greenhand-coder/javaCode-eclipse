package controller;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import model.FolderTree;
import model.MyContextMenu;
import model.FolderFile;
import model.Thumbnail;
import service.ChangeService;
import service.PaneListener;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import action.CopyAction;
import action.DeleteAction;
import action.OpenAction;
import action.PasteAction;
import action.RenameAction;
import action.SlideShowAction;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.layout.FlowPane;

import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainUIController implements Initializable {

	private MainUIController mainUI;
	private ArrayList<Thumbnail> pictures;
	private ArrayList<File> files;
	public static String theFilePath;
	
	@FXML
	private TreeView<FolderFile> treeview;
	@FXML
	private FlowPane flowPane;
	@FXML
	private Text text1;
	@FXML
	private Text text2;
	@FXML
	private ToolBar toolBar;
	@FXML
	private Button openBtn;
	@FXML
	private Button copyBtn;
	@FXML
	private Button pasteBtn;
	@FXML
	private Button deleteBtn;
	@FXML
	private Button renameBtn;
	@FXML
	private Button PPT;
	
    public void setList() {
    	files=new ArrayList<File>();
    	for(int i=0;i<pictures.size();i++) {
    		files.add(pictures.get(i).getImageFile());
    	}
    	ChangeService.files=files;
    }
	public MainUIController() {
		mainUI = this;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initData();
	}

	public void initData() {
		pictures = new ArrayList<>();
		treeview = new FolderTree(mainUI, treeview).gettreeView();
		new PaneListener(flowPane,mainUI);
		new MyContextMenu(flowPane, mainUI,false);
		openBtn.setTooltip(new Tooltip("打开"));
		copyBtn.setTooltip(new Tooltip("复制"));
		pasteBtn.setTooltip(new Tooltip("粘贴"));
		deleteBtn.setTooltip(new Tooltip("删除"));
		renameBtn.setTooltip(new Tooltip("重命名"));
		PPT.setTooltip(new Tooltip("幻灯片"));
	}

	public FlowPane getFlowPane() {
		return flowPane;
	}

	public  ObservableList<Node> getFlowPaneChildren() {
		return flowPane.getChildren();
	}
	public Text getText() {
		return text1;
	}
	public Text getTextTwo() {
		return text2;
	}

	public ArrayList<Thumbnail> getPictures() {
		return pictures;
	}

	public void addPictures(Thumbnail pNode) {
		pictures.add(pNode);
	}

	public void showPicture() {
		flowPane.getChildren().remove(0, flowPane.getChildren().size());
		for (Thumbnail pNode : pictures) {
			flowPane.getChildren().add(pNode);
		}
	}

	public void clearPictures() {
		pictures.clear();
	}

	public void removePictures(Thumbnail pNode) {
		for (Thumbnail pictureNode : pictures) {
			if (pictureNode.equals(pNode)) {
				pictures.remove(pNode);
				break;
			}
		}
	}

	@FXML
	public void openBtnAction(ActionEvent event) {
		 new OpenAction();
	}
	@FXML
	public void copyBtnAction(ActionEvent event) {
		new CopyAction();
	}
	@FXML
	public void pasteBtnAction(ActionEvent event) {
		new PasteAction(mainUI);
	}
	@FXML
	public void deleteBtnAction(ActionEvent event) {
		new DeleteAction(mainUI);
	}
	@FXML
	public void renameBtnAction(ActionEvent event) {
		new RenameAction(mainUI);
	}
	@FXML
	public void PPTAction(ActionEvent event) {
		new SlideShowAction();
	}

}
