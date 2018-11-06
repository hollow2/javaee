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
		System.out.println(title+"-д/������-");
		String content = request.getParameter("content");
		System.out.println(content+"д/������");
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
				System.out.println("���Ǹ���");
			       dao.add_article(user_name, title, content);//����
			}
			else {
				System.out.println("��������");
				dao.insert_article(user_name, title, content);//����
			}
			out.println("���·���ɹ������Ϸ��ظ��˿ռ�");
			request.getRequestDispatcher("domyspace.do").forward(request, response);
		}
		else {
			out.println("���¼���ٽ��з���");
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
