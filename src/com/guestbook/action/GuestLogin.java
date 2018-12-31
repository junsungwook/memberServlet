package com.guestbook.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.guestbook.model.GuestDAO;
import com.guestbook.model.MemberDTO;
import com.member.model.MemberDAO;

/**
 * Servlet implementation class GuestLogin
 */
@WebServlet("/guestbook/login.dodo")
public class GuestLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuestLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		GuestDAO dao = GuestDAO.getInstance();
		int count = dao.userCheck(id,password);
		HttpSession session = request.getSession();
		if(count==1) {
			MemberDTO mdto = dao.getMember(id);
			session.setAttribute("mdto", mdto);
			response.sendRedirect("guestInsert.jsp");
		}else if(count==0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
            out.println("<script>alert('비밀번호가 다릅니다'); history.go(-1);</script>");
            out.flush(); 
		}else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
            out.println("<script>alert('없는 회원입니다'); history.go(-1);</script>");
            out.flush(); 
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
