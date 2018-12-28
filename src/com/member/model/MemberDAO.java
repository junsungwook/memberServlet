package com.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private static MemberDAO instance = new MemberDAO();
	public static MemberDAO getInstance() {
		return instance;
	}
	private Connection getConnection() throws Exception{
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/member");
		return ds.getConnection();
	}
	//id중복체크
	public String idCheck(String id) {
		String result = "no";
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
				result = "ok";
			} 
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con,ps);
		}
		return result;	
	}
	//회원가입
	public void joinInsert(Member m) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			String sql = "insert into member values(?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, m.getId());
			ps.setString(2, m.getPassword());
			ps.setString(3, m.getName());
			ps.setInt(4, m.getAge());
			ps.setString(5, m.getGender());
			ps.setString(6, m.getEmail());		
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con,ps);
		}
	}
	//로그인
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
	//상세보기
	public Member memView(String id) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		Member mem = null;
		try {
			con = getConnection();
			String sql = "select * from member where id='"+id+"'";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				mem = new Member();
 				mem.setId(rs.getString("id"));
				mem.setPassword(rs.getString("password"));
				mem.setName(rs.getString("name"));
				mem.setAge(rs.getInt("age"));
				mem.setGender(rs.getString("gender"));
				mem.setEmail(rs.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, st, rs);
		}
		return mem;
	}
	//정보수정
	public void memUpdate(Member mem) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			String sql = "update member set password=?, name=?, age=?, gender=?, email=? where id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, mem.getPassword());
			ps.setString(2, mem.getName());
			ps.setInt(3, mem.getAge());
			ps.setString(4, mem.getGender());
			ps.setString(5, mem.getEmail());
			ps.setString(6, mem.getId());
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con,ps);
		}
	}
	//전체조회
	public ArrayList<Member> memberList() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Member> arr = new ArrayList<>();
		try {
			con = getConnection();
			String sql = "select * from member";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Member mem = new Member();
				mem.setId(rs.getString("id"));
				mem.setPassword(rs.getString("password"));
				mem.setName(rs.getString("name"));
				mem.setAge(rs.getInt("age"));
				mem.setGender(rs.getString("gender"));
				mem.setEmail(rs.getString("email"));
				arr.add(mem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, st, rs);
		}
		return arr;
	}
	//탈퇴
	public void memDelete(String id) {
		Connection con = null;
		Statement st = null;
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "delete from member where id='"+id+"'";
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con,st);
		}
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
