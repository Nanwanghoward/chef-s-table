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
import java.awt.Dimension;
import java.awt.Rectangle;

/**
 * HomePage class
 * 
 *
 */
public class HomePage extends JFrame {
	private HashMap<String, String> confidential;	//variable holds all userinfo
	
	private int flag = 0;
	private BufferedImage image1;
	private BufferedImage image2;
	
	JButton loginButton, signupButton, exitButton;
	JTextField textField;
	JPasswordField passwordField;
	JLabel user, psd, background;
	JLabel flash;
	
	/**
	 *HomePage constructor
	 * 
	 * @param name
	 */
	public HomePage(String name) {
		
		super(name);
		setResizable(false);
		setTitle("Home");
		setLocationRelativeTo(null);
		//read user login file
		fileRead();
		
		//set panel background
		try {
			image1 = ImageIO.read(new File("pictures/imag1.jpg"));
			background = new JLabel(new ImageIcon(image1));
			this.setContentPane(background);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//set login and sign up button
		loginButton = new JButton("Login");
		signupButton = new JButton("Signup");
		exitButton = new JButton("Exit");
		
		textField = new JTextField();
		passwordField = new JPasswordField();
		
		//set user name and password textfield 
		user = new JLabel("Username:");
		psd = new JLabel("Password:");
		flash = new JLabel("");
		
		getContentPane().setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addActionListeners();
		
		//set panel layout
		user.setBounds(200, 200, 120, 40);
		psd.setBounds(200, 230, 120, 40);
		
		textField.setBounds(280, 210, 200, 30);
		passwordField.setBounds(280, 240, 200, 30);
	
		loginButton.setBounds(200, 280, 100, 40);
		signupButton.setBounds(300, 280, 100, 40);
		exitButton.setBounds(400, 280, 100, 40);
		
		flash.setBounds(300, 330, 100, 100);
		flash.setSize(500, 100);
		

		//add all components into panel
		getContentPane().add(user);
		getContentPane().add(psd);
		getContentPane().add(textField);
		getContentPane().add(passwordField);
		getContentPane().add(loginButton);
		getContentPane().add(signupButton);
		getContentPane().add(exitButton);
		getContentPane().add(flash);
		

		//set panel layout
		this.setSize(800, 800);
		this.setVisible(true);
	}
	
	private void addActionListeners(){

		//add login button listener
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();		//get user name from textfield
				String psd = String.valueOf(passwordField.getPassword());	//get password from textfield
				if (confidential.containsKey(username) && confidential.get(username).equals(psd)){		//check if user is already signed up
					
					setVisible(false);
					User user = new User(username, psd);	//construct a current user obj
					
					SearchPage searchPage = new SearchPage(user);		//redirect to search page
					
				} else {
					flash.setText("Login failure!");		//prompt that user login failed after check in userinfo
					flash.setVisible(true);
				}
			}
		});
		
		//add signup button listener
		signupButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();		//get user name from textfield
				String psd = String.valueOf(passwordField.getPassword());		//get password from textfield
				if (confidential.containsKey(username)){			//check if user is already signed up
					flash.setText("Username already taken!");		//prompt that user name is taken after check userinfo
					flash.setVisible(true);
				} else {
					ArrayList<String> info = new ArrayList<>();
					info.add(username); info.add(psd);		//add user profile into userinfo
					FileWrite.writeConfidential(info);
					flash.setText("Sign up successfully!");
					flash.setVisible(true);
				}
				fileRead();
			}
		});
		

		//add exitbutton action listener
		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);		//exit the system
			}
		});
	}
	
	public void fileRead() {
		confidential = FileRead.readConfidential("userinfo/confidential.txt");		//read the confidential info from userinfo file
	}
	
	public static void main(String args[]) {
		HomePage homePage = new HomePage("CIT594");			//construct a new homepage
	}
	
}
