package com.dhz.librarydemo;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;

	public class BaseDao {
		private static String driver="com.mysql.cj.jdbc.Driver";
		private static String url="jdbc:mysql://localhost:3306/bookmanagement?characterEncoding=UTF-8&useSSL=false";
		private static String user="root";
		private static String pwd="123";
		private static Connection conn=null;
	    public static Connection getConn(){
	    	try {
				Class.forName(driver);
				conn=DriverManager.getConnection(url, user, pwd);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return conn;
	    }

	}



