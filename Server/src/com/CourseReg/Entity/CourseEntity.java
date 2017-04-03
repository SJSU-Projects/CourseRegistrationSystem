package com.CourseReg.Entity;


import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * @author Group 4
 * @version 1.0
 * @name : course entity
 * @description : This class defines all the attributes used by the class course
 * @since 2014-09-27
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CourseEntity {

	private  String CourseCode;
	private  String CourseTitle;
	private  String DeptID;
	private  String Description;
	private  int CourseFee; 
	private  String Quarter;
	private  String Location;
	private  String StartDate;
	private  String EndDate;
	private  String Timings;
	private  int Units;
	private  int Capacity;
	private  int FilledStatus;
	
	public CourseEntity(){
		
	}
	public String getCourseCode() {
		return CourseCode;
	}
	public void setCourseCode(String courseCode) {
		CourseCode = courseCode;
	}
	public String getCourseTitle() {
		return CourseTitle;
	}
	public void setCourseTitle(String courseTitle) {
		CourseTitle = courseTitle;
	}
	public String getDeptID() {
		return DeptID;
	}
	public void setDeptID(String deptID) {
		DeptID = deptID;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public int getCourseFee() {
		return CourseFee;
	}
	public void setCourseFee(int courseFee) {
		CourseFee = courseFee;
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
	public int getUnits() {
		return Units;
	}
	public void setUnits(int units) {
		Units = units;
	}
	public int getCapacity() {
		return Capacity;
	}
	public void setCapacity(int capacity) {
		Capacity = capacity;
	}
	public int getFilledStatus() {
		return FilledStatus;
	}
	public void setFilledStatus(int filledStatus) {
		FilledStatus = filledStatus;
	}
	
}

