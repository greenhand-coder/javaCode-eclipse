package action;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;

import controller.MainUIController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.FolderFile;
import model.Thumbnail;
import service.ChangeService;

public class PageTurningAction {

	private static int pageNumber = 0;
	private static Image images;
	private boolean forwardNext = true;


	public static void changePicture(ImageView imageView,Boolean Previous_or_next) {
	    if(Previous_or_next) {
	    	pageNumber++;
	    }
	    if(!Previous_or_next) {
	    	pageNumber--;
	    }
		if (pageNumber < 0) {
			String text = "这是第一张图片";
			Button button = new Button(text);
			Pane root = new Pane(button);
			Scene scene = new Scene(root);
			Stage Stage = null;
			Stage = new Stage();
			Stage.setTitle("提示");
			Stage.setScene(scene);
			Stage.show();
			pageNumber++;
			return;
		}
		if (pageNumber > ChangeService.files.size() - 1) {
			String text = "这是最后一张图片";
			Button button = new Button(text);
			Pane root = new Pane(button);
			Scene scene = new Scene(root);
			Stage Stage = null;
			Stage = new Stage();
			Stage.setTitle("提示");
			Stage.setScene(scene);
			Stage.show();
			pageNumber--;
			return;
		}
		File file = ChangeService.files.get(pageNumber);
		try {
			images = new Image(file.toURI().toURL().toString());
			imageView.setImage(images);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
