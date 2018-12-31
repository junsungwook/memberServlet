package com.guestbook.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guestbook.model.GuestDAO;
import com.guestbook.model.GuestDTO;

/**
 * Servlet implementation class GuestSearch
 */
@WebServlet("/guestbook/search.dodo")
public class GuestSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuestSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String pageNum = request.getParameter("pageNum")==null?"1":request.getParameter("pageNum");
		String field = request.getParameter("field");
		String word = request.getParameter("word");
		GuestDAO dao = GuestDAO.getInstance();
		int currentPage = Integer.parseInt(pageNum);
		int pageSize = 5;
		int startRow = (currentPage-1)*pageSize+1; //2page -> 6번댓글부터
		int endRow = currentPage*pageSize;
		ArrayList<GuestDTO> arr = dao.guestList(field,word,startRow,endRow);
		System.out.println(arr.size());
		int count = dao.guestCount(field,word);
		//페이징에 필요한 값 구하기
		int totpage = count/pageSize+(count%pageSize==0?0:1);
		int blockpage =3; //[이전] 456 [다음]
		int startpage=((currentPage-1)/blockpage)*blockpage+1;
		int endpage=startpage+blockpage-1;
		
		if(endpage > totpage) endpage=totpage;
		
		request.setAttribute("totpage", totpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("blockpage", blockpage);
		request.setAttribute("field", field);
		request.setAttribute("word", word);
		
		request.setAttribute("lists", arr);
		request.setAttribute("count", count);
		RequestDispatcher dispatcher = request.getRequestDispatcher("searchResult.jsp");
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
