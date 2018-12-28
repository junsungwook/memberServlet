package com.guestbook.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guestbook.model.GuestDAO;
import com.guestbook.model.GuestDTO;

/**
 * Servlet implementation class GuestInsert
 */
@WebServlet("/guestbook/create")
public class GuestInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuestInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		String grade = request.getParameter("grade");
		String ipaddr = request.getRemoteAddr();
		GuestDTO guest = new GuestDTO();
		guest.setName(name);
		guest.setContent(content);
		guest.setGrade(grade);
		guest.setIpaddr(ipaddr);
		GuestDAO dao = GuestDAO.getInstance();
		dao.Insert(guest);
		response.sendRedirect("list");
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
