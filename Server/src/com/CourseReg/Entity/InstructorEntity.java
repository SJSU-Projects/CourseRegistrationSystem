package com.CourseReg.Entity;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Group 4
 * @version 1.0
 * @name : department entity
 * @description : This class defines all the attributes used by the class department
 * @since 2014-09-27
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class InstructorEntity {

	private String InstructorID;
	private String LastName;
	private String FirstName;
	private String Gender;
	private String Qualification;
	private String HireDate;
	private String Salary; 
	private String ContactNo;
	private String Address;
	private  String CourseCode;
	private  String Quarter;
	private  String Location;
	private  String StartDate;
	private  String EndDate;
	private  String Timings;
	private String emailID;
	private String sachet;
	
	public String getSachet() {
		return sachet;
	}

	public void setSachet(String sachet) {
		this.sachet = sachet;
	}

	public String getCourseCode() {
		return CourseCode;
	}

	public void setCourseCode(String courseCode) {
		CourseCode = courseCode;
	}

	public String getQuarter() {
		return Quarter;
	}

	public void setQuarter(String quarter) {
		Quarter = quarter;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public String getStartDate() {
		return StartDate;
	}

	public void setStartDate(String startDate) {
		StartDate = startDate;
	}

	public String getEndDate() {
		return EndDate;
	}

	public void setEndDate(String endDate) {
		EndDate = endDate;
	}

	public String getTimings() {
		return Timings;
	}

	public void setTimings(String timings) {
		Timings = timings;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	private CourseEntity courseEntity;
	private LoginEntity login;
	
	public CourseEntity getCourse() {
		return courseEntity;
	}

	public void setCourse(CourseEntity courseEntity) {
		this.courseEntity = courseEntity;
	}

	public LoginEntity getLogin() {
		return login;
	}

	public void setLogin(LoginEntity login) {
		this.login = login;
	}

	public InstructorEntity(){
		
	}

	public String getInstructorID() {
		return InstructorID;
	}

	public void setInstructorID(String instructorID) {
		InstructorID = instructorID;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getQualification() {
		return Qualification;
	}

	public void setQualification(String qualification) {
		Qualification = qualification;
	}

	public String getHireDate() {
		return HireDate;
	}

	public void setHireDate(String hireDate) {
		HireDate = hireDate;
	}

	public String getSalary() {
		return Salary;
	}

	public void setSalary(String salary) {
		Salary = salary;
	}

	public String getContactNo() {
		return ContactNo;
	}

	public void setContactNo(String contactNo) {
		ContactNo = contactNo;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

}

