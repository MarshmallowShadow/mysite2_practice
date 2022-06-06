package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestVo;

public class GuestBookDao {
	public Connection conn = null;
	public PreparedStatement pstmt = null;
	public ResultSet rs = null;
	public String driver = "oracle.jdbc.driver.OracleDriver";
	public String url = "jdbc:oracle:thin:@localhost:1521:xe";
	public String id = "webdb";
	public String pw = "webdb";
	
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
	
	public int insert(GuestVo gVo) {
		int count = -1;
		
		try {
			getConnection();
			
			String query = "";
			query += " insert into guestbook";
			query += " values(seq_guestbook_no.nextval, ?, ?, ?, sysdate)";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, gVo.getName());
			pstmt.setString(2, gVo.getPassword());
			pstmt.setString(3, gVo.getContent());
			
			count = pstmt.executeUpdate();
			
			System.out.println(count + "건이 등록되었습니다.");
		} catch(SQLException e) {
			System.out.println("error: " + e);
		}
		
		close();
		
		return count;
	}
	
	public int delete(GuestVo gVo) {
		int count = -1;
		
		try {
			getConnection();
			
			String query = "";
			query += " delete from guestbook";
			query += " where no = ?";
			query += " and password = ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, gVo.getNo());
			pstmt.setString(2, gVo.getPassword());
			
			count = pstmt.executeUpdate();
			
			System.out.println(count + "건이 삭제되었습니다.");
		} catch(SQLException e) {
			System.out.println("error: " + e);
		}
		
		close();
		
		return count;
	}
	
	public List<GuestVo> getList() {
		List<GuestVo> gList = new ArrayList<>();
		
		try {
			getConnection();
			
			String query = "";
			query += " select	no,";
			query += " 			name,";
			query += " 			password,";
			query += " 			content,";
			query += " 			reg_date";
			query += " from		guestbook";
			query += " order by no";
			
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt(1);
				String name = rs.getString(2);
				String password = rs.getString(3);
				String content = rs.getString(4);
				String regDate = rs.getString(5);
				
				GuestVo gVo = new GuestVo(no, name, password, content, regDate);
				gList.add(gVo);
			}
			
		} catch(SQLException e) {
			System.out.println("error: " + e);
		}
		
		close();
		
		return gList;
	}
	
	public GuestVo getGuest(int no) {
		GuestVo gVo = new GuestVo();
		
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
		
		return gVo;
	}
}
