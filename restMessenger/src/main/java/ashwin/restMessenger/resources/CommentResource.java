package ashwin.restMessenger.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/")
public class CommentResource {
	
	@GET
	public String Test(){
		return "test";
	}
	
	@GET
	@Path("/{commentId}")
	public String getCommentId(@PathParam("commentId") String cid){
		return "Comment Id is "+cid;
	}

}
