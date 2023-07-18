package aar;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/users")
public class UserService {
	
	UserDao userDao = new UserDao();
	
	Logger log = Logger.getLogger(UserService.class.getName());
    
    @GET
    @Path("/xml")
    @Produces(MediaType.APPLICATION_XML)
    public List<User> getUsers() {	   
 	   return userDao.getAllUsers();
    }	
    
    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsersJson() {
 	   return userDao.getAllUsers();
    }
    
    @GET
    @Path("/user/{id}/json")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("id") Integer id) {
 	   return userDao.getUser(id);
    }
    
    @POST
    @Path("/")
    @Consumes("application/x-www-form-urlencoded")
    public Response addUser(@FormParam("name") String name, @FormParam("profession") String profession) {
 	   try {
 		   userDao.addUser(name, profession);
 		   log.log(Level.INFO, "Inserted user "+name);
       
 		   return Response.status(200)
 				   .entity("addUser -> name: " + name + ", profession: " + profession)
 				   .build();
       } catch(Exception e) {
     	  return Response.status(400).build();
       }
    }
    
    @DELETE
    @Path("/")
    @Consumes("application/x-www-form-urlencoded")
    public Response removeUser(@FormParam("id") Integer id) {
       try {
     	  boolean deletedOk = userDao.deleteUser(id);
       
     	  if(deletedOk == true) 
     		  log.log(Level.INFO, "deleted user "+id+" correctly ");
       
     	  return Response.status(200)
     			  .entity("Deleted user with id: " + id + " correctly ")
     			  .build();
       } catch(Exception e) {
     	  return Response.status(400).build();
       }
    }
    
    
    
}
