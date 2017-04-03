package com.CourseReg.DAO;

import java.util.Date;

import com.CourseReg.Database.connectionDao;
import com.CourseReg.Entity.*;
/**
 * Welcome to Student Course Registration!
 *
 *
 * @author  Group 4
 * @version 1.0
 * @name :  InstructorService
 * @description : This Instructor Data Access(DAO)class which allows the service class to access data which is related to instructor.
 * @since   2015-10-23
 **/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.CourseReg.Exception.MyApplicationException;

public class InstructorDAO {
	
	static Connection connection = null;
	Statement statement;
	MyApplicationException exception = new MyApplicationException();
	
	public InstructorDAO() {
		// TODO Auto-generated constructor stub
	}
	
	/**
     * @name :  InstructorDAO
     * @description : This method returns all the instructors.
     * @since   2015-10-23
     * @exception : throws any exception it gets to the Main class.
     */
	public InstructorEntity ViewAllInstructors(){
		
		
		InstructorEntity allInstructor = new InstructorEntity();
		
		
	
			try{
			 
				connection	= connectionDao.connectToDatabse();
				statement = (Statement) connection.createStatement(); 
			
				
			String query = "select * from instructor";
			ResultSet rs = statement.executeQuery(query);
			System.out.println(rs);
			int count = 0;
			while (rs.next()) {
				allInstructor.setInstructorID(rs.getString("InstructorID"));
				allInstructor.setLastName(rs.getString("LastName"));
				allInstructor.setFirstName(rs.getString("FirstName"));
				allInstructor.setGender(rs.getString("Gender"));
				allInstructor.setQualification(rs.getString("Qualification"));
				allInstructor.setHireDate(rs.getString("HireDate"));
				allInstructor.setSalary(rs.getString("Salary"));
				allInstructor.setContactNo(rs.getString("ContactNo"));
				count++;
				
			}
			connectionDao.closeConnection();
			if (count == 0) {
				exception.ApplicationException();
				System.out.println("***************************************************************");
				System.out.println("Instructor doesn't exist in the database!");
				System.out.println("***************************************************************");
			}

		}catch (Exception e){
			e.printStackTrace();
		}
			//closeConnection();
			return allInstructor; 
					
	}
	
	/**
     * @name :  InstructorDAO
     * @description : This method returns student details for specific professor.
     * @since   2015-10-23
     * @params :  InstructorID.
     * @exception : throws any exception it gets to the Main class.
     */
	
	public InstructorEntity ViewMyStudents(String InstructorID){
		InstructorEntity allInstructor = new InstructorEntity();
	    
			try{
			
				connection	= connectionDao.connectToDatabse();
				statement = (Statement) connection.createStatement(); 	
				
			statement = (Statement) connection.createStatement();    
			String query = "select c.coursecode,s.firstname,s.lastname,c.startdate,c.enddate,c.location,c.timings,c.quarter,l.emailid from course c, course_instructor ci,instructor i,student s,login l,enrollment e where ci.instructorid=i.instructorid and c.coursecode=ci.coursecode and e.coursecode=ci.coursecode and e.studentid = s.studentid and e.studentid = l.id and i.instructorid='"+Integer.parseInt(InstructorID)+"'" ;
			ResultSet rs = statement.executeQuery(query);
			int count = 0;
			while (rs.next()) {			
				allInstructor.setCourseCode(rs.getString("CourseCode"));
				allInstructor.setFirstName(rs.getString("FirstName"));
				allInstructor.setLastName(rs.getString("LastName"));
				allInstructor.setStartDate(rs.getString("StartDate"));
				allInstructor.setEndDate(rs.getString("EndDate"));
				allInstructor.setLocation(rs.getString("Location"));
				allInstructor.setTimings(rs.getString("Timings"));
				allInstructor.setQuarter(rs.getString("Quarter"));
				allInstructor.setEmailID(rs.getString("EmailID"));
				count++;
				
			}
			connectionDao.closeConnection();
			if (count == 0) {
				exception.ApplicationException();
				System.out.println("***************************************************************");
				System.out.println("Instructor doesn't exist in the database!");
				System.out.println("***************************************************************");
			}

		}catch (Exception e){
			e.printStackTrace();
		}
			//closeConnection();
			                            return allInstructor;
					
	}
	
	/**
     * @name :  InstructorDAO
     * @description : This method returns Instructor details based on Instructor ID.
     * @since   2015-10-23
     * @params :  InstructorID.
     * @exception : throws any exception it gets to the Main class.
     */
	public InstructorEntity ViewMyProfile(String InstructorID){
		InstructorEntity allInstructor = new InstructorEntity();
		
		
	    
			try{
				
				connection	= connectionDao.connectToDatabse();
				statement = (Statement) connection.createStatement(); 
			statement = (Statement) connection.createStatement();    
			String query = "select firstname,lastname,gender,qualification,address from instructor where instructorid ='"+Integer.parseInt(InstructorID)+"'";
			ResultSet rs = statement.executeQuery(query);
			System.out.println(rs);
			int count = 0;
			while (rs.next()) {
			
				allInstructor.setFirstName(rs.getString("FirstName"));
				allInstructor.setLastName(rs.getString("LastName"));
				allInstructor.setGender(rs.getString("Gender"));
				allInstructor.setQualification(rs.getString("Qualification"));
				allInstructor.setAddress(rs.getString("Address"));
				count++;
				
			}
			connectionDao.closeConnection();
			if (count == 0) {
				exception.ApplicationException();
				System.out.println("***************************************************************");
				System.out.println("Instructor doesn't exist in the database!");
				System.out.println("***************************************************************");
			}

		}catch (Exception e){
			e.printStackTrace();
		}
			//closeConnection();
			return allInstructor; 
					
	}
	
	
	/**
     * @name :  InstructorDAO
     * @description : This method returns Instructor schedule details based on Instructor ID.
     * @since   2015-10-23
     * @params :  InstructorID.
     * @exception : throws any exception it gets to the Main class.
     */
	public InstructorEntity ViewMySchedule(String InstructorID){
		InstructorEntity allInstructor = new InstructorEntity();
		
	    
			try{
				
				connection	= connectionDao.connectToDatabse();
				statement = (Statement) connection.createStatement(); 
			    
			String query = "select c.coursecode,c.startdate,c.location,c.timings,c.quarter,c.enddate from course c,course_instructor ci,instructor i where ci.instructorid=i.instructorid and c.coursecode=ci.coursecode and i.instructorid='"+Integer.parseInt(InstructorID)+"'";
			ResultSet rs = statement.executeQuery(query);
			int count = 0;
			while (rs.next()) {
				allInstructor.setCourseCode(rs.getString("CourseCode"));
				allInstructor.setStartDate(rs.getString("StartDate"));
				allInstructor.setLocation(rs.getString("Location"));
				allInstructor.setTimings(rs.getString("Timings"));
				allInstructor.setTimings(rs.getString("Quarter"));
				allInstructor.setEndDate(rs.getString("EndDate"));
				count++;
				
			}
			connectionDao.closeConnection();
			if (count == 0) {
				exception.ApplicationException();
				System.out.println("***************************************************************");
				System.out.println("Instructor doesn't exist in the database!");
				System.out.println("***************************************************************");
			}

		}catch (Exception e){
			e.printStackTrace();
		}
			//closeConnection();
			return allInstructor; 
					
	}
	//add Instructors
	/*public void addInstructor(String InstructorID, String LastName, String FirstName, String Gender, String Qualification, String HireDate, int Salary, String ContactNo, String Address){
		try{
			connectToDatabse();
			statement = (Statement) connection.createStatement();    
			String query = "insert into Instructor values('" + InstructorID + "','"+ LastName +"','"+ FirstName +"','"+ Gender +"','"+ Qualification +"','"+ HireDate +"','"+ Salary +"','"+ ContactNo +"','"+ Address + "')";
			statement.executeUpdate(query);
			

		}catch (Exception e){
			e.printStackTrace();
		}
			closeConnection();
	 
	}*/
	
	// delete Instructors by Instructor ID
	
	/**
     * @name :  InstructorDAO
     * @description : This method deletes Instructor based on Instructor ID.
     * @since   2015-10-23
     * @params :  InstructorID.
     * @exception : throws any exception it gets to the Main class.
     */
	public void deleteInstructor(String InstructorID){
		try{
			
			connection	= connectionDao.connectToDatabse();
			statement = (Statement) connection.createStatement(); 
			    
			String query = "delete from Instructor where InstructorID = '" + Integer.parseInt(InstructorID) + "'" ;
			statement.executeUpdate(query);
			connectionDao.closeConnection();
			

		}catch (Exception e){
			System.out.println(e.getMessage());
			exception.ApplicationException();
		}
			//closeConnection();
	 
	}
	
	/**
     * @name :  InstructorDAO
     * @description : This method updates salary for Instructor based on Instructor ID.
     * @since   2015-10-23
     * @params :  InstructorID.
     * @exception : throws any exception it gets to the Main class.
     */
	
	// update  Instructors by Instructor ID
	public void updateInstructor(String InstructorID, String Salary){
	try{
		connection	= connectionDao.connectToDatabse();
		statement = (Statement) connection.createStatement(); 
		    
		String query = "update Instructor set Salary ="+ "'" + Integer.parseInt(Salary) + "'" + "where InstructorID = '"+ Integer.parseInt(InstructorID) + "'";
		statement.executeUpdate(query);
		connectionDao.closeConnection();
	}catch (Exception e){
		System.out.println(e.getMessage());
		exception.ApplicationException();  
	}
		//closeConnection();
 
}
	
}