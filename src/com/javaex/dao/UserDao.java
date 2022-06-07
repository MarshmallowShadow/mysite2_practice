package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaex.vo.UserVo;

public class UserDao {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";
	
	private void getConnection() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
		} catch(ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch(SQLException e) {
			System.out.println("error: " + e);
		}
	}
	
	private void close() {
		try {
			if(rs != null) {
				rs.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		} catch(SQLException e) {
			System.out.println("error: " + e);
		}
	}
	
	public int userInsert(UserVo uVo) {
		int count = -1;
		
		try {
			getConnection();
			
			String query = "";
			query += " insert into users";
			query += " values(seq_users_no.nextval, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, uVo.getId());
			pstmt.setString(2, uVo.getPassword());
			pstmt.setString(3, uVo.getName());
			pstmt.setString(4, uVo.getGender());
			
			count = pstmt.executeUpdate();
			
			System.out.println(count + "건이 등록되었습니다.");
			
		} catch(SQLException e) {
			System.out.println("error: " + e);
		}
		
		close();
		return count;
	}
	
	public int userUpdate(UserVo uVo) {
		int count = -1;
		
		try {
			getConnection();
			
			String query = "";
			query += " ";
			query += " ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, uVo.getPassword());
			pstmt.setString(2, uVo.getName());
			pstmt.setString(3, uVo.getGender());
			
			count = pstmt.executeUpdate();
			
			System.out.println(count + "건이 수정되었습니다.");
			
		} catch(SQLException e) {
			System.out.println("error: " + e);
		}
		
		close();
		return count;
	}
	
	public UserVo getUser(UserVo uVo) {
		UserVo authUser = new UserVo();
		
		try {
			getConnection();
			
			String query = "";
			query += " select	no,";
			query += " 			name";
			query += " from users";
			query += " where	id = ?";
			query += " and			password = ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, uVo.getId());
			pstmt.setString(2, uVo.getPassword());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				authUser.setNo(no);
				authUser.setName(name);
			}
			
		} catch(SQLException e) {
			System.out.println("error: " + e);
		}
		
		close();
		return authUser;
	}
	
	public UserVo getUser(int no) {
		UserVo uVo = new UserVo();
		
		try {
			getConnection();
			
			String query = "";
			query += " select	no,";
			query += " 			id,";
			query += " 			password,";
			query += " 			name,";
			query += " 			gender,";
			query += " from users";
			query += " where	no = ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String id = rs.getString("id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				uVo.setNo(no);
				uVo.setId(id);
				uVo.setPassword(password);
				uVo.setName(name);
				uVo.setGender(gender);
			}
			
		} catch(SQLException e) {
			System.out.println("error: " + e);
		}
		
		close();
		return uVo;
	}
}
