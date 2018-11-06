package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Unit {
	static String driver = "com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/blog?useUnicode=true&characterEncoding=utf8";
	static String user = "root";
	static String password = "goodbad123.";
	
       static {
    	   try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       }
       public static Connection getConnection() throws SQLException {
    	   Connection conn = DriverManager.getConnection(url,user,password);
    	   return conn;
       }
       
       
           public static void closeResource(Connection conn, Statement st,ResultSet rs) {
    	   if(rs!= null) {
    		   try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	   }
    	   if(st!= null) {
    		   try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	   }
    	   if(conn!= null) {
    		   try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	   }
       }
	

	
}

