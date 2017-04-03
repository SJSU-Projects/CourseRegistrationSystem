package com.CourseReg.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.CourseReg.DAO.LoginDAO;


/**
 * Root resource (exposed at "LoginService" path)
 */

@Path("/login")
public class LoginService {
	
	
	LoginDAO loginService = new LoginDAO();
	
	/**
     * @description Method handling HTTP GET requests. The returned object will be sent to the client as "text/plain" media type.
     * @return String that will be returned as a text/plain response.
     */
	
	@GET
	@Path("/displayloginmenu")
	@Produces(MediaType.TEXT_PLAIN)
	public String DisplayLoginMenu(){
	String option1 = "1.Allow Max to login when ceredentials are valid.\n";
	
	return option1;
	}
	
	/**
     * @description Method handling HTTP GET requests. It will take the EmailID and Password from the user.
     * @return String that will be returned as a application/json response.
     * @params :  EmailID, Password.
     */
	
	@GET
	@Path("/auth/{EmailID}/{Password}")
	@Produces(MediaType.APPLICATION_JSON)
	public String test1(@PathParam("EmailID") String EmailID, @PathParam("Password") String Password){
		return loginService.existingUserMisMatch(EmailID, Password);
	}
	
	
}	


