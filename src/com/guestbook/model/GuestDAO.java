package com.guestbook.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class GuestDAO {
	private static GuestDAO instance = new GuestDAO();
	public static GuestDAO getInstance() {
		return instance;
	}
	private Connection getConnection() throws Exception{
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/member");
		return ds.getConnection();
	}
	//댓글삭제
	public void commentDelete(int num) {
		Connection con = null;
		Statement st = null;
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "delete from guestbook where num="+num;
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con,st);
		}
	}
	//멤버값가져오는
	public MemberDTO getMember(String id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MemberDTO mem = null;
		try {
			con = getConnection();
			String sql = "select * from member where id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs =ps.executeQuery();
			if(rs.next()) {
				mem = new MemberDTO();
				mem.setId(rs.getString("id"));
				mem.setPassword(rs.getString("password"));
				mem.setName(rs.getString("name"));
				mem.setAge(rs.getInt("age"));
				mem.setGender(rs.getString("gender")); 
				mem.setEmail(rs.getString("email")); 
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con,ps);
		}	
		return mem;
	}
	//유저로그인체크
	public int userCheck(String id, String password) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "select * from member where id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs =ps.executeQuery();
			if(rs.next()) {
				if(rs.getString("password").equals(password)) {
					result = 1;
				}
				else if(!rs.getString("password").equals(password)) {
					result = 0;
				}
			}
			else {
				result = -1;
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con,ps);
		}
		return result;	
	}
	//입력
	public void Insert(GuestDTO guest) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			String sql = "insert into guestbook values(guestbook_seq.nextval,?,?,?,sysdate,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, guest.getName());
			ps.setString(2, guest.getContent());
			ps.setString(3, guest.getGrade());
			ps.setString(4, guest.getIpaddr());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con,ps);
		}
	}
	//갯수조회
	public int guestCount() {
		int count = 0;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "select count(*) from guestbook";
			rs = st.executeQuery(sql);
			if(rs.next())
			count = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return count;
	}

	//조회 (페이징으로 변경)
	public ArrayList<GuestDTO> guestList(int startRow, int endRow) {
	   Connection con= null;
	   Statement st = null;
	   ResultSet rs = null; 
	   ArrayList<GuestDTO> arr = new ArrayList<>();
	   String sql="";
	   try {
	     con = getConnection();
	     sql = "select * from (select rownum rn,aa.* from (select * from guestbook order by num desc)aa) where rn>="+startRow+" and rn<="+endRow;
	     System.out.println(sql);
		 st = con.createStatement();
		 rs = st.executeQuery(sql);
		 while(rs.next()) {
			 GuestDTO guest = new GuestDTO();
			 guest.setNum(rs.getInt("num"));
			 guest.setName(rs.getString("name"));
			 guest.setContent(rs.getString("content"));
			 guest.setGrade(rs.getString("grade"));
			 guest.setCreated(rs.getString("created")); 
			 guest.setIpaddr(rs.getString("ipaddr")); 
			 arr.add(guest);
	     }
	  } catch (Exception e) {
	    e.printStackTrace();
	  }finally {
	     closeCon(con,st,rs);
	  }
	  System.out.println(arr.size());
	  return arr;
	}
	//상세보기
	public GuestDTO guestView(int num) {
	   Connection con= null;
	   Statement st = null;
	   ResultSet rs = null;
	   GuestDTO guest = null;
	   String sql="";
	   try {
	     con = getConnection();
	     sql = "select * from guestbook where num="+num;
		 st = con.createStatement();
		 rs = st.executeQuery(sql);
		 if(rs.next()) {
			 guest = new GuestDTO();
			 guest.setNum(rs.getInt("num"));
			 guest.setName(rs.getString("name"));
			 guest.setContent(rs.getString("content"));
			 guest.setGrade(rs.getString("grade"));
			 guest.setCreated(rs.getString("created")); 
			 guest.setIpaddr(rs.getString("ipaddr")); 
	     }
	  } catch (Exception e) {
	    e.printStackTrace();
	  }finally {
	     closeCon(con,st,rs);
	  }
	  return guest;
	}
	
	
	
	private void closeCon(Connection con, PreparedStatement ps) {
		try {
			if(ps!=null) ps.close();
			if(con!=null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	private void closeCon(Connection con, Statement st,ResultSet rs) {
		try {
			if(st!=null) st.close();
			if(con!=null) con.close();
			if(rs!=null) rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	private void closeCon(Connection con, Statement st) {
		try {
			if(st!=null) st.close();
			if(con!=null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
