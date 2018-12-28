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
	//�Է�
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
	//������ȸ
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

	//��ȸ
	public ArrayList<GuestDTO> guestList() {
	   Connection con= null;
	   Statement st = null;
	   ResultSet rs = null;
	   ArrayList<GuestDTO> arr = new ArrayList<>();
	   String sql="";
	   try {
	     con = getConnection();
	     sql = "select * from guestbook";
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
	  return arr;
	}
	//�󼼺���
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
