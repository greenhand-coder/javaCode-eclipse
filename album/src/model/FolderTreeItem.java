package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

public class FolderTreeItem extends TreeItem<FolderFile> {    //代表目录节点的Item，树中每个节点叫treeItem

	private boolean isLeaf;
	private boolean isFirstChildren = true;
	private boolean isFirstLeaf = true;

	public FolderTreeItem(FolderFile folderFile) {
		super(folderFile);
	}

	@Override
	public ObservableList<TreeItem<FolderFile>> getChildren() {        //生成目录树
		if (isFirstChildren) {
			isFirstChildren = false;                     //锁住了防止重复遍历
			super.getChildren().setAll(creatChildren(this));           //this一直在变
			System.out.println(this);
		}
		return super.getChildren();               //getChildren和add方法被添加到了root节点上。也可以使用addAll方法来一次性添加这些子节点。
	}

	@Override
	public boolean isLeaf() {
		if (isFirstLeaf) {
			isFirstLeaf = false;
			FolderFile folderFile = getValue();
			FolderFile[] folderFiles = folderFile.listFolderFiles();
			if (folderFiles == null ||folderFiles.length == 0 ) {
				isLeaf = true;
			} else {
				isLeaf = true;
				for (FolderFile a : folderFiles) {
					if (a.isDirectory()) {
						isLeaf = false;
					}
				}
			}

		}
		return isLeaf;
	}
                                                                  //this
	private ObservableList<TreeItem<FolderFile>> creatChildren(TreeItem<FolderFile> TreeItem) {   
		FolderFile folderFile = TreeItem.getValue();

		if (folderFile != null && folderFile.isDirectory()) {                                         //这里是if，不是递归  
			FolderFile[] folderFiles = folderFile.listFolderFiles();   
			if (folderFiles != null && folderFiles.length != 0) {
				ObservableList<TreeItem<FolderFile>> children = FXCollections.observableArrayList();
				for (FolderFile childFile : folderFiles) {      
					if (childFile.isFile()) {
						continue;
					}
					children.add(new FolderTreeItem(childFile));//将FolderFile数组转化成TreeItem List(children List)
				}
				return children;
			}
		}
		return FXCollections.emptyObservableList();                                                    //文件空就返回空表
	}
}