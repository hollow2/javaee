package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Article;
import repository.Dao;

/**
 * Servlet implementation class doarticle
 */
@WebServlet("/doarticle.do")
public class doarticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public doarticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out = response.getWriter();
		Dao dao = new Dao();
		 List<Article> list = new ArrayList<>();
		 list = dao.find_Allarticle();
		 for(Article lists:list) {
			    System.out.println(lists.getId()+"--用户ID");
		    	System.out.println(lists.getTitle()+"--文章标题");
		    	System.out.println(lists.getContent()+"--文章内容");
		    	System.out.println(lists.getUser_name()+"--文章作者");
		    }
		 request.setAttribute("lists", list);
		 request.getRequestDispatcher("articlelist.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
