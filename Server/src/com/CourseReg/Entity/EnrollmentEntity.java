package com.CourseReg.Entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Group 4
 * @version 1.0
 * @name : Enrollment entity
 * @description : This class defines all the attributes used by the class Enrollment
 * @since 2014-09-27
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EnrollmentEntity {
	
	private int StudentID;
	private String CourseCode;
	private String PaymentDate;
	public int getStudentID() {
		return StudentID;
	}
	public void setStudentID(int studentID) {
		StudentID = studentID;
	}
	public String getCourseCode() {
		return CourseCode;
	}
	public void setCourseCode(String courseCode) {
		CourseCode = courseCode;
	}
	public String getPaymentDate() {
		return PaymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		PaymentDate = paymentDate;
	}
	

}

