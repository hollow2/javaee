package web;


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
import javax.servlet.http.HttpSession;

import domain.User;
import repository.Dao;

/**
 * Servlet implementation class dologin
 */
@WebServlet("/login.do")
public class dologin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dologin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    {
	    	request.setCharacterEncoding("UTF-8");
	    	response.setContentType("text/html;charset=UTF-8");
	    	PrintWriter out = response.getWriter();
	    	String username = request.getParameter("username");
	    	String password = request.getParameter("password");
	    	String checkbox =request.getParameter("checkbox");
	    	System.out.println(username);
	    	System.out.println(password);
	    	System.out.println(checkbox);
	    	if(username==null||password==null) {
	    		out.println("用户名或密码为空，请重新输入！");
	    		response.setHeader("refresh", "3,url=signin.jsp");
	    	}else {
	    	Dao Dao = new Dao();
	    	User user = Dao.login(username);
	    	if(user==null) {
	    		out.println("没有这个用户！请重新输入！");
	    		response.setHeader("refresh", "3,url=signin.jsp");
	    	}
	    	String name = user.getUsername();
	    	String ppassword = user.getPassword();
	    	System.out.println(name);
	    	System.out.println(ppassword);
	    	if(username.equals(name)&&password.equals(ppassword)) {
	    		Cookie cookie = new Cookie("users",username);
	    		cookie.setMaxAge(60*5);
	    		response.addCookie(cookie);
	    		HttpSession session = request.getSession();
	            session.setAttribute("user", username);
				session.setMaxInactiveInterval(60*2);
	    		request.getRequestDispatcher("index.jsp").forward(request, response);
	    	}else {
	    		out.println("密码错误，请重新输入！");
	    		response.setHeader("refresh", "3,url=signin.jsp");
	    	}
	    	}
	    	}// TODO Auto-generated method stub
	}

}
