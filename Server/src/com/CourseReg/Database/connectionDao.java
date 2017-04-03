package com.CourseReg.Database;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class connectionDao {
	
			// JDBC driver name and database URL
			static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
			static final String DB_URL = "jdbc:mysql://localhost/s4";

			// Database credentials
			static final String USER = "root";
			static final String PASS = "desire123";
			static Connection connection = null;
			
			public static Connection connectToDatabse() {

				/*if(connection!= null)
				{
					return connection;
				}*/
				try {
					// STEP 1: Register JDBC driver
					Class.forName("com.mysql.jdbc.Driver");

					// STEP 2: Open a connection
					connection = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
					return connection;
					
				} catch (Exception e) {
					e.printStackTrace();
				} finally {

				}
				return connection;			
		}
			
			public static void closeConnection() {
				try {
					if (connection != null)
						connection.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
				
				
			
			}
			


}
