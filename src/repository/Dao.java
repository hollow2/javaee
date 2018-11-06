package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import domain.Article;
import domain.Content;
import domain.User;


public class Dao {
	public void register(User user) {
		// TODO Auto-generated method stub
		String sql = "insert into t_users(username,password,email,phone,qq) value(?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = Unit.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getPhone());
			stmt.setString(5, user.getQq());
			stmt.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
		Unit.closeResource(conn, stmt,null);
		}
	}
	public User login(String userName) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet tr = null;
		String sql = "select * from t_users where username=?";
		try {
			conn = Unit.getConnection();
			User user = new User();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
		    tr = stmt.executeQuery();
			if(tr.next()) {
				user.setUsername(tr.getString("username"));
				user.setPassword(tr.getString("password"));
				user.setEmail(tr.getString("email"));
				user.setPhone(tr.getString("phone"));
				user.setQq(tr.getString("qq"));
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			new Unit();
			Unit.closeResource(conn, stmt, tr);
		}
		return null;
	}
	public boolean check(String userName) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet st = null;
		String sql = "select * from t_users where username=?";
	    try {
			conn = Unit.getConnection();
		    stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
		    st = stmt.executeQuery();
			if(st.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			new Unit();
			Unit.closeResource(conn, stmt, st);
		}
		return false;
	}
	

	
	

	
	public List<Article> findUserarticle(String username) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet st = null;
		
		List<Article> list = new ArrayList<>();
		String sql = "select * from t_article where user_name=?";
		try {
			conn = Unit.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			st = stmt.executeQuery();
			while(st.next()) {
				Article article = new Article();
			      article.setId(st.getInt("id"));
			      article.setTitle(st.getString("title"));
			      article.setContent(st.getString("content"));
			      article.setUser_name(st.getString("user_name"));
			      System.out.print("!!!!"+article.getTitle()+"hhhh");
			      list.add(article); 
			      
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			new Unit();
			Unit.closeResource(conn, stmt, st);		
		}
		return null;
	}
	

	
	
	
	
	public boolean check_article(String user_name, String title) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet st = null;
		String sql = "select * from t_article where user_name=? and title=?";
		try {
			conn = Unit.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user_name);
			stmt.setString(2, title);
			st = stmt.executeQuery();
			if(st.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Unit.closeResource(conn, (Statement) stmt, st);
		}
		return false;
	}
	
	public void add_article(String user_name, String title, String content) {
		// TODO Auto-generated method stub
		System.out.println("更新开始--");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet st = null;
		String sql = "Update t_article set content=? where title=? and user_name=?";
		
		try {
			 conn = Unit.getConnection();
			 stmt = conn.prepareStatement(sql);
			 stmt.setString(1, content);
			 stmt.setString(2, title);
			 stmt.setString(3, user_name);
			 stmt.executeUpdate();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Unit.closeResource(conn, (Statement) stmt, st);
		}
	}
	
	public void insert_article(String user_name, String title, String content) {
		// TODO Auto-generated method stub
		System.out.println("新增开始");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet st = null;
		String sql = "insert into t_article (user_name,title,content)value(?,?,?)";
		try {
			conn = Unit.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user_name);
			stmt.setString(2, title);
			stmt.setString(3,content);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		Unit.closeResource(conn, (Statement) stmt, st);
		}
		
	}
	
	
	
	public List<User> find_Allusers() {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet st = null;
		
		List<User> list = new ArrayList<>();
		String sql = "select * from users";
		try {
			conn =Unit.getConnection();
			stmt = conn.prepareStatement(sql);
			st = stmt.executeQuery();
			while(st.next()) {
			      User user = new User();
			      user.setUsername(st.getString("username"));
			      user.setEmail(st.getString("email"));
			      user.setPhone(st.getString("phone"));
			      user.setPassword(st.getString("password"));
			      list.add(user);
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("find all users is ok");
			e.printStackTrace();
		}finally {
			Unit.closeResource(conn, (Statement) stmt, st);
		}
		return null;
	}
	
	public List<User> find_users(String user_name) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet st = null;
		
		List<User> list = new ArrayList<>();
		String sql = "select * from t_users where username =?";
		try {
			conn = Unit.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user_name);
			st = stmt.executeQuery();
			while(st.next()) {
			    User user = new User();
			      user.setUsername(st.getString("username"));
			      user.setEmail(st.getString("email"));
			      user.setPhone(st.getString("phone"));
			      user.setQq(st.getString("qq"));
			      user.setPassword(st.getString("password"));
			      list.add(user);
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("find all shops is ok");
			e.printStackTrace();
		}finally {
			Unit.closeResource(conn, (Statement) stmt, st);
		}
		return null;
	}
	

	
	public List<Article> find_Allarticle() {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet st = null;
		List<Article> list = new ArrayList<>();
		String sql = "select * from t_article";
		try {
			conn =Unit.getConnection();
			stmt = conn.prepareStatement(sql);
			st = stmt.executeQuery();
			while(st.next()) {
			      Article article = new Article();
			      article.setId(st.getInt("id"));
			      article.setTitle(st.getString("title"));
			      article.setContent(st.getString("content"));
			      article.setUser_name(st.getString("user_name"));
			      list.add(article);
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("find all article is not ok");
			e.printStackTrace();
		}finally {
			Unit.closeResource(conn, (Statement) stmt, st);
		}
		return null;
	}
	public List<Content> find_Allcontent() {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet st = null;
		List<Content> list = new ArrayList<>();
		String sql = "select * from t_content";
		try {
			conn =Unit.getConnection();
			stmt = conn.prepareStatement(sql);
			st = stmt.executeQuery();
			while(st.next()) {
			      Content content = new Content();
			      content.setId(st.getInt("content_id"));
			      content.setUser_name(st.getString("user_name"));
			      content.setUser_content(st.getString("user_content"));
			      list.add(content);
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("find all article is not ok");
			e.printStackTrace();
		}finally {
			Unit.closeResource(conn, (Statement) stmt, st);
		}
		return null;
	}
	
	public List<Article> find_Articlecontent(String user_name, String title) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet st = null;
		List<Article> list = new ArrayList<>();
		String sql = "select * from t_article where title =? and user_name=? ";
		System.out.println("i try"+user_name+"--");
		System.out.println("to try"+title+"--");
		try {
			conn =Unit.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, title);
			stmt.setString(2,user_name);
			st = stmt.executeQuery();
			while(st.next()) {
			      Article article = new Article();
			      article.setId(st.getInt("id"));
			      article.setTitle(st.getString("title"));
			      article.setContent(st.getString("content"));
			      article.setUser_name(st.getString("user_name"));
			      list.add(article);
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("find all article content is not ok");
			e.printStackTrace();
		}finally {
			Unit.closeResource(conn, (Statement) stmt, st);
		}
		return null;
	}
	
	public void Delete(String user_name, String title) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet st = null;
		String sql = "delete from t_article where user_name=? and title=?";	
		try {
			conn = Unit.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user_name);
			stmt.setString(2, title);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Unit.closeResource(conn, (Statement) stmt, st);
		}
		
	}
	

	
	public void DeleteUser(String username, String password) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet st = null;
		String sql = "delete from users where username=? and password=?";
		try {
			conn = Unit.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Unit.closeResource(conn, (Statement) stmt, st);
		}
		
	}
	
	

}
