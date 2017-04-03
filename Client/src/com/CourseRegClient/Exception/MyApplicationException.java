package com.CourseRegClient.Exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.CourseRegClient.Entity.ErrorMessage;


public class MyApplicationException {
	
	public void ApplicationException(){
	ErrorMessage errorMessage = new ErrorMessage("Not Found",404,"Course cannot be Inserted");
	Response.status(Status.NOT_FOUND).entity(errorMessage).build();
	throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
 }
}