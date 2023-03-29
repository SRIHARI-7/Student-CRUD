package com.kce.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConfiguration {
	
	private static Connection conn=null;
	
	public static Connection getConnection() {
		if(conn==null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/kce", "root", "root");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return conn;
		}
		else {
			
			return conn;
		}
		
	}
	public static void main(String []args) {
		System.out.println(getConnection());
	}
	
}
