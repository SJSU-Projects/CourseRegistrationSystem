package com.CourseReg.DAO;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.CourseReg.Database.*;

/**
 * Welcome to Course Registration!!
 *
 *
 * @author  Group 4
 * @version 1.0
 * @name :  LoginService
 * @description : This Login Data Access(DAO)class which allows the service class to access data which is related to login.
 * @since   2015-10-26
 **/

public class LoginDAO {

	
	static Connection connection = null;
	Statement statement;
	
	public LoginDAO() {
		// TODO Auto-generated constructor stub
	}

	/**
     * @name :  LoginDAO
     * @description : This method takes the EmailID and Password to validate them checking with the adtabase record.
     * @since   2015-10-26
     * @params :  EmailID, Password.
     * @exception : throws any exception it gets to the Main class.
     */
	
	public String existingUserMisMatch(String EmailID, String Password) {
    	try {
			
    		
    		
    		connection	= connectionDao.connectToDatabse();
    		statement = (Statement) connection.createStatement(); 
    		
    		
    		String query = "Select * from Login" ;
    		ResultSet rs = statement.executeQuery(query);
    		String result = "";
    		
			while (rs.next()) {

				if ((rs.getString(2).matches(EmailID)) && (rs.getString(3).matches(Password))){
				
			
					result = "Welcome\t"+ EmailID +"\t"+ "Your credentials are valid\n"+ "To see all the courses offered: http://localhost:8080/CourseReg/v1/courses/allcourses\n" + " To see the courses offered in San Jose: http://localhost:8080/CourseReg/v1/courses/location/San Jose\n" + "To display all students: http://localhost:8080/CourseReg/v1/students/allstudents";
					
				}
				
			}
			
				if(result == "")
				{
					result =  "Sorry\t"+ EmailID +"\t"+ "Your credentials are NOT valid";
				}
				
				connectionDao.closeConnection();
				
				return result;
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			connectionDao.closeConnection();
		}
       		
		return null;	

	}
	
	

	
}

