package com.CourseReg.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.sf.json.JSONArray;
import com.CourseReg.Entity.*;
import com.CourseReg.DAO.*;

/**
 * Root resource (exposed at "InstructorService" path)
 */

@Path("/Instructors")
public class InstructorService {
	InstructorDAO InstructorDAO = new InstructorDAO();
	
	/**
     * @description Method handling HTTP GET requests. The returned object will be sent to the client as "JSON" media type.
     * @return String that will be returned as a JSON response.
     */
	
	
	@GET
	@Path("/ViewMyStudents/{InstructorID}")
	@Produces(value={MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.TEXT_XML})
	public Response ViewMyStudents1(@PathParam("InstructorID") String InstructorID, @Context Request request){
		
		//return InstructorDAO.ViewMyStudents(InstructorID);
		
		InstructorEntity i = InstructorDAO.ViewMyStudents(InstructorID);
	    
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
		EntityTag etag = new EntityTag(Integer.toString(i.hashCode()));
	    ResponseBuilder builder = request.evaluatePreconditions(etag);
		
	    if(builder != null){
	        builder.cacheControl(cc);
		    return builder.build();
	    }

		builder = Response.ok(i, MediaType.APPLICATION_JSON);
		builder.cacheControl(cc);
		builder.tag(etag);
		return builder.build();
	}
	
	@GET
	@Path("/ViewMyProfile/{InstructorID}")
	@Produces(value={MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.TEXT_XML})
	public Response ViewMyProfile1(@PathParam("InstructorID") String InstructorID, @Context Request request){
		
		//return InstructorDAO.ViewMyProfile(InstructorID);
		
		InstructorEntity i = InstructorDAO.ViewMyProfile(InstructorID);
	    
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
		EntityTag etag = new EntityTag(Integer.toString(i.hashCode()));
	    ResponseBuilder builder = request.evaluatePreconditions(etag);
		
	    if(builder != null){
	        builder.cacheControl(cc);
		    return builder.build();
	    }

		builder = Response.ok(i, MediaType.APPLICATION_JSON);
		builder.cacheControl(cc);
		builder.tag(etag);
		return builder.build();
		
	}
	
	@GET
	@Path("/ViewMySchedule/{InstructorID}")
	@Produces(value={MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.TEXT_XML})
	public Response ViewMySchedule1(@PathParam("InstructorID") String InstructorID, @Context Request request){
		
		//return InstructorDAO.ViewMySchedule(InstructorID);
		InstructorEntity i = InstructorDAO.ViewMySchedule(InstructorID);
	    
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
		EntityTag etag = new EntityTag(Integer.toString(i.hashCode()));
	    ResponseBuilder builder = request.evaluatePreconditions(etag);
		
	    if(builder != null){
	        builder.cacheControl(cc);
		    return builder.build();
	    }

		builder = Response.ok(i, MediaType.APPLICATION_JSON);
		builder.cacheControl(cc);
		builder.tag(etag);
		return builder.build();
		
	}
	
	@PUT
	@Path("/Instructorupdate")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateInstructor(String InstructorJson) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		InstructorEntity InstructorUpd = mapper.readValue(InstructorJson, InstructorEntity.class);
		InstructorDAO InstructorDAO = new InstructorDAO(); 
		System.out.println(InstructorUpd.getInstructorID());
	    InstructorDAO.updateInstructor(InstructorUpd.getInstructorID(),InstructorUpd.getSalary());
	    return Response.status(200).entity(InstructorJson).build();
	}
	
	@DELETE
	@Path("/deleteInstructor/{InstructorID}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteInstructors(@PathParam("InstructorID") String InstructorID) throws Exception{
	    InstructorDAO.deleteInstructor(InstructorID);
	}
}

