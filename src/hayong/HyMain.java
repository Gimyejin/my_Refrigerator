package hayong;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HyMain{
	Parent root;
	
	public void foodScene() {

		System.out.println("물품관리로 이동");
		try {
			Stage primaryStage = new Stage();
			FXMLLoader loader = 
					new FXMLLoader(getClass().getResource("foodList.fxml"));
			root = loader.load();
			Scene scene = new Scene(root);
			
			FoodListController ctl = loader.getController();
			ctl.setRoot(root);
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	} 

	/*	@Override

		public void start(Stage primaryStage) throws Exception {
			FXMLLoader loader = 
					new FXMLLoader(getClass().getResource("foodList.fxml"));
			
			Parent root = loader.load();
			Scene scene = new Scene(root);
			
			FoodListController ctl = loader.getController();
			ctl.setRoot(root);
			
			primaryStage.setScene(scene);
			primaryStage.show();
		}

		public static void main(String[] args) {
			launch(args);

		}*/

		}




