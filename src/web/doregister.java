package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import domain.User;
import repository.Dao;


/**
 * Servlet implementation class doregister
 */
@WebServlet("/register.do")
public class doregister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public doregister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("userName");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String phone =request.getParameter("phone");
		String qq = request.getParameter("qq");
		System.out.println(username);
		System.out.println(password);
		System.out.println(email);
		System.out.println(phone);
		System.out.println(qq);
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setPhone(phone);
		user.setQq(qq);
		    Dao Dao = new Dao();
		    boolean flag = Dao.check(username);
		    System.out.println(flag);
		    if(flag) {
		    	out.println("���û����ڣ�������ע�ᣡ");
		    	response.setHeader("refresh", "3,url=register.jsp");
		    }else {
		    Dao.register(user);
		    out.println("ע��ɹ���3�������½���棡");
			response.setHeader("refresh", "3,url=signin.jsp");
		    }
		    
		    
		} 
	

}
