package geonhwe.Login;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.MainFunction_Controller;

public class ToMenuProc {
	Parent root;
	
	public void setRoot(Parent root) {
		   this.root = root;
	   }
	public void toMenu() {
		System.out.println("메뉴페이지로 넘어감");
		try {
			Stage primaryStage =new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../main/menu.fxml"));
			Parent newRoot = loader.load();
			Scene sc = new Scene(newRoot);
			newRoot = loader.load();
			
			MainFunction_Controller mc = loader.getController();
			mc.setRoot(newRoot);
			
			primaryStage.setScene(sc);
			primaryStage.show();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		//
		
	}
}
