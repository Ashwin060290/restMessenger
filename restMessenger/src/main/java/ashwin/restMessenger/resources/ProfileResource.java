package ashwin.restMessenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;






import ashwin.restMessenger.model.Profile;
import ashwin.restMessenger.service.ProfileService;


@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {
	
	private ProfileService ps = new ProfileService();
	
	@GET
	public List<Profile> getProfiles(){
		return ps.getAllProfiles();
	}

	@POST
	public Profile addProfile(Profile profile){
		return ps.addProfile(profile);
	}
	
	@PUT
	@Path("/{profileName}")
	public List<Profile> updateProfile(@PathParam("profileName") String profileName,Profile profile){
		profile.setProfileName(profileName);
		ps.updateProfile(profile);
		return ps.getAllProfiles();
	}
	

	@DELETE
	@Path("/{profileName}")
	public List<Profile> deleteProfile(@PathParam("profileName") String profileName){
		ps.removeProfile(profileName);
		return ps.getAllProfiles();
	}
	
	@GET
	@Path("/{profileName}")
	public Profile getProfile(@PathParam("profileName") String profileName){
		return ps.getProfile(profileName);
	}
}
