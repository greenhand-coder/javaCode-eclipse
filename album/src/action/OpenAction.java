package action;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import service.ChangeService;

public class OpenAction {
	public OpenAction() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/ViewUI.fxml"));
			Parent root = (Parent)loader.load();
			Scene scene = new Scene(root);
			ChangeService.stage.setScene(scene);
			ChangeService.stage.setTitle("Album");
			ChangeService.stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
