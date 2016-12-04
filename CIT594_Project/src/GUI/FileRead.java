package GUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import User.User;
public class FileRead {
	BufferedReader br;
	private HashMap<String, String> confidential;
	private HashMap<Integer, User> users;
	
	public FileRead(String fileName) {
		confidential = new HashMap<>();
		users = new HashMap<>();
		try {
			File file = new File(fileName);
			br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null){
				String[] info = line.split(" ");
				confidential.put(info[0], info[1]);
				
				
			// to be completed
				User user = new User(info[0], info[1]);
				users.put(user.getId(), user);
			}
			br.close();
		} catch (Exception e) {	
		}
	}
	
	public HashMap<String, String> getConfidential() {
		return confidential;
	}
	
	public HashMap<Integer, User> getUsers() {
		return users;
	}
	
}
