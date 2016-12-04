package User;

public class User {
	static int id = 0;
	private int userId;
	private String username;
	private String psd;
	
	
	public User(String user, String psd) {
		username = user;
		this.psd = psd;
		this.userId = id++;
	}
	
	public void setUsername(String name) {
		this.username = name;
	}
	
	public void setPsd(String psd) {
		this.psd = psd;
	}
	
	public int getId() {
		return this.userId;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.getPassword();
	}
	
	
}
