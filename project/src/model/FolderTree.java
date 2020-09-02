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

	private final File[] rootPath = File.listRoots();

	public FolderTree(MainUIController mianUI, TreeView<FolderFile> treeView) {

		this.mainUI = mianUI;
		// System.out.println(mianUI);
		this.treeView = treeView;
		root = new TreeItem<FolderFile>(new FolderFile("root"));
		root.setExpanded(true);
		treeView.setRoot(root);
		treeView.setShowRoot(false);
		buildFileTree();
		getSelected();

	}

	private void buildFileTree() {
		for (int i = 0; i < rootPath.length; i++) {
			FolderTreeItem item = new FolderTreeItem(new FolderFile(rootPath[i]));
			root.getChildren().add(item);
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
				FolderFile pFile = treeView.getSelectionModel().getSelectedItem().getValue();

				if (pFile.isDirectory()) {
					
					MainUIController.theFilePath = pFile.getImageFile().getAbsolutePath();
					int total = 0;
					double size = 0;
//					boolean first = true;
					FolderFile[] pictureFiles = pFile.listPictures();
					mainUI.clearPictures();
					for (FolderFile pictureFile : pictureFiles) {
						if (pictureFile.isPicture()) {
					
							total++;
							size += pFile.length();
							try {
								Thumbnail pictureNode = new Thumbnail(pictureFile, mainUI);
//								System.out.println("pic");
								mainUI.getFlowPane().getChildren().add(pictureNode);
								mainUI.addPictures(pictureNode);
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
