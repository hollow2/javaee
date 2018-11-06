package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Article;
import domain.User;
import repository.Dao;

/**
 * Servlet implementation class domyspace
 */
@WebServlet("/domyspace.do")
public class domyspace extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public domyspace() {
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
		Cookie[] cookies = request.getCookies();
		String username = null;
		if(cookies != null) {
			for(Cookie cookie:cookies) {
				if(cookie.getName().equals("users")) {
					username = cookie.getValue();
					break; 
				}
			}
			if(username==null) {
				out.println("请登录后进入！");
				response.setHeader("refresh", "2,url=signin.jsp");
			}else {
		   System.out.println(username+"ok");
		   Dao dao = new Dao();
			 List<Article> List = new ArrayList<>();
			 List = dao.findUserarticle(username);
			 List<User> LList = new ArrayList<>();
			 LList = dao.find_users(username);
		   for(Article list: List) {
			   System.out.println(list.getTitle()+"标题");
		   }
			   request.setAttribute("lists", List);
			   request.setAttribute("llists", LList);
			   request.getRequestDispatcher("myspace.jsp").forward(request, response);
		   }
		}else {
			out.println("请登录后再进入！");
			response.setHeader("refresh", "2,url=register.jsp");
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
