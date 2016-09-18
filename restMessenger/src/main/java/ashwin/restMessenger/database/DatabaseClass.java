package ashwin.restMessenger.database;
import ashwin.restMessenger.model.Message;
import ashwin.restMessenger.model.Profile;

import java.util.HashMap;
import java.util.Map;

public class DatabaseClass {
	
	private static Map<Long, Message> messages = new HashMap<>();
	private static Map<String, Profile> profiles = new HashMap<>();

	
	public static Map<Long,Message> getMessage(){
		return messages;
	}
	
	public static Map<String,Profile> getProfile(){
		return profiles;
	}
}
