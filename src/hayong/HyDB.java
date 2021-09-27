package hayong;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import geonhwe.Login.LoginServiceImpl;

public class HyDB {
	
public static Connection conn;
	
	public HyDB() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@210.221.253.215:1521:xe", "team1", "1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	public ArrayList<FoodDTO> DbValue() {
		
		ArrayList<FoodDTO> list = new ArrayList<FoodDTO>();
		try {
			String sql = "select * from item_db where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, LoginServiceImpl.staticid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				FoodDTO dto = new FoodDTO();
				dto.setFoodName(rs.getString("item_name"));
				dto.setFoodNum(String.valueOf(rs.getInt("item_count")));
				dto.setFoodTime(rs.getString("item_add_date"));
				list.add(dto);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}return list;
	}
	
	public int insert(FoodDTO dto) {
		int result=0;
		try {
			String sql = "insert into item_db values (?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, LoginServiceImpl.staticid);
			ps.setString(2, dto.getFoodName());
			ps.setString(3, dto.getFoodTime());
			ps.setInt(4, Integer.parseInt(dto.getFoodNum()));
			ps.setString(5, null);
		
			result = ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	public int update(FoodDTO dto) {
		int result=0;
		try {
			String sql =
		"update item_db set item_name=?,item_count=? where id=? and item_name=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getFoodName());
			ps.setInt(2, Integer.parseInt(dto.getFoodNum()));
			ps.setString(3, LoginServiceImpl.staticid);
			ps.setString(4, dto.getOldName());
			result = ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
	}
	public int remove(FoodDTO dto) {
		int result=0;
		try {
			String sql = "delete item_db where id=? and item_name=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, LoginServiceImpl.staticid);
			ps.setString(2, dto.getFoodName());
			result = ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
	}

}



