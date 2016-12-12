package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import User.User;

public class HomePage extends JFrame {
	private HashMap<String, String> confidential;
	
	// pictures used in this jframe
	private BufferedImage image1;
	
	JButton loginButton, signupButton, exitButton;
	JTextField textField;
	JPasswordField passwordField;
	JLabel user, psd, background;
	JLabel flash;
	
	public HomePage(String name) {
		
		super(name);
		setResizable(false);
		setTitle("Home");
		setLocationRelativeTo(null);
		fileRead();
		
		
		// import background pics
		try {
			image1 = ImageIO.read(new File("pictures/imag1.jpg"));
			background = new JLabel(new ImageIcon(image1));
			this.setContentPane(background);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		// three useful buttons
		loginButton = new JButton("Login");
		signupButton = new JButton("Signup");
		exitButton = new JButton("Exit");
		
		textField = new JTextField();
		passwordField = new JPasswordField();
		
		user = new JLabel("Username:");
		psd = new JLabel("Password:");
		flash = new JLabel("");
		
		getContentPane().setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		// add all listeners to buttons
		addActionListeners();
		
		
		//adjust positon and size
		user.setBounds(200, 200, 120, 40);
		psd.setBounds(200, 230, 120, 40);
		
		textField.setBounds(280, 210, 200, 30);
		passwordField.setBounds(280, 240, 200, 30);
	
		loginButton.setBounds(200, 280, 100, 40);
		signupButton.setBounds(300, 280, 100, 40);
		exitButton.setBounds(400, 280, 100, 40);
		
		flash.setBounds(300, 330, 100, 100);
		flash.setSize(500, 100);
		
		
		// add to panel
		getContentPane().add(user);
		getContentPane().add(psd);
		getContentPane().add(textField);
		getContentPane().add(passwordField);
		getContentPane().add(loginButton);
		getContentPane().add(signupButton);
		getContentPane().add(exitButton);
		getContentPane().add(flash);
		
		this.setSize(800, 800);
		this.setVisible(true);
	}
	
	private void addActionListeners(){

		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();
				String psd = String.valueOf(passwordField.getPassword());
				if (confidential.containsKey(username) && confidential.get(username).equals(psd)){
					
					setVisible(false);
					User user = new User(username, psd);
					
					// login successfully and open searchPage
					SearchPage searchPage = new SearchPage(user);
					
				} else {
					// login failure message
					flash.setText("Login failure!");
					flash.setVisible(true);
				}
			}
		});
		
		signupButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();
				String psd = String.valueOf(passwordField.getPassword());
				if (confidential.containsKey(username)){
					
					// do not allow identical username
					flash.setText("Username already taken!");
					flash.setVisible(true);
				} else {
					ArrayList<String> info = new ArrayList<>();
					info.add(username); info.add(psd);
					FileWrite.writeConfidential(info);
					flash.setText("Sign up successfully!");
					flash.setVisible(true);
				}
				fileRead();
			}
		});
		
		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
	public void fileRead() {
		confidential = FileRead.readConfidential("userinfo/confidential.txt");
	}
	
	public static void main(String args[]) {
		HomePage homePage = new HomePage("CIT594");
	}
	
}

