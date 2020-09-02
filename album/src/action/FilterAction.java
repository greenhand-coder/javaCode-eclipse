package action;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import service.ChangeService;

public class FilterAction {
	public FilterAction() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/Filter.fxml"));
			Parent root = (Parent)loader.load();
			Scene scene = new Scene(root);
			ChangeService.stage.setScene(scene);
			ChangeService.stage.setTitle("图片美化");
		//	Main.stage.setResizable(false);
			ChangeService.stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
