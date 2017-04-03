package com.CourseReg.DAO;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.*;
import java.util.*;

import com.CourseReg.Database.connectionDao;
import com.CourseReg.Entity.*;
import com.CourseReg.Exception.MyApplicationException;

/**
 * Welcome to Course Registration!!
 *
 * @author  Group 4
 * @version 1.0
 * @name :  StudentService
 * @description : This Student Data Access(DAO)class allows the student service class to access student data from the mysql database.
 * @since   2015-10-27
 **/

public class StudentDAO {

		
	static Connection connection = null;
	Statement statement;
	MyApplicationException exception = new MyApplicationException();
	
	public StudentDAO() {
		
	}
	
	/**
	 * @name :  ViewAllStudents
	 * @description : This method returns all the Student information.
	 * @since   2015-10-27
	 * @exception : throws any exception it gets to the Main class.
	 */
	
	public List<StudentEntity> ViewAllStudents(){
	
		ArrayList<StudentEntity> allStudents = new ArrayList<StudentEntity>();
		StudentEntity studentEntity;
		String myString = null;
		String returnString = null;
	    
			try{
				connection	= connectionDao.connectToDatabse();
				statement = (Statement) connection.createStatement(); 
			   
			String query = "select * from student";
			ResultSet rs = statement.executeQuery(query);
			int count = 0;
			while (rs.next()) {
				StudentEntity allStudent = new StudentEntity();
				allStudent.setStudentID(rs.getInt("StudentID"));
				allStudent.setLastName(rs.getString("LastName"));
				allStudent.setFirstName(rs.getString("FirstName"));
				allStudent.setDateOfBirth(rs.getString("DateOfBirth"));
				allStudent.setGender(rs.getString("Gender"));
				allStudent.setContactNo(rs.getString("ContactNo"));
				allStudent.setAddress(rs.getString("Address"));
				allStudents.add(allStudent);
				count++ ;
			}
			 connectionDao.closeConnection();
			System.out.println(allStudents);
			if (count == 0) {
				exception.ApplicationException();
				System.out.println("***************************************************************");
				System.out.println("Student doesn't exist in the database!");
				System.out.println("***************************************************************");
			}
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return allStudents;
	}
	
	/**
	 * @name :  ViewStudent based on StudentID
	 * @description : This method returns all the Student information for a particular student
	 * @since   2015-10-26
	 * @exception : throws any exception it gets to the Main class.
	 */

public StudentEntity ViewStudent(int StudentID){
	    
	    StudentEntity studentEntity = null;
	       
	      try{
	      connection = connectionDao.connectToDatabse();
	      statement = (Statement) connection.createStatement();    
	      String query = "select * from student where studentid="+StudentID;
	      ResultSet rs = statement.executeQuery(query);
	      int count = 0;
	      if (rs.next()) {
	        StudentEntity allStudent = new StudentEntity();
	        allStudent.setStudentID(rs.getInt("StudentID"));
	        allStudent.setLastName(rs.getString("LastName"));
	        allStudent.setFirstName(rs.getString("FirstName"));
	        allStudent.setDateOfBirth(rs.getString("DateOfBirth"));
	        allStudent.setGender(rs.getString("Gender"));
	        allStudent.setContactNo(rs.getString("ContactNo"));
	        allStudent.setAddress(rs.getString("Address"));
	        studentEntity = allStudent;
	       }
	          
	      connectionDao.closeConnection();
	    }catch (Exception e){
	      exception.ApplicationException();	
	      e.printStackTrace();
	    }
	    //connectionDao.closeConnection();
	    return studentEntity;
	  }
	
	/**
	 * @name :  Updates Student information in the Student table
	 * @description : This method Updates the student profile details in the database
	 * @since   2015-10-27
	 * @param : The method course code and location field as params
	 * @exception : throws any exception it gets to the Main class.
	 */
	
	public void updateStudent(StudentEntity s)
	{
		
		try{
			connection	= connectionDao.connectToDatabse();
			statement = (Statement) connection.createStatement();   
			//String query = "update student set LastName ='"+ s.getLastName() + "',"+ "FirstName='"+s.getFirstName() +"',contactno='"+s.getContactNo()+"',gender='"+ s.getGender()+"',dateofbirth='"+ s.getDateOfBirth()+"',address='"+s.getAddress()+ "' where studentID="+s.getStudentID();
			String query = "update student set LastName ='"+ s.getLastName() + "' where studentID="+s.getStudentID();
			statement.executeUpdate(query);
			 connectionDao.closeConnection();
			
		}catch (Exception e){
			System.out.println(e.getMessage());
			exception.ApplicationException();  
		}
			//closeConnection();
	 
	}
	
	/**
	 * @name :  Delete a Student 
	 * @description : This method deletes a student details from the database
	 * @since   2015-10-27
	 * @param : The method takes student ID as input and deletes from the tables enrollment,student & login
	 * @exception : throws any exception it gets to the Main class.
	 */
		public void deleteStudent(int StudentID)
		{
			
			try{
				connection	= connectionDao.connectToDatabse();
				statement = (Statement) connection.createStatement(); 
				 
				String query1 = "delete from enrollment where studentID="+StudentID;
				String query2 = "delete from student where studentID="+StudentID;
				String query3 = "delete from login where ID="+StudentID;
				statement.executeUpdate(query1);
				statement.executeUpdate(query2);
				statement.executeUpdate(query3);
				
				connectionDao.closeConnection();
				
			}catch (Exception e){
				System.out.println(e.getMessage());
				exception.ApplicationException();  
			}
				//closeConnection();
		 
		}
		/**
		 * @name :  Delete enrollment
		 * @description : This method deletes enrollment from the database.
		 * @since   2015-10-27
		 * @param : The method takes student ID as input and deletes from the tables enrollment
		 * @exception : throws any exception it gets to the Main class.
		 */
			public void deleteEnrollment(String StudentID, String courseCode)
			{
				
				try{
					connection	= connectionDao.connectToDatabse();
					statement = (Statement) connection.createStatement(); 
					 
					String query1 = "delete from enrollment where studentID="+StudentID+ " and courseCode=" + "'" + courseCode + "'";
					statement.executeUpdate(query1);
					
					connectionDao.closeConnection();
					
				}catch (Exception e){
					System.out.println(e.getMessage());
					exception.ApplicationException();  
				}
					//closeConnection();
			 
			}
		
		
	/**
	 * @name :  Add Student
	 * @description : This method inserts a new student into the database
	 * @since   2015-10-27
	 * @param : The method takes all the required fields for inserting a new record into the tables login & student
	 * @exception : throws any exception it gets to the Main class.
	 */
		
		public void insertStudent(StudentEntity S) {
					
				try{
					connection	= connectionDao.connectToDatabse();
					statement = (Statement) connection.createStatement(); 
					
					String query1 = "insert into login(emailid,password,role) values('"+S.getEmailID()+"','"+S.getPassword()+"','STUDENT')";
					statement.executeUpdate(query1);						
					ResultSet rs = statement.executeQuery("select * from login order by id desc limit 1");
						while(rs.next()) {
						String query2 = "insert into student(studentid,LastName,FirstName,DateOfBirth,Gender,ContactNo,Address) values("+rs.getInt("ID")+",'"+S.getLastName()+"','"+S.getFirstName()+"','"+S.getDateOfBirth()+"','"+S.getGender()+"','"+S.getContactNo()+"','"+S.getAddress()+"')";
						statement.executeUpdate(query2);
						}
					connectionDao.closeConnection();
					}catch (Exception e){
						System.out.println(e.getMessage());
						exception.ApplicationException();  
					}
						//closeConnection();
				 
				}
				
			
			/**
			 * @name :  Enroll Student
			 * @description : This method inserts a student record into the enrollment table
			 * @since   2015-10-27
			 * @param : The method takes studentid and coursecodes as input for inserting a new record in enrollment table
			 * @exception : throws any exception it gets to the Main class.
			 */
			
			public void enrollStudent(int StudentID,String CourseCode)
				{
					
					try{
						connection	= connectionDao.connectToDatabse();
						statement = (Statement) connection.createStatement();
						
						String query = "insert into enrollment(StudentID,CourseCode,paymentdate) values("+StudentID+",'"+CourseCode+"',now())";
						int sqlstat = statement.executeUpdate(query);
						if (sqlstat == 0) { String status = "Congratulations !! Successfully enrolled"; }
						else { String status = "Please make sure you have registered or <=5 courses/perquarter"; }
						connectionDao.closeConnection();
					}catch (Exception e){
						System.out.println(e.getMessage());
						exception.ApplicationException();  
					}
						//closeConnection();
						
						
				
				}
			

			/**
			 * @name : Add Student
			 * @description : This method inserts a new student in the database
			 * @since 2015-10-23
			 * @param :Login[] loginInsert - Login information - Email ID, password.
			 *         Student[] studentInsert - Student Information - First name, last name, address.
			 *         record in student table
			 * @exception : throws any exception it gets to the Main class.
			 */
			public void addStudent(LoginEntity[] loginInsert, StudentEntity[] studentInsert) {

				try {
					/**
					 * @description : Set Isolation level = 2 (Read commit). Reset to
					 *              default after Set Isolation level = 2 (Read commit).
					 *              Reset to default after
					 */

					connection	= connectionDao.connectToDatabse();
					statement = (Statement) connection.createStatement();

					connection.setAutoCommit(false);
					connection.setTransactionIsolation(2);

					int i = 0;
					while (loginInsert.length > i) {

						String query = "INSERT INTO Login" + "(EmailID, Password, Role)" + "VALUES ('"
								+ loginInsert[i].getEmailID() + "','" + loginInsert[i].getPassword() + "','" + "Student" + "')";
						statement.executeUpdate(query);

						query = "Select * from Login";
						ResultSet rs = statement.executeQuery(query);
						rs.last();

						int studentID = (rs.getInt("ID"));
						statement.executeUpdate("INSERT INTO Student"
								+ "(StudentID, LastName, FirstName, DateOfBirth, Gender, ContactNo, Address)" + "VALUES ('"
								+ studentID + "','" + studentInsert[i].getLastName() + "','" + studentInsert[i].getFirstName()
								+ "','" + studentInsert[i].getDateOfBirth() + "','" + studentInsert[i].getGender() + "','"
								+ studentInsert[i].getContactNo() + "','" + studentInsert[i].getAddress() + "')");

						i++;
					}
					connection.setAutoCommit(true);
					connection.setTransactionIsolation(1);
					connectionDao.closeConnection();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					exception.ApplicationException();
				}

			}
			
			/**
			 * @name : Delete a Student
			 * @description : This method deletes multiple or single student from the
			 *                student table
			 * @since 2015-10-23
			 * @param : List<Integer> studentIDList - List of Student IDs to be deleted.
			 * @exception : throws any exception it gets to the Main class.
			 */

			public void deleteStudent(String[] studentIDList) {
				int i = 0;
				try {
					/**
					 * @description : Set Isolation level = 2 (Read commit). Reset to
					 *              default after Set Isolation level = 2 (Read commit).
					 *              Reset to default after
					 */

					connection	= connectionDao.connectToDatabse();
					statement = (Statement) connection.createStatement();

					connection.setAutoCommit(false);
					connection.setTransactionIsolation(2);

					while (studentIDList.length > i) {

						statement.execute("DELETE FROM Login WHERE ID = " + studentIDList[i]);
						i++;
					}

					connection.setAutoCommit(true);
					connection.setTransactionIsolation(1);
					connectionDao.closeConnection();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					exception.ApplicationException();
				}
			}

		
}

	
