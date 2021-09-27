package main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.fxml.Initializable;
import javafx.scene.Parent;
import nayoung.memo.MemoProc;
import nayoung.temp.TempProc;

import geonhwe.Login.LoginService;
import geonhwe.Login.LoginServiceImpl;

import geonhwe.member.MemberService;
import geonhwe.member.MemberServiceImpl;
import hayong.FoodListController;
import hayong.HyMain;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import yegin.alert.AlertController;
import yegin.shelf_life.shelfLifeController;



public class MainFunction_Controller implements Initializable{
	Parent root;
	Parent newRoot;
	MemberService ms;
	MemoProc mc;
	TempProc tc;
	hayong.HyMain hy;

	public void setRoot(Parent root) {
		this.root = root;
		ms.setRoot(root);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		ms = new MemberServiceImpl();
		mc = new MemoProc();
		tc = new TempProc();
		hy = new HyMain();

	}

	public void membershipProc() {
		System.out.println("회원가입 클릭");
		ms.membershipProc();
	}

	public void memberCancle() {
		System.out.println("취소 클릭");
		ms.memberCancle();
	}

	
	public void btnMemo() {
		mc.memo();
	}
	
	public void btnTemp() {
		tc.temp();
	}



	public void setRoot2(Parent root) {
		this.newRoot = root;
	}

	public void frozen_Storage() {
		System.out.println("냉동고 버튼 눌림");

		FXMLLoader loader = new FXMLLoader(getClass().getResource("frozenStorage_function.fxml"));
		newRoot = null;
		Scene sc = null;
		try {
			// newRoot = loader.load();
			this.newRoot = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sc = new Scene(newRoot);
		Stage stage = (Stage) root.getScene().getWindow();

		MainFunction_Controller mc = loader.getController();
		mc.setRoot2(newRoot);
		stage.setScene(sc);
		stage.show();
	}

	public void alert() {
		System.out.println("알람");
		AlertController ac = new AlertController();
		ac.setRoot(newRoot);
		ac.alert();
	}

	public void cold_Storage() {
		System.out.println("냉장고 버튼 눌림");

		FXMLLoader loader = new FXMLLoader(getClass().getResource("coldStorage_function.fxml"));
		newRoot = null;
		Scene sc = null;
		try {
			// newRoot = loader.load();
			this.newRoot = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sc = new Scene(newRoot);
		Stage stage = (Stage) root.getScene().getWindow();

		MainFunction_Controller mc = loader.getController();
		mc.setRoot2(newRoot);
		stage.setScene(sc);
		stage.show();
	}

	public void shelfLife() {
		System.out.println("유통기한버튼");
		shelfLifeController sc = new shelfLifeController();
		System.out.println(newRoot);
		sc.setRoot(newRoot);
		sc.shelfLifeList();

	}
	public void food() {
		System.out.println("관리로이동");
		hy.foodScene();

		
	}

}

