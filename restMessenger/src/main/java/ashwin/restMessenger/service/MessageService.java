package ashwin.restMessenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ashwin.restMessenger.database.DatabaseClass;

import ashwin.restMessenger.model.ErrorMessage;
import ashwin.restMessenger.model.Message;

public class MessageService {
	
	private Map<Long,Message> messages = DatabaseClass.getMessage();
	
	public MessageService(){
		messages.put(1L, new Message(1,"Hello World","Ashwin"));
		messages.put(2L, new Message(2,"Hello Jersey","Ashwin"));
	}
	
	public List<Message> getAllMessages(){
		return new ArrayList<Message>(messages.values());
	}
	
	public List<Message> getAllMessagesForYear(int year){
		List<Message> msgforyear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for(Message message : messages.values()){
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR)==year){
				msgforyear.add(message);
			}
		}
		return msgforyear;
	}
	
	public List<Message> getAllMessagesStratSize(int start, int size){
		List<Message> list = new ArrayList<Message>(messages.values());
		if((start+size)>list.size())return new ArrayList<Message>();
		return list.subList(start, start+size);
	}
	
	public Message getMessage(long id){
		ErrorMessage em = new ErrorMessage("Not Found", 404, "www.google.com");
		Message message= messages.get(id);
		Response res = Response.status(Status.NOT_FOUND).entity(em).build();
		if(message == null)
			throw new NotFoundException(res);
		else
			return message; 
	}
	
	public Message addMessage(Message message){
		message.setId(messages.size()+1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message){
		if(message.getId()<=0){
			return null;
		}
		else{
			messages.put(message.getId(), message);
			return message;
		}		
	}
	
	public Message removeMessage(long id){
		return messages.remove(id);
	}

}
