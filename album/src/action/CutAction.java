package action;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import model.Thumbnail;

public class CutAction {

	public CutAction() {
		if(Thumbnail.getSelectedPictures().size()<=0) {
			Thumbnail.getCutedPictures().clear();
			return;
		}
		else {
			for(Thumbnail pNode : Thumbnail.getCutedPictures()) {
				pNode.getImageView().setEffect(null);
			}
			Thumbnail.getCutedPictures().clear();
		}
		Clipboard clipboard = Clipboard.getSystemClipboard();
		ClipboardContent clipboardContent = new ClipboardContent();
		clipboard.clear();
		clipboardContent.clear();
		Thumbnail.getSelectedPictureFiles().clear();
		
		for(Thumbnail pNode : Thumbnail.getSelectedPictures()) {
			Thumbnail.getSelectedPictureFiles().add(pNode.getImageFile());
			pNode.getImageView().setEffect(new ColorAdjust(0, 0, 0.5, 0));//标志被剪切
			Thumbnail.addCutedPictures(pNode);			
		}
		clipboardContent.putFiles(Thumbnail.getSelectedPictureFiles());
		clipboard.setContent(clipboardContent);
		Thumbnail.getSelectedPictureFiles().clear();	
		Thumbnail.clearSelected();
	}
}
