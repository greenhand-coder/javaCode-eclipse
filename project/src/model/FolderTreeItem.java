package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

public class FolderTreeItem extends TreeItem<FolderFile> {

	private boolean isLeaf;
	private boolean isFirstChildren = true;
	private boolean isFirstLeaf = true;

	public FolderTreeItem(FolderFile pictureFile) {
		super(pictureFile);
	}

	@Override
	public ObservableList<TreeItem<FolderFile>> getChildren() {
		if (isFirstChildren) {
			isFirstChildren = false;
			super.getChildren().setAll(buildChildren(this));
		}
		return super.getChildren();
	}

	@Override
	public boolean isLeaf() {
		if (isFirstLeaf) {
			isFirstLeaf = false;
			FolderFile pictureFile = getValue();
			FolderFile[] pictureFiles = pictureFile.listPictures();
			if (pictureFiles == null ||pictureFiles.length == 0 ) {
				isLeaf = true;
			} else {
				isLeaf = true;
				for (FolderFile pictureFile2 : pictureFiles) {
					if (pictureFile2.isDirectory()) {
						isLeaf = false;
					}
				}
			}

		}
		return isLeaf;
	}

	private ObservableList<TreeItem<FolderFile>> buildChildren(TreeItem<FolderFile> TreeItem) {
		FolderFile pictureFile = TreeItem.getValue();

		if (pictureFile != null && pictureFile.isDirectory()) {
			FolderFile[] pictureFiles = pictureFile.listPictures();
			if (pictureFiles != null && pictureFiles.length != 0) {
				ObservableList<TreeItem<FolderFile>> children = FXCollections.observableArrayList();
				for (FolderFile childFile : pictureFiles) {
					if (childFile.isHidden() || childFile.isFile()) {
						continue;
					}
					children.add(new FolderTreeItem(childFile));
				}
				return children;
			}
		}
		return FXCollections.emptyObservableList();
	}
}