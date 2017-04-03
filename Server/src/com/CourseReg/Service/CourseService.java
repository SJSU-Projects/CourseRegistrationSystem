package com.CourseReg.Service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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

import com.CourseReg.DAO.CourseDAO;
import com.CourseReg.Entity.CourseEntity;
import com.CourseReg.Entity.DepartmentEntity;

/**
 * Root resource 
 */
@Path("/courses")
public class CourseService {
	
	CourseDAO courseDAO = new CourseDAO();
	
	/**
    * Method handling HTTP GET requests. The method retrieves all department
    * information.The returned object will be sent
    * to the client as "JSON" media type.
    *
    * @return 
    * Response = 200 if Etag doesn't match. String that will be returned as a JSON response.
    * Response = 304 if Etag matches. No contents would be returned.
    */
	@GET
	@Path("/dept")
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewAllDepartment(@Context Request request){
		//return ((CourseDAO) courseDAO).viewAllDepartment();
		List<DepartmentEntity> deptList = ((CourseDAO) courseDAO).viewAllDepartment();
		
		/** Set maximum cache valid time - setMaxAge = 500
		 * Make sure only client is able to cache the URI - setPrivate = true
		 * No need to store cacheable response on disk from browser - setNoStore = true
		 */
	    CacheControl cc = new CacheControl();
		cc.setMaxAge(500);
		cc.setPrivate(true);
		cc.setNoStore(true);
		
		/**
		 * Conditional access using Etag to maintain concurrency
		 */
		EntityTag etag = new EntityTag(Integer.toString(deptList.hashCode()));
	    ResponseBuilder builder = request.evaluatePreconditions(etag);
		
	    if(builder != null){
	        builder.cacheControl(cc);
		    return builder.build();
	    }
	   
		builder = Response.ok(deptList.toArray(new CourseEntity[deptList.size()]), MediaType.APPLICATION_JSON);
		builder.cacheControl(cc);
		builder.tag(etag);
		return builder.build();
		
	}
		
	
	/**
	 * Method handling HTTP GET requests. The method retrieves all course
	 * information grouped by department.The returned object will be sent
	 * to the client as "JSON" media type.
	 * @return 
     * Response = 200 if Etag doesn't match. String that will be returned as a JSON response.
     * Response = 304 if Etag matches. No contents would be returned.
	 */
	@GET
	@Path("/srcdept")
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewCoursesByDept(@Context Request request){
		
			
				List<CourseEntity> courseList = ((CourseDAO) courseDAO).viewCoursesByDept();
				System.out.println("Preethi");
				/** Set maximum cache valid time - setMaxAge = 500
				 * Make sure only client is able to cache the URI - setPrivate = true
				 * No need to store cacheable response on disk from browser - setNoStore = true
				 */
			    CacheControl cc = new CacheControl();
				cc.setMaxAge(500);
				cc.setPrivate(true);
				cc.setNoStore(true);
				
				/**
				 * Conditional access using Etag to maintain concurrency
				 */
				EntityTag etag = new EntityTag(Integer.toString(courseList.hashCode()));
			    ResponseBuilder builder = request.evaluatePreconditions(etag);
				
			    if(builder != null){
			        builder.cacheControl(cc);
				    return builder.build();
			    }

				builder = Response.ok(courseList.toArray(new CourseEntity[courseList.size()]), MediaType.APPLICATION_JSON);
				builder.cacheControl(cc);
				builder.tag(etag);
				return builder.build();
				
	
	 }
	
	/**
	 * Method handling HTTP GET requests. The method retrieves all course
	 * information by Location where the course is conducted.
	 * The returned object will be sent to the client as "JSON" media type.
	 *
	 * @return 
     * Response = 200 if Etag doesn't match. String that will be returned as a JSON response.
     * Response = 304 if Etag matches. No contents would be returned.
	 */
	@GET
	@Path("/location/{Location}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchCourseLocation(@PathParam("Location") String Location,@Context Request request){
				
		List<CourseEntity> courseList = ((CourseDAO) courseDAO).searchCourseLocation(Location);
				
		/** Set maximum cache valid time - setMaxAge = 500
		 * Make sure only client is able to cache the URI - setPrivate = true
		 * No need to store cacheable response on disk from browser - setNoStore = true
		 */
	    CacheControl cc = new CacheControl();
		cc.setMaxAge(5);
		cc.setPrivate(true);
		cc.setNoStore(true);
		
		/**
		 * Conditional access using Etag to maintain concurrency
		 */
		EntityTag etag = new EntityTag(Integer.toString(courseList.hashCode()));
	    ResponseBuilder builder = request.evaluatePreconditions(etag);
		
	    if(builder != null){
	        builder.cacheControl(cc);
		    return builder.build();
	    }

		builder = Response.ok(courseList.toArray(new CourseEntity[courseList.size()]), MediaType.APPLICATION_JSON);
		builder.cacheControl(cc);
		builder.tag(etag);
		return builder.build();
	 }
	
	/**
	 * Method handling HTTP GET requests. The method retrieves all course
	 * information by Location where the course is conducted.
	 * The returned object will be sent to the client as "JSON" media type.
	 *
	 * @return 
     * Response = 200 if Etag doesn't match. String that will be returned as a JSON response.
     * Response = 304 if Etag matches. No contents would be returned.
	 */
	@GET
	@Path("/fee/{CourseFee}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchCourseFee(@PathParam("CourseFee") int CourseFee, @Context Request request){
				
		List<CourseEntity> courseList = ((CourseDAO) courseDAO).searchCourseFee(CourseFee);
		
		/** Set maximum cache valid time - setMaxAge = 500
		 * Make sure only client is able to cache the URI - setPrivate = true
		 * No need to store cacheable response on disk from browser - setNoStore = true
		 */
	    CacheControl cc = new CacheControl();
		cc.setMaxAge(500);
		cc.setPrivate(true);
		cc.setNoStore(true);
		
		/**
		 * Conditional access using Etag to maintain concurrency
		 */
		EntityTag etag = new EntityTag(Integer.toString(courseList.hashCode()));
	    ResponseBuilder builder = request.evaluatePreconditions(etag);
		
	    if(builder != null){
	        builder.cacheControl(cc);
		    return builder.build();
	    }

		builder = Response.ok(courseList.toArray(new CourseEntity[courseList.size()]), MediaType.APPLICATION_JSON);
		builder.cacheControl(cc);
		builder.tag(etag);
		return builder.build();
		
	 }
	
	/**
	 * Method handling HTTP POST requests. The method inserts new course information
	 * information into the Course Table.
	 * The returned object will be sent to the client as "JSON" media type.
	 * @param : The HTTP method takes all the required course details as parameters in JSON format
	 * @return String that will be returned as a JSON response.
	 */
	
	@POST
	@Path("/addcourse")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCourses(String CourseJson) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		CourseEntity courseInsert = mapper.readValue(CourseJson, CourseEntity.class);
		courseDAO = new CourseDAO(); 
		System.out.println(courseInsert.getCourseCode());
	    courseDAO.addCourse(courseInsert.getCourseCode(),courseInsert.getCourseTitle(),courseInsert.getDeptID(),courseInsert.getDescription(), courseInsert.getCourseFee(),courseInsert.getQuarter(),courseInsert.getLocation(),courseInsert.getStartDate(),courseInsert.getEndDate(),courseInsert.getTimings(),courseInsert.getUnits(),courseInsert.getCapacity(),courseInsert.getFilledStatus());
	    return Response.status(200).entity(CourseJson).build();
	}
	
	/**
	 * Method handling HTTP DELETE requests. The method deletes course information
	 * information from the Course Table.delet
	 * The returned object will be sent to the client as "JSON" media type.
	 * @param : The HTTP method takes course code as parameters in JSON format
	 * @return String that will be returned as a JSON response.
	 */
	@DELETE
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCourses(String CourseJson) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		CourseEntity coursedelete = mapper.readValue(CourseJson, CourseEntity.class);
		CourseDAO courseDAO = new CourseDAO(); 
		System.out.println(coursedelete.getCourseCode());
	    courseDAO.deleteCourse(coursedelete.getCourseCode());
	    return Response.status(200).entity(CourseJson).build();
	}
	
	/**
	 * Method handling HTTP PUT requests. The method Updates course location 
	 * information from the Course course code.
	 * The returned object will be sent to the client as "JSON" media type.
	 * @param : The HTTP method takes course code and location to be updated as parameters in JSON format
	 * @return String that will be returned as a JSON response.
	 */
	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCourse(String CourseJson) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		CourseEntity courseUpd = mapper.readValue(CourseJson, CourseEntity.class);
		CourseDAO courseDAO = new CourseDAO(); 
		System.out.println(courseUpd.getCourseCode());
	    courseDAO.updateCourse(courseUpd.getCourseCode(),courseUpd.getLocation());
	    return Response.status(200).entity(CourseJson).build();
	}
	
	/**
	 * Method handling HTTP GET requests. The method retrieves all course
	 * information.The returned object will be sent
	 * to the client as "JSON" media type.
	 *
	 * @return String that will be returned as a JSON response.
	 */
	@GET
	@Path("/allcourses")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CourseEntity> viewAllCourses(@QueryParam("quarter") String Quarter,
										@QueryParam("start") int start,
										@QueryParam("size") int size){
		if(Quarter != null){
			return ((CourseDAO) courseDAO).viewAllCoursesQuarter(Quarter);
		}
		if (start > 0 && size > 0){
			return ((CourseDAO) courseDAO).viewAllCoursesPage(start,size);
		}
		return ((CourseDAO) courseDAO).viewAllCourses();
	 }
	
	/**
	 * Method handling HTTP GET requests. The method retrieves all course
	 * information by Course Code.
	 * The returned object will be sent to the client as "JSON" media type.
	 *
	 * @return 
     * Response = 200 if Etag doesn't match. String that will be returned as a JSON response.
     * Response = 304 if Etag matches. No contents would be returned.
     */
	@GET
	@Path("/{courseCode}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response test(@PathParam("courseCode") String courseCode, @Context Request request){
		CourseEntity course = ((CourseDAO) courseDAO).getCourseInfo(courseCode);
		
		/** Set maximum cache valid time - setMaxAge = 500
		 * Make sure only client is able to cache the URI - setPrivate = true
		 * No need to store cacheable response on disk from browser - setNoStore = true
		 */
	    CacheControl cc = new CacheControl();
		cc.setMaxAge(500);
		cc.setPrivate(true);
		cc.setNoStore(true);
		
		/**
		 * Conditional access using Etag to maintain concurrency
		 */
		EntityTag etag = new EntityTag(Integer.toString(course.hashCode()));
	    ResponseBuilder builder = request.evaluatePreconditions(etag);
		
	    if(builder != null){
	        builder.cacheControl(cc);
		    return builder.build();
	    }

		builder = Response.ok(course, MediaType.APPLICATION_JSON);
		builder.cacheControl(cc);
		builder.tag(etag);
		return builder.build();
	}
}

	




