package geonhwe.Login;

import java.io.IOException;

import geonhwe.db.teamproject;
import geonhwe.member.MemberDTO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import main.LoginButton_Controller;
import main.MainFunction_Controller;


public class LoginServiceImpl implements LoginService {
	public static String staticid; //id전역변수설정
	public static String getStaticid() {return staticid;}
	public static void setStaticid(String staticid) {LoginServiceImpl.staticid = staticid;}

	Parent root;

	LoginProc lp;
	ToMenuProc tmp;

	Parent mainRoot;//들어옴

	teamproject tpj = new teamproject(); 
	int result = 0;
	public void setRoot(Parent root) {
		this.root = root;
		System.out.println("LoginServiceImpl에 있는 root"+root);
	}

	@Override
	public int loginCheck() {
		System.out.println("로그인하겠습니다");
		TextField id = (TextField) root.lookup("#fxId");
		TextField pw = (TextField) root.lookup("#fxPwd");
		MemberDTO dto = new MemberDTO();
		dto.setId(id.getText());
		dto.setPwd(pw.getText());
		System.out.println(id.getText());
		System.out.println(pw.getText());
		
		MemberDTO getdto = tpj.loginChk(id.getText()); //TextField에 적은 값으로 DB안에 있는 데이터를 가져온다.
		if(getdto == null) {
			System.out.println("아이디일치X");
			alertMethod("해당 아이디는 없는 아이디입니다.","로그인 실패");
		}else {
			System.out.println("아이디 일치");
			if(getdto.getPwd().equals(pw.getText()) ) { //equals 서로 비교하여 일치하는지 확인해주는 역할
				System.out.println("로그인 성공");
				Stage stage = (Stage)root.getScene().getWindow();
				stage.close();//로그인 성공시 해당 창 닫음
				//하용 테스트
				setStaticid(id.getText());
				
				result = 1;
				System.out.println("(연결용)메뉴페이지로 넘어감");
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				LoginButton_Controller lbc=new LoginButton_Controller();
				lbc.setRoot(mainRoot);
				System.out.println("로그인서비스impl "+mainRoot);
				lbc.login();
				/*
				 FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/menu.fxml"));
				 
				Parent newRoot = null;
				Scene sc = null;
				try {
					newRoot = loader.load();//여기서 에러
					System.out.println("here:"+newRoot);;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(mainRoot);//들어옴!
				System.out.println(newRoot);
				sc = new Scene(newRoot);

				// scene만 바꿔치기
				Stage stage2 = (Stage) mainRoot.getScene().getWindow();
				
				MainFunction_Controller mc=loader.getController();
				mc.setRoot(newRoot);
				
				stage2.setScene(sc);
				stage2.show();
				*/
			}else {
				System.out.println("로그인 실패");
				alertMethod("비밀번호가 일치하지 않습니다.","로그인 실패");
			} 
		} return result; 
		//나중에 합칠 때 이제 변수에 값을 줘서 로그인 성공 실패 여부 정하고 조원분들에게 알려드리고 확인하기
	} 

	public void loginToMenu() {
		
		loginCheck();
		if(loginCheck()==1) {
			tmp.toMenu();
		}else {
//			alertMethod("로그인 실패. 다시 시도해 주세요");
		}
	}
	

//	public void alertMethod(String mss) {
//		Alert alert = new Alert(AlertType.INFORMATION);

	public void alertMethod(String mss,String msg) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText(msg);

		alert.setContentText(mss);
		alert.show();
	}

	public void setMainRoot(Parent mainRoot) {
		this.mainRoot=mainRoot;
		System.out.println("mainRoot 여부: "+mainRoot);
	}
} 