package nayoung.memoList;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Collection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;
import nayoung.memo.Memo;
import nayoung.memoListDB.SL_DB;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;

public class MemoListController implements Initializable{
	Parent root;
	MemoList ml;

	public void setRoot(Parent root) {
		this.root = root;
		ml.setRoot(root);
	}

	public void memoProc() {
		System.out.println("장보기 메모 클릭");
		ml.memoListProc();	
	}
	
	@FXML
	private DatePicker datePicker;
	@FXML
	private TextField txtName;
	@FXML
	private TextFlow txtflow;
	
	@FXML 
	private Label resultLabel; 
	
	private String inputValue;

	
	
	@FXML
	private ListView<ShoppingList> list;
	
	private ObservableList<ShoppingList> items;
	
	private SL_DB db;
	//private Connection conn;
	@FXML
	private void initialize() {
		items = FXCollections.observableArrayList();
		list.setItems(items);
		db = new SL_DB();
		
		Connection con = db.getConnection();
		if(con == null ) {
			AppUtil.alert("DB연결에 실패했습니다.", null);
			return;
		}
		
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM SHOPPINGLIST";
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String name = rs.getString("name");
				LocalDate date = rs.getDate("date").toLocalDate();
				
				ShoppingList sl = new ShoppingList(" ",name, date);
				items.add(sl);
			}
		}catch (Exception e) {
			e.printStackTrace();
			AppUtil.alert("데이터 삽입 실패", null);
			return;
		}finally {
			if(rs != null) try { rs.close(); } catch (Exception e) {}
			if(stmt != null) try { stmt.close(); } catch (Exception e) {}
			if(con != null) try { con.close(); } catch (Exception e) {}
		}
	}
	
	public void addList() {
		String name = txtName.getText();
		
		if(name.isEmpty()) {
			AppUtil.alert("품목의 이름을 입력하셔야 합니다.", null);
			return;
		}
		
		LocalDate date = datePicker.getValue();
		if(date == null) {
			AppUtil.alert("날짜를 입력하세요", null);
			return;
		}
		Connection conn = db.getConnection();
		if(conn == null) {
			AppUtil.alert("DB연결에 실패했습니다.", null);
			return;
		}
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO SHOPPINGLIST (LIST, SL_DATE) VALUES( ?, ?)";
		
		//
		String insertId = null;
		
		try {
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1,  name);
			pstmt.setDate(2, Date.valueOf(date));
			int cnt = pstmt.executeUpdate();
			if(cnt == 0) {
				AppUtil.alert("데이터 삽입 실패", null);
				return;
			}
			ResultSet key = pstmt.getGeneratedKeys();
			if(key.next()) {
				//
				insertId = key.getString(1); 
			}
		}catch (Exception e) {
			e.printStackTrace();
			AppUtil.alert("데이터 삽입 실패", null);
			return;
		}finally {
			if(pstmt != null) try { pstmt.close(); } catch (Exception e) {}
			if(conn != null) try { conn.close(); } catch (Exception e) {}
		}
		
		ShoppingList sl = new ShoppingList(insertId, name, date);
		items.add(sl);

	}
	
	public void delList() {
		int idx = list.getSelectionModel().getSelectedIndex();
		if(idx >= 0) {
			items.remove(idx);
		}else {
			AppUtil.alert("삭제할 항목을 선택하세요", "에러");
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void seeList() {
//		int idx = list.getSelectionModel().getSelectedIndex();
//		if(idx >= 0) {
////			TextFlow textFlow;
////			textFlow.getChildren().add();
//			
//
//			
//		}else {
//			AppUtil.alert("항목을 선택하세요", "에러");
//		}
	}
	
//
//	
//	@FXML
//    public Label  myLabel;
//    
//    @FXML
//    public ComboBox<String> combobox;
//    
//    @FXML
//    public ListView<String> listView;
//    
//    ObservableList<String> list = FXCollections.observableArrayList("우유", "계란", "양파", "사과");
//
//	
//	@Override
//	public void initialize(URL arg0, ResourceBundle arg1) {
//		ml = new MemoListImpl();
//		
//		//combobox.setItems(list);
//        //listView.setItems(list);
//        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//
//	}
//	public void memoListProc() {
//		System.out.println("리스트 클릭");
//		ml.memoListProc();
//	}
//	
//	public void comboChange(ActionEvent event) {
//        myLabel.setText(combobox.getValue());
//    }
//    public void buttonAction(ActionEvent event) {
//        //combobox.getItems().addAll("ㄱ", "ㄴ", "ㄷ", "ㄹ");
//        //listView.getItems().addAll("ㄱ", "ㄴ", "ㄷ", "ㄹ");
//        ObservableList<String> names;
//        names = listView.getSelectionModel().getSelectedItems();
//        for (String name : names) {
//            System.out.println(name);
//        }
//    }
//
//}
}