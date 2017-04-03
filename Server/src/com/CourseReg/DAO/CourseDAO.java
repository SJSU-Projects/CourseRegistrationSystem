package com.CourseReg.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.CourseReg.Entity.*;
import com.CourseReg.Exception.MyApplicationException;
import com.CourseReg.DAO.*;
import com.CourseReg.Database.connectionDao;
/**
 * Welcome to Course Registration!!
 *
 * @author  Group 4
 * @version 1.0
 * @name :  CourseService
 * @description : This Course Data Access(DAO)class allows the course service class to access course data from the mysql database.
 * @since   2015-10-26
 **/
public class CourseDAO {
		
	static Connection connection = null;
	Statement statement;
	MyApplicationException exception = new MyApplicationException();
	
	public CourseDAO() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @name :  viewAllCourses
	 * @description : This method returns all the Course information.
	 * @since   2015-10-23
	 * @exception : throws any exception it gets to the Main class.
	 */
	public List<CourseEntity> viewAllCourses(){
		ArrayList<CourseEntity> allCourses = new ArrayList<CourseEntity>();
		CourseEntity courseEntity;
		String myString = null;
		String returnString = null;
	    	try{
	    		connection	= connectionDao.connectToDatabse();
				statement = (Statement) connection.createStatement();    
	    		String query = "select CourseCode,CourseTitle,Quarter,Capacity from course";
	    		ResultSet rs = statement.executeQuery(query);
	    		int count = 0;
			while (rs.next()) {
				CourseEntity allCourse = new CourseEntity();
				allCourse.setCourseCode(rs.getString("CourseCode"));
				allCourse.setCourseTitle(rs.getString("CourseTitle"));
				allCourse.setDescription(rs.getString("Quarter"));
				allCourse.setDescription(rs.getString("Capacity"));
				allCourses.add(allCourse);
				count++ ;
			}
			connectionDao.closeConnection();
			if (count == 0) {
				exception.ApplicationException();
				System.out.println("***************************************************************");
				System.out.println("Course doesn't exist in the database!");
				System.out.println("***************************************************************");
			}

		}catch (Exception e){
			e.printStackTrace();
		}
			//closeConnection();
		return allCourses;
	}
	/**
	 * @name :  viewAllCourses based on Quarter the course is conducted
	 * @description : This method returns all the Course information for a particular quarter
	 * @since   2015-10-23
	 * @exception : throws any exception it gets to the Main class.
	 */
	public List<CourseEntity> viewAllCoursesQuarter(String Quarter){
		ArrayList<CourseEntity> allCourses = new ArrayList<CourseEntity>();
		CourseEntity courseEntity;
		String myString = null;
		String returnString = null;
	    	try{
	    		connection	= connectionDao.connectToDatabse();
				statement = (Statement) connection.createStatement();
				
	    		String query = "select * from course where Quarter = '" + Quarter + "'";
	    		ResultSet rs = statement.executeQuery(query);
	    		int count = 0;
			while (rs.next()) {
				CourseEntity allCourse = new CourseEntity();
				allCourse.setCourseCode(rs.getString("CourseCode"));
				allCourse.setCourseTitle(rs.getString("CourseTitle"));
				allCourse.setDeptID(rs.getString("DeptID"));
				allCourse.setDescription(rs.getString("Description"));
				allCourse.setCourseFee(rs.getInt("CourseFee"));
				allCourse.setQuarter(rs.getString("Quarter"));
				allCourse.setLocation(rs.getString("Location"));
				allCourse.setStartDate(rs.getString("StartDate"));
				allCourse.setEndDate(rs.getString("EndDate"));
				allCourse.setTimings(rs.getString("Timings"));
				allCourse.setUnits(rs.getInt("Units"));
				allCourse.setCapacity(rs.getInt("Capacity"));
				allCourse.setFilledStatus(rs.getInt("FilledStatus"));
				allCourses.add(allCourse);
				count++ ;
			}
			connectionDao.closeConnection();
			if (count == 0) {
				exception.ApplicationException();
				System.out.println("***************************************************************");
				System.out.println("Course doesn't exist in the database!");
				System.out.println("***************************************************************");
			}

		}catch (Exception e){
			e.printStackTrace();
		}
			//closeConnection();
		return allCourses;
	}
	/**
	 * @name :  viewAllCourses - Paginated
	 * @description : This method returns Course information for a particular size
	 * @since   2015-10-23
	 * @exception : throws any exception it gets to the Main class.
	 */
	public List<CourseEntity> viewAllCoursesPage(int start,int size){
		ArrayList<CourseEntity> allCourses = new ArrayList<CourseEntity>();
		CourseEntity courseEntity;
		String myString = null;
		String returnString = null;
	    	try{
	    		connection	= connectionDao.connectToDatabse();
				statement = (Statement) connection.createStatement();
				
	    		String query = "select * from course";
	    		ResultSet rs = statement.executeQuery(query);
	    		int count = 0;
			while (rs.next()) {
				CourseEntity allCourse = new CourseEntity();
				allCourse.setCourseCode(rs.getString("CourseCode"));
				allCourse.setCourseTitle(rs.getString("CourseTitle"));
				allCourse.setDeptID(rs.getString("DeptID"));
				allCourse.setDescription(rs.getString("Description"));
				allCourse.setCourseFee(rs.getInt("CourseFee"));
				allCourse.setQuarter(rs.getString("Quarter"));
				allCourse.setLocation(rs.getString("Location"));
				allCourse.setStartDate(rs.getString("StartDate"));
				allCourse.setEndDate(rs.getString("EndDate"));
				allCourse.setTimings(rs.getString("Timings"));
				allCourse.setUnits(rs.getInt("Units"));
				allCourse.setCapacity(rs.getInt("Capacity"));
				allCourse.setFilledStatus(rs.getInt("FilledStatus"));
				allCourses.add(allCourse);
				count++ ;
			}
			connectionDao.closeConnection();
			if (count == 0) {
				exception.ApplicationException();
				System.out.println("***************************************************************");
				System.out.println("Course doesn't exist in the database!");
				System.out.println("***************************************************************");
			}

		}catch (Exception e){
			e.printStackTrace();
		}			
		return allCourses.subList(start,start + size);
	}
	/**
     * @name :  Department
     * @description : This method returns all the Departments.
     * @since   2015-10-23
     * @exception : throws any exception it gets to the Main class.
     */
	public List<DepartmentEntity> viewAllDepartment(){
		ArrayList<DepartmentEntity> allDepts = new ArrayList<DepartmentEntity>();
		String myString = null;
		String returnString = null;
	   	try{
	   		connection	= connectionDao.connectToDatabse();
			statement = (Statement) connection.createStatement();
			
			String query = "select DeptId,DeptName,course from department";
			ResultSet rs = statement.executeQuery(query);
			System.out.println(rs);
			int count = 0;
			while (rs.next()) {
				DepartmentEntity allDept = new DepartmentEntity();
				allDept.setDeptID(rs.getString("DeptID"));
				allDept.setDeptName(rs.getString("DeptName"));
				allDept.setCourse(rs.getString("Course"));
				allDepts.add(allDept);
				count++;
				
			}
			connectionDao.closeConnection();
			if (count == 0) {
				exception.ApplicationException();
				System.out.println("***************************************************************");
				System.out.println("No Departments");
				System.out.println("***************************************************************");
			}

		}catch (Exception e){
			e.printStackTrace();
		}
			//closeConnection();
			return allDepts; 
					
	}
	/**
     * @name :  Search for Courses by Department
     * @description : This method returns all the Departments.
     * @since   2015-10-23
     * @exception : throws any exception it gets to the Main class.
     */
	public List<CourseEntity> viewCoursesByDept(){
		ArrayList<CourseEntity> allCourses = new ArrayList<CourseEntity>();
		String myString = null;
		String returnString = null;
	   	try{
	   		connection	= connectionDao.connectToDatabse();
			statement = (Statement) connection.createStatement();
			
			String query = "select c.CourseCode,c.CourseTitle,c.DeptID from course as c,department as d where  c.deptid = d.deptid order by Deptname";
			ResultSet rs = statement.executeQuery(query);
			System.out.println(rs);
			int count = 0;
			while (rs.next()) {
				CourseEntity allCourse = new CourseEntity();
				allCourse.setCourseCode(rs.getString("CourseCode"));
				allCourse.setCourseTitle(rs.getString("CourseTitle"));
				allCourse.setDeptID(rs.getString("DeptID"));
				allCourses.add(allCourse);
				count++;
				
			}
			connectionDao.closeConnection();
			if (count == 0) {
				exception.ApplicationException();
				System.out.println("***************************************************************");
				System.out.println("No Departments");
				System.out.println("***************************************************************");
			}

		}catch (Exception e){
			e.printStackTrace();
		}
			//closeConnection();
			return allCourses; 
					
	}
	/**
	 * @name :  Search by Course Location
	 * @description : This method returns all the Course information when searched by Location
	 * @since   2015-10-23
	 * @param : The method takes course Location parameters as input
	 * @exception : throws any exception it gets to the Main class.
	 */
	public List<CourseEntity> searchCourseLocation(String Location){
		ArrayList<CourseEntity> allCourses = new ArrayList<CourseEntity>();
		String myString = null;
		String returnString = null;
		  try{
			    connection	= connectionDao.connectToDatabse();
				statement = (Statement) connection.createStatement();
				
			String query = "select CourseCode,CourseTitle,Quarter,Capacity from course where Location = '" + Location + "'" ;
			ResultSet rs = statement.executeQuery(query);
			int count = 0;
			while (rs.next()) {
				CourseEntity allCourse = new CourseEntity();
				allCourse.setCourseCode(rs.getString("CourseCode"));
				allCourse.setCourseTitle(rs.getString("CourseTitle"));
				allCourse.setDescription(rs.getString("Quarter"));
				allCourse.setDescription(rs.getString("Capacity"));
				allCourses.add(allCourse);
				count++ ;
			}
			connectionDao.closeConnection();
			if (count == 0) {
				exception.ApplicationException();
				System.out.println("***************************************************************");
				System.out.println("Location does not exist in the database!");
				System.out.println("***************************************************************");
			}

		}catch (Exception e){
			e.printStackTrace();
		}
			//closeConnection();
			return allCourses; 
					
	}
	/**
	 * @name :  Search by CourseFee
	 * @description : This method returns all the Course information when searched by Location
	 * @since   2015-10-23
	 * @param : The method takes course Location parameters as input
	 * @exception : throws any exception it gets to the Main class.
	 */
	public List<CourseEntity> searchCourseFee(int CourseFee){
		ArrayList<CourseEntity> allCourses = new ArrayList<CourseEntity>();
		String myString = null;
		String returnString = null;
		  try{
			  connection	= connectionDao.connectToDatabse();
				statement = (Statement) connection.createStatement();
				
			String query = "select * from course where CourseFee <'" + CourseFee + "'" ;
			ResultSet rs = statement.executeQuery(query);
			int count = 0;
			while (rs.next()) {
				CourseEntity allCourse = new CourseEntity();
				allCourse.setCourseCode(rs.getString("CourseCode"));
				allCourse.setCourseTitle(rs.getString("CourseTitle"));
				allCourse.setDeptID(rs.getString("DeptID"));
				allCourse.setDescription(rs.getString("Description"));
				allCourse.setCourseFee(rs.getInt("CourseFee"));
				allCourse.setQuarter(rs.getString("Quarter"));
				allCourse.setLocation(rs.getString("Location"));
				allCourse.setStartDate(rs.getString("StartDate"));
				allCourse.setEndDate(rs.getString("EndDate"));
				allCourse.setTimings(rs.getString("Timings"));
				allCourse.setUnits(rs.getInt("Units"));
				allCourse.setCapacity(rs.getInt("Capacity"));
				allCourse.setFilledStatus(rs.getInt("FilledStatus"));
				allCourses.add(allCourse);
				count++ ;
			}
			connectionDao.closeConnection();
			if (count == 0) {
				exception.ApplicationException();
				System.out.println("***************************************************************");
				System.out.println("Location does not exist in the database!");
				System.out.println("***************************************************************");
			}

		}catch (Exception e){
			e.printStackTrace();
		}
			//closeConnection();
			return allCourses; 
					
	}
	
	/**
	 * @name :  Search by Course Code
	 * @description : This method returns all the Course information.
	 * @since   2015-10-23
	 * @param : The method takes course code parameters as input
	 * @exception : throws any exception it gets to the Main class.
	 */
	public CourseEntity getCourseInfo(String courseCode){
		CourseEntity allCourse = new CourseEntity();
		String myString = null;
		String returnString = null;
		//String srchCourseCode = courseCode;
	    
			try{
				connection	= connectionDao.connectToDatabse();
				statement = (Statement) connection.createStatement();
				
			String query = "select * from course where CourseCode = '" + courseCode + "'" ;
			ResultSet rs = statement.executeQuery(query);
			int count = 0;
			while (rs.next()) {
				allCourse.setCourseCode(rs.getString("CourseCode"));
				allCourse.setCourseTitle(rs.getString("CourseTitle"));
				allCourse.setDeptID(rs.getString("DeptID"));
				allCourse.setDescription(rs.getString("Description"));
				allCourse.setCourseFee(rs.getInt("CourseFee"));
				allCourse.setQuarter(rs.getString("Quarter"));
				allCourse.setLocation(rs.getString("Location"));
				allCourse.setStartDate(rs.getString("StartDate"));
				allCourse.setEndDate(rs.getString("EndDate"));
				allCourse.setTimings(rs.getString("Timings"));
				allCourse.setUnits(rs.getInt("Units"));
				allCourse.setCapacity(rs.getInt("Capacity"));
				allCourse.setFilledStatus(rs.getInt("FilledStatus"));
				count++;
			}
			if (count == 0) {
				exception.ApplicationException();
				System.out.println("***************************************************************");
				System.out.println("Course doesn't exist in the database!");
				System.out.println("***************************************************************");
			}
			connectionDao.closeConnection();

		}catch (Exception e){
			e.printStackTrace();
		}
			//closeConnection();
			return allCourse; 
					
	}
	/**
	 * @name :  Add Course
	 * @description : This method inserts a new course in the database
	 * @since   2015-10-23
	 * @param : The method takes all the required fields for inserting a new record in course table
	 * @exception : throws any exception it gets to the Main class.
	 */
	public void addCourse(String CourseCode, String CourseTitle, String DeptID, String Description, int CourseFee, String Quarter, String Location, String StartDate, String EndDate, String Timings, int Units, int Capacity, int FilledStatus){
		try{
			connection	= connectionDao.connectToDatabse();
			statement = (Statement) connection.createStatement();
			
			String query = "insert into course values('" + CourseCode + "','"+ CourseTitle +"','"+ DeptID +"','"+ Description+"','"+ CourseFee+"','"+ Quarter+"','"+ Location+"','"+StartDate+"','"+EndDate+"','"+ Timings+"','"+ Units+"','"+ Capacity+"','"+ FilledStatus + "')";
			statement.executeUpdate(query);
			connectionDao.closeConnection();
			}catch (SQLException e){
			 System.out.println(e.getMessage());
			 exception.ApplicationException();
		}
		//closeConnection();
	 }
	/**
	 * @name :  Delete a Course 
	 * @description : This method deletes a course from the course table
	 * @since   2015-10-23
	 * @param : The method course code as input and deletes the course from the table
	 * @exception : throws any exception it gets to the Main class.
	 */
	public void deleteCourse(String CourseCode){
		try{
			connection	= connectionDao.connectToDatabse();
			statement = (Statement) connection.createStatement();
			
			String query = "delete from course where coursecode = '" + CourseCode + "'" ;
			statement.executeUpdate(query);
			connectionDao.closeConnection();
			}catch (SQLException e){
			System.out.println(e.getMessage());
			exception.ApplicationException();
		}
	//closeConnection();
	}
	/**
	 * @name :  Updates Course information in the Course table
	 * @description : This method Updates the course location in the database
	 * @since   2015-10-23
	 * @param : The method course code and location field as params
	 * @exception : throws any exception it gets to the Main class.
	 */
		public void updateCourse(String CourseCode, String Location){
			try{
				connection	= connectionDao.connectToDatabse();
				statement = (Statement) connection.createStatement();
				
				String query = "update course set location ="+ "'" + Location + "'" + "where CourseCode = '"+ CourseCode + "'";
				statement.executeUpdate(query);
				connectionDao.closeConnection();
				}catch (SQLException e){
				System.out.println(e.getMessage());
				exception.ApplicationException();
			}
		
	}
		
}



