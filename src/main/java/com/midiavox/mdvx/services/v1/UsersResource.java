package com.midiavox.mdvx.services.v1;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.midiavox.mdvx.BusinessManager;
import com.midiavox.mdvx.services.v1.User;

@Path("/v1/users")
@Api(value = "/users", description = "Manage Users")

public class UsersResource {
	
	private static final Logger log = Logger.getLogger(UsersResource.class.getName());
	
	@GET
	@Path("/{name}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find User",
	notes = "This API retrieves the public information for the user(Private info is returned if this is the auth user)"+
	"<p><u>Input Parameters</u><ul><li><b>userId and password</b> is required</li></ul>")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucess:{ user profile }"),
			@ApiResponse(code = 400, message = "Failed: {\"error\":\"error_description\", \"status\":\"FAIL\"}")
	})
	
	public Response getUserById(@ApiParam(value = "name", required = true, defaultValue = "ola", allowableValues = "", allowMultiple = false)
	@PathParam("name") String name,
	@ApiParam(value = "password", required = true, defaultValue = "senha", allowableValues = "", allowMultiple = false)
	@PathParam("password") String password){
		
		log.info("UsersResource::getUserById started name=" + name);
		log.info("UsersResource::getUserById started password=" + password);
		if (name == null){
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("{\"error\":\"Empty name\", \"status\":\"FAIL\"}")
					.build();
		}
		try {
			User user = BusinessManager.getInstance().loginUser(name, password);
			if(user != null)
				//return "OK";
			return Response.status(Response.Status.OK).entity(user).build();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//return "ERROR";
		return Response.status(Response.Status.BAD_REQUEST)
				.entity("{\"error\":\"Could Not Find User\", \"status\":\"FAIL\"}")
				.build();
	}
	
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find All Users", notes = "This API retrieves all user")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucess: { users : [] }"),
			@ApiResponse(code = 400, message = "Failed: {\"error\":\"error description\", \"status\":\"FAIL\"}")
	})
	public Response getAllUsers(){
		
		try{
			List<User> users = BusinessManager.getInstance().findUsers();
			
			UsersHolder userHolder = new UsersHolder();
			
			userHolder.setUsers(users);
			
			return Response.status(Response.Status.OK).entity(userHolder).build();
		} catch (Exception e){
			
		}
		
		return Response.status(Response.Status.BAD_REQUEST)
				.entity("{\"error\":\"Could Not Find User\", \"status\":\"FAIL\"}")
				.build();
		
	}
	
	@POST
	@Path("/{name}/{password}")
	@Consumes({ MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_JSON})
	@ApiOperation(value = "Create a new User",
	notes = "This API create a new user if the username does not exist" +
	"<p><u>Input Parameters</u><ul><li><b>new user object</b> is required</li></ul>")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Sucess: { user profile }"),
	@ApiResponse(code = 400, message = "Failed: {\"error\":\"error description\", \"status\":\"FAIL\"}")})

	public Response createUser(@ApiParam(value = "name", required = true, defaultValue = "ola", allowableValues = "", allowMultiple = false)
	@PathParam("name") String name,
	@ApiParam(value = "password", required = true, defaultValue = "senha", allowableValues = "", allowMultiple = false)
	@PathParam("password") String password){
		
		try {
			log.info("name "+name+" pass "+password);
			User newUser = BusinessManager.getInstance().addUser(new User(name,password));
			return Response.status(Response.Status.CREATED).entity(newUser).build();
		} catch(Exception e){
			
		}
		
		return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"Could Not Create User\", \"status\":\"FAIL\"}").build();
		
	}
	
	@PUT
	@Path("/{userId}")
	@Consumes({ MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_JSON})
	@ApiOperation(value = "Update User",
	notes = "This API updates the user")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucess: { user profile }"),
	@ApiResponse(code = 400, message = "Failed: {\"error\":\"error description\", \"status\":\"FAIL\"}")})
	
	public Response updateUser(@PathParam("userId") String userId, String jsonString){
		
		String name;
		
		try{
			Object obj = JSONValue.parse(jsonString);
			JSONObject jsonObject = (JSONObject) obj;
			name = (String) jsonObject.get("name");
		} catch(Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"Invalid or Missing fields error\", \"status\":\"FAIL\"}").build();
		}
		
		try{
			User updatedUser = BusinessManager.getInstance().updateUserAttribute(userId, "name", name);
			
			return Response.status(Response.Status.OK).entity(updatedUser).build();
		} catch (Exception e){}
		
		
		return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"Could Not Update User\", \"status\":\"FAIL\"}").build();
	}
	
	@DELETE
	@Path("/{userId}")
	@Consumes({ MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_JSON})
	@ApiOperation(value = "Delete User",
	notes = "This API deletes the user")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucess: {  }"),
	@ApiResponse(code = 400, message = "Failed: {\"error\":\"error description\", \"status\":\"FAIL\"}")})
	
	public Response deleteUser(@PathParam("userId") String userId){
		try{
			BusinessManager.getInstance().deleteUser(userId);
			
			return Response.status(Response.Status.OK).entity("{}").build();
		} catch(Exception e){
			
		}
		
		return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"Could Not Update User\", \"status\":\"FAIL\"}").build();
	}

	
}
