package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import repository.Dao;



@WebServlet("/dodelete.do")
public class dodelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public dodelete() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String user_name = request.getParameter("user_name");
		String title = request.getParameter("title");
		System.out.print(user_name+"É¾³ý");
		System.out.println(title+"É¾³ý");
		Dao dao = new Dao();
		dao.Delete(user_name, title);
		response.sendRedirect("domyspace.do");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
