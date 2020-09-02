package model;

import java.io.File;
import java.net.MalformedURLException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import controller.MainUIController;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class FolderTree {

	private MainUIController mainUI;                           
	private TreeView<FolderFile> treeView;
	private TreeItem<FolderFile> root;

	private final File[] rootPath = File.listRoots(); //windows的根目录 下级是CDEF盘

	public FolderTree(MainUIController mainUI, TreeView<FolderFile> treeView) {

		this.mainUI = mainUI;
		// System.out.println(mainUI);
		this.treeView = treeView;
		root = new TreeItem<FolderFile>(new FolderFile("root"));  //创建根节点，对应的目录是虚拟的
		root.setExpanded(true);
		treeView.setRoot(root);
		treeView.setShowRoot(false);   //隐藏根节点
		creatTopFolderTree();
		getSelected();        //选中，链接viewer

	}

	private void creatTopFolderTree() {        //创建CDEF盘层级目录
		for (int i = 0; i < rootPath.length; i++) {
			FolderTreeItem folderItem = new FolderTreeItem(new FolderFile(rootPath[i]));  //代表目录节点的Item
//			System.out.println(folderItem);   解除注释可证明创建CDEFG盘
			root.getChildren().add(folderItem);
		}
	}

	public TreeView<FolderFile> gettreeView() {
		return treeView;
	}

	/**
	 * 显示目录文件下的图片
	 */
	private void getSelected() {
		treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<FolderFile>>() {

			@Override
			public void changed(ObservableValue<? extends TreeItem<FolderFile>> observable, TreeItem<FolderFile> oldValue,
					TreeItem<FolderFile> newValue) {
				mainUI.getFlowPane().getChildren().remove(0, mainUI.getFlowPane().getChildren().size());
				FolderFile file = treeView.getSelectionModel().getSelectedItem().getValue();

				if (file.isDirectory()) {
					
					MainUIController.theFilePath = file.getfolderFile().getAbsolutePath();
					int total = 0;
					double size = 0;
//					boolean first = true;
					FolderFile[] folderFiles = file.listFolderFiles();
					mainUI.clearPictures();
					for (FolderFile folderFile : folderFiles) {
						if (folderFile.isPicture()) {
					
							total++;
							size += file.length();
							try {
								Thumbnail thumbnail = new Thumbnail(folderFile, mainUI);
								mainUI.getFlowPane().getChildren().add(thumbnail);
								mainUI.addPictures(thumbnail);
							} catch (MalformedURLException e) {
								e.printStackTrace();
							}

						}
					}
					mainUI.showPicture();
					mainUI.getText().setText(total + "张图片， 共" + (int)size + "Byte");
				}
			}

		});
	}
}
