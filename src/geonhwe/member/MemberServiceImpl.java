package geonhwe.member;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import geonhwe.member.*;
import geonhwe.common.*;
import geonhwe.db.teamproject;

public class MemberServiceImpl implements MemberService{
	Parent root;
	Parent memberRoot;
	teamproject tpj = new teamproject();

	public void memberCancle() {
		CommonService cs = new CommonServiceImpl();
		cs.setRoot(root);
		cs.windowClose();
	}

	public void alertMethod(String msg) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText(msg);
		alert.show();
	}

	public void membershipProc() {
		TextField id = (TextField) root.lookup("#fxId");
		TextField pwd = (TextField) root.lookup("#fxPw");
		TextField name = (TextField) root.lookup("#fxName");
		// 노랑색부분은 바꿀 수 있지만 파란색은 신빌더랑 맞춰줘야한다.
		MemberDTO dto = new MemberDTO();

		dto.setId(id.getText());
		dto.setPwd(pwd.getText());
		dto.setName(name.getText());

		// = 하나는 저장, == 앞과 뒤가 같은지 확인하는 용도 / 오른쪽의 값을 좌측에 넣겠다.

		int result = tpj.insert(dto);

		if(result == 0) { alertMethod ("이미 가입된 아이디입니다.");
		System.out.println("회원가입 실패!");
		}else if(result == 1) {
			alertMethod ("회원가입이 가능한 아이디입니다.");
			System.out.println("회원가입 완료!");
			
				
			} 
		
		System.out.println(dto.getId()); // set = 넣다 get = 꺼내다
		System.out.println(dto.getName());
		System.out.println(dto.getPwd());
	}

	public void setRoot(Parent root) {
		this.root = root;
	}
}

// 그 fxid를 설정을 하고나서 이제 찾을 때 #memberid로
//lookup 찾다/ "#~~~"를 텍스트필드를갑 줄건데 id라는 변수에 담는다
// ((TextField)root.lookup("#fxId")) 이 값만 가지고 내용을 찾을거다