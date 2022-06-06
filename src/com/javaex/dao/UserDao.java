package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
			query += " ";
			
			pstmt = conn.prepareStatement(query);
			
			
			count = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("error: " + e);
		}
		
		close();
		return count;
	}
	
	public int userDelete(UserVo uVo) {
		int count = -1;
		
		try {
			getConnection();
			
			String query = "";
			query += " ";
			
			pstmt = conn.prepareStatement(query);
			
			
			count = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("error: " + e);
		}
		
		close();
		return count;
	}
	
	public UserVo getUser(int no) {
		UserVo uVo = new UserVo();
		
		try {
			getConnection();
			
			String query = "";
			query += " ";
			
			pstmt = conn.prepareStatement(query);
			
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				
			}
			
		} catch(SQLException e) {
			System.out.println("error: " + e);
		}
		
		close();
		return uVo;
	}
}
