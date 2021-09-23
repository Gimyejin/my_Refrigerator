package geonhwe.Login;

import java.net.URL;
import java.util.ResourceBundle;

import geonhwe.db.teamproject;
import geonhwe.member.MemberDTO;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class LoginController implements Initializable {

	Parent root;

//	LoginService ls;
//	ToMenuProc tmp;
	
	Parent mainRoot;
	LoginServiceImpl lsi = new LoginServiceImpl();
	
	public void setRoot(Parent root) {
		this.root = root;
		lsi.setRoot(root); // root.값 넘기기
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		ls = new LoginServiceImpl();
//		tmp = new ToMenuProc();
	}

	public void login() {

//		System.out.println("로그인");
//		tmp.toMenu();

		System.out.println("loginController에 위치/ 로그인");
		lsi.setMainRoot(mainRoot);
		lsi.loginCheck();

	}

	public void setMainRoot(Parent root2) {
		this.mainRoot = root2;
		System.out.println("로그인 컨드롤에 있는 mainroot"+mainRoot);
	}

}
