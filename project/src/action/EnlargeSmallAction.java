package action;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EnlargeSmallAction {

	private static int changeNumber = 1;

	public static void enlarge(ImageView imageView) {
		changeNumber +=1;
		Image image = imageView.getImage();
		imageView.setFitWidth(800*(changeNumber*0.1+1));
		imageView.setFitHeight(800*(changeNumber*0.1+1));
		imageView.setPreserveRatio(true);
	}
	public static void small(ImageView imageView) {
		changeNumber -=1;
		Image image = imageView.getImage();
		imageView.setFitWidth(800*(changeNumber*0.1+1));
		imageView.setFitHeight(800*(changeNumber*0.1+1));
		imageView.setPreserveRatio(true);
	}
	
}
