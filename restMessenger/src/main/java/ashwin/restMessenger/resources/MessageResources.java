package ashwin.restMessenger.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;




import ashwin.restMessenger.model.Message;
import ashwin.restMessenger.resources.beans.MessageFilterbean;
import ashwin.restMessenger.service.MessageService;


@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(value={MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
public class MessageResources {
	
	MessageService ms = new MessageService();

	@GET
	public List<Message> getMessages(@BeanParam MessageFilterbean filterBean){
		if(filterBean.getYear()>0){
			return ms.getAllMessagesForYear(filterBean.getYear());
		}
		if(filterBean.getStart()>=0 && filterBean.getSize()>0){
			return ms.getAllMessagesStratSize(filterBean.getStart(), filterBean.getSize());
		}
		else
			return ms.getAllMessages();
	}
	
	@POST
	public Response addMessage(Message message,@Context UriInfo uriInfo) throws URISyntaxException{
		Message msg = ms.addMessage(message);
		return Response.created(new URI(uriInfo.getAbsolutePath()+"/"+ msg.getId())).entity(msg).build();
		//return ms.addMessage(message);
		
	}
	
	@PUT
	@Path("/{messageId}")
	public List<Message> updateMessage(@PathParam("messageId") long messageId,Message message){
		message.setId(messageId);
		ms.updateMessage(message);
		return ms.getAllMessages();
	}
	
	@DELETE
	@Path("/{messageId}")
	public List<Message> deleteMessage(@PathParam("messageId") long messageId){
		ms.removeMessage(messageId);
		return ms.getAllMessages();
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long messageId,@Context UriInfo uriInfo){
		Message message= ms.getMessage(messageId);
		message.addlink(getUriForSelf(uriInfo, message),"self");
		message.addlink(getUriForProfile(uriInfo, message),"profile");
		message.addlink(getUriForComment(uriInfo,message), "comment");
		return message;
	}

	private String getUriForComment(UriInfo uriInfo, Message message) {
		URI uri = uriInfo.getBaseUriBuilder().path(MessageResources.class).path(MessageResources.class, "getCommentResource").resolveTemplate("messageId", message.getId()).build();
		return uri.toString();
	}

	private String getUriForProfile(UriInfo uriInfo, Message message) {
		URI uri = uriInfo.getBaseUriBuilder().path(ProfileResource.class).path(message.getAuthor()).build();
		return uri.toString();
	}

	private String getUriForSelf(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder().path(MessageResources.class).path(Long.toString(message.getId())).toString();
		return uri;
	}
	
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource(){
		return new CommentResource();
	}
	
}
