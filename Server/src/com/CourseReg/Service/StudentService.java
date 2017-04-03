package com.CourseReg.Service;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.print.attribute.DateTimeSyntax;
import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mysql.fabric.xmlrpc.base.Data;

import net.sf.json.JSONArray;

import com.CourseReg.Entity.*;
import com.CourseReg.DAO.*;

/**
 * Welcome to Course Registration!!
 * Root resource
 * @author  Group 4
 * @version 1.0
 * @name :  StudentClient
 * @description : This Student Data Access(DAO)class allows the student service class to access student data from the mysql database.
 * @since   2015-10-27
 **/
@Path("/students")
public class StudentService {

	StudentDAO studentDAO = new StudentDAO();


	/**
    * Method handling HTTP GET requests. The method retrieves all students'
    * information.The returned object will be sent
    * to the client as "JSON" media type.
    *
    * @return String that will be returned as a JSON response.
    */
	
	@GET
	@Path("/allstudents")
	@Produces(MediaType.APPLICATION_JSON)
	public Response displayallstudents(@Context Request request){
		List<StudentEntity> s =  ((StudentDAO) studentDAO).ViewAllStudents();
		 /* Set maximum cache valid time - setMaxAge = 500
			 * Make sure only client is able to cache the URI - setPrivate = true
			 * No need to store cacheable response on disk from browser - setNoStore = true
			 */
		    CacheControl cc = new CacheControl();
			cc.setMaxAge(1000);
			cc.setPrivate(false);
			cc.setNoStore(true);
			//request.
			/*
			 * Conditional access using Etag to maintain concurrency
			 */
			EntityTag etag = new EntityTag(Integer.toString(s.hashCode()));
			//EntityTag etag = new EntityTag(Integer.toString(1234));
						
			//java.util.Date date = new java.util.Date();
		    ResponseBuilder builder = request.evaluatePreconditions(etag);
			 if(builder != null){
			        builder.cacheControl(cc);
			       // builder.lastModified(date);
				    return builder.build();
			    }

			   // builder.lastModified(date);
				builder = Response.ok(s.toArray(new StudentEntity[s.size()]), MediaType.APPLICATION_JSON);
				builder.cacheControl(cc);
				builder.tag(etag);
				return builder.build();
		    		    			
}

	/**
    * Method handling HTTP GET requests. The method retrieves all student 
    * information of a given studentid.The returned object will be sent
    * to the client as "JSON" media type.
    *
    * @return String that will be returned as a JSON response.
	 * @throws URISyntaxException 
    */
		
	  @GET
	  @Path("/getstudent/{StudentID}")
	  @Produces(value={MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.TEXT_XML})
	  public Response getStudent(@PathParam("StudentID") int StudentID, @Context Request request, @Context UriInfo uri) throws URISyntaxException{
		StudentEntity s = studentDAO.ViewStudent(StudentID);
		
		
	    
		/* Set maximum cache valid time - setMaxAge = 500
		 * Make sure only client is able to cache the URI - setPrivate = true
		 * No need to store cacheable response on disk from browser - setNoStore = true
		 */
	    CacheControl cc = new CacheControl();
		cc.setMaxAge(500);
		cc.setPrivate(true);
		cc.setNoStore(true);
		
		/*
		 * Conditional access using Etag to maintain concurrency
		 */
		EntityTag etag = new EntityTag(Integer.toString(s.hashCode()));
	    ResponseBuilder builder = request.evaluatePreconditions(etag);
	    
	     String rsURI = uri.getBaseUriBuilder()
	    .path(StudentService.class)
	    .path("getstudent/" + Integer.toString(StudentID)).build().toString();
	     
	     s.setLink(rsURI);
	     s.setRel("self");
	  	 	
	    if(builder != null){
	        builder.cacheControl(cc);
		    return builder.build();
	    }

		builder = Response.ok(s, MediaType.APPLICATION_JSON);
		builder.cacheControl(cc);
		builder.tag(etag);
		return builder.build();
	   }

	
	/**
	 * Method handling HTTP PUT requests. The method Updates student profile 
	 * information based on the Studentid.
	 * The returned object will be sent to the client as "JSON" media type.
	 * @param : The HTTP method takes course code and location to be updated as parameters in JSON format
	 * @return String that will be returned as a JSON response.
	 */
	
	@PUT
	@Path("/studentupdate")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateStudent(String StudentJson, @Context Request request) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		StudentEntity studentUpd = mapper.readValue(StudentJson, StudentEntity.class);
		StudentDAO studentDAO = new StudentDAO(); 
		
		 /* Set maximum cache valid time - setMaxAge = 500
			 * Make sure only client is able to cache the URI - setPrivate = true
			 * No need to store cacheable response on disk from browser - setNoStore = true
			 */
		    CacheControl cc = new CacheControl();
			cc.setMaxAge(500);
			cc.setPrivate(true);
			cc.setNoStore(true);
			
			/*
			 * Conditional access using Etag to maintain concurrency
			 */
			EntityTag etag = new EntityTag(Integer.toString(studentUpd.hashCode()));
		    ResponseBuilder builder = request.evaluatePreconditions(etag);
		    
		    			
		    if(builder != null){
		        builder.cacheControl(cc);
			    return builder.build();
		    }

		    studentDAO.updateStudent(studentUpd);
			builder = Response.ok(studentUpd, MediaType.APPLICATION_JSON);
			builder.cacheControl(cc);
			builder.tag(etag);
			return builder.build();
	
	}
	
	/**
	 * Method handling HTTP DELETE requests. The method deletes student information
	 * information from the database
	 * The returned object will be sent to the client as "JSON" media type.
	 * @param : The HTTP method takes studentid as parameters 
	 * @return String that will be returned as a JSON response.
	 */
	
	@DELETE
	@Path("/studentdelete/{StudentID}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteStudent(@PathParam("StudentID") int StudentID) throws Exception{
		StudentDAO studentDAO = new StudentDAO(); 
	    studentDAO.deleteStudent(StudentID);
	    //return Response.status(200).entity(StudentJson).build();
	}

	/**
	 * Method handling HTTP POST requests. The method inserts new student 
	 * information into the Login and Student Tables.
	 * The returned object will be sent to the client as "JSON" media type.
	 * @param : The HTTP method takes all the required student details as parameters in JSON format
	 * @return String that will be returned as a JSON response.
	 */
	
	@POST
	@Path("/registerstudent")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	//public Response registerStudent(String StudentJson,String LoginJson) throws Exception{
	public Response registerStudent(String StudentJson) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		StudentEntity studentIns = mapper.readValue(StudentJson,StudentEntity.class);
		//Login loginIns = mapper.readValue(LoginJson,Login.class);
		StudentDAO studentDAO = new StudentDAO();
		//studentService.insertStudent(studentIns,loginIns);
		studentDAO.insertStudent(studentIns);
		return Response.status(200).entity(StudentJson).build();
		//return Response.status(200).entity(LoginJson).build();
	}
	
	/**
	 * Method handling HTTP POST requests. The method inserts student information
	 * into the enrollment Table.
	 * The returned object will be sent to the client as "JSON" media type.
	 * @param : The HTTP method takes all the required course details as parameters in JSON format
	 * @return String that will be returned as a JSON response.
	 */
	
	@POST
	@Path("/enrollstudent/{StudentID}/{CourseCode}")
	@Produces(MediaType.TEXT_PLAIN)
	public String enrollStudent(@PathParam("StudentID") int StudentID,@PathParam("CourseCode") String CourseCode) throws Exception{
		StudentDAO studentDAO = new StudentDAO();
		studentDAO.enrollStudent(StudentID,CourseCode);
		String status="Congratulations !!! You have successfully enrolled";
		return status;
	}
	
	/**
	 * Method handling HTTP POST requests. The method inserts batch of new students information
	 * information into the student Table.
	 * The returned object will be sent to the client as "JSON" media type.
	 * @param : The HTTP method takes all the required student details as parameters in JSON format
	 * @return String that will be returned as a JSON response.
	 */
	
	@POST
	@Path("/addBatch")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addStudents(String StudentJson, @Context Request request) throws Exception{
 		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		LoginEntity[] loginInsert = mapper.readValue(StudentJson, LoginEntity[].class);
		StudentEntity[] studentInsert = mapper.readValue(StudentJson, StudentEntity[].class);
			
		/* Set maximum cache valid time - setMaxAge = 500
		 * Make sure only client is able to cache the URI - setPrivate = true
		 * No need to store cacheable response on disk from browser - setNoStore = true
		 */
	    CacheControl cc = new CacheControl();
		cc.setMaxAge(500);
		cc.setPrivate(true);
		cc.setNoStore(true);
		
		/*
		 * Conditional access using Etag to maintain concurrency
		 */
		EntityTag etag = new EntityTag(Integer.toString(studentInsert.hashCode()));
	    ResponseBuilder builder = request.evaluatePreconditions(etag);
	    
	    			
	    if(builder != null){
	        builder.cacheControl(cc);
		    return builder.build();
	    }

	    studentDAO.addStudent(loginInsert, studentInsert);
		builder = Response.ok(studentInsert, MediaType.APPLICATION_JSON);
		builder.cacheControl(cc);
		builder.tag(etag);
		return builder.build();
	}
	
	
	/**
	 * Method handling HTTP DELETE requests. The method deletes multiple studnet's(batch delete) information
	 * from the student Table. 
	 * The returned object will be sent to the client as "JSON" media type.
	 * @param : The HTTP method takes course code as parameters in JSON format
	 * @return String that will be returned as a JSON response.
	 */
	
	  @DELETE
	  @Path("/deleteBatch/{StudentIDs}")
	  @Produces(MediaType.TEXT_PLAIN)		
	  public String deleteStudent(@PathParam("StudentIDs") String studentIDList) throws Exception{
		  
	  String[] idList = studentIDList.split(",");
	 
	  studentDAO.deleteStudent(idList);
	  
	  String status="Student deletion is successful!!!";
	  return status;
	  	 
	}

	  @DELETE
	  @Path("/deleteEnrollment/{StudentID}/{courseCode}")
	  @Produces(MediaType.TEXT_PLAIN)		
	  public String deleteStudent(@PathParam("StudentID") String studentID,@PathParam("courseCode") String courseCode) throws Exception{
	  
	  studentDAO.deleteEnrollment(studentID,courseCode);
	  	  	  
	  String status="Enrollment deletion is successful!!!";
	  return status;
	  	 
	}
	  
	  
}