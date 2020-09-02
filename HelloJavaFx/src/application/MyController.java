package application;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MyController {
	@FXML
	private Button btn_1;
	@FXML
	private TextField text_1;
	
	public void eventButton() {
		String text = text_1.getText();
		System.out.println(text);
	}

}
