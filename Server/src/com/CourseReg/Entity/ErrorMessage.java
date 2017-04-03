package com.CourseReg.Entity;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * @author Group 4
 * @version 1.0
 * @name : Error Message
 * @description : This class defines all the attributes used by the class Department
 * @since 2014-09-27
 */
@XmlRootElement
public class ErrorMessage {
	
	public ErrorMessage(){		
	}
	
	public ErrorMessage(String errorMessage, int errorCode, String documentation) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.documentation = documentation;
	}
	private String errorMessage;
	private int errorCode;
	private String documentation;
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	/**
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}
	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	/**
	 * @return the documentation
	 */
	public String getDocumentation() {
		return documentation;
	}
	/**
	 * @param documentation the documentation to set
	 */
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}

}
