package action;

import application.Main;
import controller.MainUIController;
import model.MyAlert;
import model.Thumbnail;

public class DeleteAction {
	MainUIController mainUIController;
	public DeleteAction(MainUIController mainUI) {
		mainUIController = mainUI;
		if(Thumbnail.getSelectedPictures().size()<=0) {
			return;
		}
		else {
			for(Thumbnail pNode : Thumbnail.getCutedPictures()) {
				pNode.getImageView().setEffect(null);
			}
			Thumbnail.getCutedPictures().clear();
		}
		
		if(MyAlert.showAlert("是否删除选中的图片？", "", Main.mainStage)) {
			for(Thumbnail pNode : Thumbnail.getSelectedPictures()) {
				mainUIController.getFlowPane().getChildren().remove(pNode);
				pNode.getImageFile().delete();
			}
		}
		Thumbnail.getSelectedPictureFiles().clear();
		Thumbnail.clearSelected();
	}
}
