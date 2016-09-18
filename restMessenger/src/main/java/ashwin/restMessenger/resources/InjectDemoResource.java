package ashwin.restMessenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;


@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {
	
	@GET
	@Path("/anotations")
	public String getParamsUsingAnotation(@MatrixParam("param") String param,
										  @HeaderParam("headvar") int headvar,
										  @CookieParam("name")String cookie){
		return "MatrixParam: "+param+"\nHeaderValue: "+headvar+"\nCookie: "+cookie;
	}
	
	

	@GET
	@Path("/context")
	public String getValueUsignContext(@Context UriInfo uriInfo,
										@Context HttpHeaders head){
		String path = uriInfo.getAbsolutePath().toString();
		String cookie=head.getCookies().toString();
		return "Path: "+path+"\nCookie: "+cookie;
	}
}
