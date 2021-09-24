package hayong;

import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import geonhwe.Login.LoginServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import main.MainFunction_Controller;

public class FoodListController implements Initializable{
	Parent root;
	ListView<String> fxNameView;
	ListView<String> fxcntView;
	ListView<String> fxtimeView;
	ArrayList<FoodDTO> dtolist;
	ObservableList<String> NameString,timeString, cntString;
	HyDB hb;
	Button btnmod, btnrm;


	public void setRoot(Parent root) {
		this.root = root;
		fxNameView = (ListView)root.lookup("#fxNameView");
		fxcntView = (ListView)root.lookup("#fxcntView");
		fxtimeView = (ListView)root.lookup("#fxtimeView");
		addComboBox();
		Label fxname = (Label)root.lookup("#fxname");
		fxname.setText(LoginServiceImpl.staticid+" 님의 냉장고");
		setListView();
		btnmod = (Button)root.lookup("#mod");
		btnrm = (Button)root.lookup("#rm");
		btnmod.setDisable(true);
		btnrm.setDisable(true);
		fxNameView.setOnMousePressed(e->{
			btnmod.setDisable(false);
			btnrm.setDisable(false);
		});

	}
	public void addComboBox() {
		ComboBox<String> cnt = (ComboBox<String>)root.lookup("#fxcount");
		if(cnt != null) {
			cnt.getItems().addAll("1","2","3","4","5","6","7","8","9","10"
					,"11","12","13","14","15","16","17","18","19","20");
	}

	}
	
	public void setListView() { //디비에서값을받아와 fx리스트에 세팅
		NameString = FXCollections.observableArrayList();
		cntString = FXCollections.observableArrayList();
		timeString = FXCollections.observableArrayList();
		NameString.add("음식");
		cntString.add("수량");
		timeString.add("날짜");
		
		ArrayList<FoodDTO> list = hb.DbValue();		

		//for(int i=0;i<list.size();i++) {
		//	System.out.println(list.get(i).getFoodName());
		//	System.out.println(list.get(i).getFoodNum());
		//	System.out.println(list.get(i).getFoodTime());
		//}
		if(list!=null) {
			for(int i=0;i<list.size();i++) {
				NameString.add(list.get(i).getFoodName());
				cntString.add(list.get(i).getFoodNum());
				timeString.add(list.get(i).getFoodTime());
			}
		} 
		fxNameView.setItems(NameString);
		fxcntView.setItems(cntString);
		fxtimeView.setItems(timeString);
			
	}
	
	public void fxadd() { //추가기능
		
		TextArea food = (TextArea)root.lookup("#fxaddtext");
		ComboBox<String> com = (ComboBox<String>)root.lookup("#fxcount"); //입력값 set
		FoodDTO dto = new FoodDTO(); 
		if(food==null||com==null) {
			Alert alt = new Alert(AlertType.INFORMATION);
			alt.setContentText("추가할 음식과 수량을 입력해주세요");
			alt.show(); return;
		}
		dto.setFoodName(food.getText());
		dto.setFoodNum(getComboBox());
		Date date = new Date(); 
		SimpleDateFormat s = new SimpleDateFormat("MM월 dd일 aa hh시"); //현재시간
		String str = s.format(date);
		dto.setFoodTime(str);
		System.out.println(dto.getFoodName()+" "+dto.getFoodNum());
		int result = hb.insert(dto);
		if(result==1) {
			Label fxmsg = (Label)root.lookup("#fxmsg");
			fxmsg.setText("추가되었습니다");
		} else {
			Alert alt = new Alert(AlertType.INFORMATION);
			alt.setContentText("실패");
			alt.show();
		}

	}

	public void fxmod() { //수정기능
		FoodDTO dtomod = new FoodDTO(); 
		fxNameView.getSelectionModel().selectedIndexProperty().
		addListener((observable, oldValue, newValue)->{
			dtomod.setOldName(NameString.get((int)newValue));	//마우스로 선택한값 set
		});
		//ObservableList<String> a = fxNameView.getSelectionModel().getSelectedItems();
		//dto.setOldName(a.get(0));
		TextArea food = (TextArea)root.lookup("#fxaddtext");
		ComboBox<String> com = (ComboBox<String>)root.lookup("#fxcount"); //입력값 set	
		dtomod.setFoodName(food.getText());
		dtomod.setFoodNum(getComboBox());
		int result = hb.update(dtomod);
		if(result==1) {
			Label fxmsg = (Label)root.lookup("#fxmsg");
			fxmsg.setText("수정되었습니다");
		}else {
			Alert alt = new Alert(AlertType.INFORMATION);
			alt.setContentText("실패");
			alt.show(); return;
		}
		setListView();

	}
	
	public void fxrm() { //삭제기능
		FoodDTO dto = new FoodDTO(); 
		fxNameView.getSelectionModel().selectedIndexProperty().
		addListener((observable, oldValue, newValue)->{
			dto.setFoodName(NameString.get((int)newValue));	//마우스로 선택한값 set
			dto.setFoodNum(cntString.get((int)newValue));
			dto.setFoodTime(timeString.get((int)newValue));
		});
		int result = hb.remove(dto);
		if(result==1) {
			Label fxmsg = (Label)root.lookup("#fxmsg");
			fxmsg.setText("삭제되었습니다");
		}else {
			Alert alt = new Alert(AlertType.INFORMATION);
			alt.setContentText("실패");
			alt.show(); 
		}
		fxNameView.getItems().remove(dto.getFoodName());
		fxcntView.getItems().remove(dto.getFoodNum());
		fxtimeView.getItems().remove(dto.getFoodTime());
		setListView();
		
		
	}
	public void fxCan() { //뒤로가기
		Stage stage = (Stage)root.getScene().getWindow();
		stage.close();
		MainFunction_Controller mc = new MainFunction_Controller();
		mc.cold_Storage();

	}
	public String getComboBox() {
		ComboBox<String> cnt = (ComboBox<String>)root.lookup("#fxcount");
		String su = null;
		if(cnt.getValue() == null) {
			su= "1";
		}else {
			su = cnt.getValue().toString();
		}
		return su;
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		hb = new HyDB();
		
	}


}

