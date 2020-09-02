package action;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import service.ChangeService;

public class SlideShowAction {
	public SlideShowAction() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/SlideShow.fxml"));
			Parent root = (Parent)loader.load();
			Scene scene = new Scene(root);
			ChangeService.stage.setScene(scene);
			ChangeService.stage.setTitle("幻灯片");
		//	Main.stage.setResizable(false);
			ChangeService.stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
