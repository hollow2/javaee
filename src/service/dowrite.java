package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import repository.Dao;

/**
 * Servlet implementation class dowrite
 */
@WebServlet("/dowrite.do")
public class dowrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dowrite() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String title=request.getParameter("title");
		System.out.println(title+"-写/改文章-");
		String content = request.getParameter("content");
		System.out.println(content+"写/改文章");
		String user_name= null;
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie:cookies) {
				if(cookie.getName().equals("users")) {
					user_name = cookie.getValue();
					break; 
				}
			}
			System.out.println(user_name+"1111");
			Dao dao = new Dao();
		
			boolean flag = dao.check_article(user_name, title);
			System.out.println(flag+"1234");
			if(flag) {
				System.out.println("这是更新");
			       dao.add_article(user_name, title, content);//更新
			}
			else {
				System.out.println("这是新增");
				dao.insert_article(user_name, title, content);//新增
			}
			out.println("文章发表成功，马上返回个人空间");
			request.getRequestDispatcher("domyspace.do").forward(request, response);
		}
		else {
			out.println("请登录后再进行发表");
			response.setHeader("refresh", "2,url=signin.jsp");
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
