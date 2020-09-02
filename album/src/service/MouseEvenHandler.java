package service;
import action.OpenAction;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.FolderFile;
import model.Thumbnail;


public class MouseEvenHandler implements EventHandler<MouseEvent>{

	Node node;
	FolderFile folderFile;
	public MouseEvenHandler(Node node,FolderFile folderFile) {
		this.node = node;
		this.folderFile = folderFile;
	}
	
	@Override
	public void handle(MouseEvent event) {
		if(node instanceof Thumbnail) {
//			System.out.println(event.getSource());
			if(event.isControlDown() == false) {//Control没有按下
				if(event.getButton()!=MouseButton.SECONDARY || !((Thumbnail)node).selected.getValue())
					Thumbnail.clearSelected();
				((Thumbnail)node).setSelected(true);
				if(event.getClickCount()>=2 && event.getButton() == MouseButton.PRIMARY){
					//双击打开事件
					((Thumbnail)node).setSelected(true);
					((Thumbnail) node).openAction();
//					System.out.println(PictureNode.getSelectedPictures().size()+"~~~");
				}				
			}
			if(event.isControlDown() && event.getButton() == MouseButton.PRIMARY) {//Control按下
				((Thumbnail) node).setSelected( !((Thumbnail)node).selected.get() );
			}
		}
	}
}
