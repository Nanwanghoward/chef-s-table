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

public class HomePage extends JFrame {
	private HashMap<String, String> confidential;
	
	// pictures used in this jframe
	private int flag = 0;
	private BufferedImage image1;
	private BufferedImage image2;
	//private BufferedImage image3;
	//private BufferedImage image4;
	
	JButton loginButton, signupButton, exitButton;
	JTextField textField;
	JPasswordField passwordField;
	JLabel user, psd, background;
	JLabel flash;
	
	public HomePage(String name) {
		
		super(name);
		
		fileRead();
		
		try {
			image1 = ImageIO.read(new File("imag1.jpg"));
			background = new JLabel(new ImageIcon(image1));
			this.setContentPane(background);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		loginButton = new JButton("Login");
		signupButton = new JButton("Signup");
		exitButton = new JButton("Exit");
		
		textField = new JTextField();
		passwordField = new JPasswordField();
		
		user = new JLabel("Username:");
		psd = new JLabel("Password:");
		flash = new JLabel("");
		
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();
				String psd = String.valueOf(passwordField.getPassword());
				System.out.println(username);
				System.out.println(psd);
				if (confidential.containsKey(username) && confidential.get(username).equals(psd)){
					//System.out.println("success");
					
					setVisible(false);
					SearchPage searchPage = new SearchPage("Find your feast!");
					// TO BE IMPLEMENTED!!!!
					
				} else {
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
					flash.setText("Username already taken!");
					flash.setVisible(true);
				} else {
					ArrayList<String> info = new ArrayList<>();
					info.add(username); info.add(psd);
					FileWrite fileWrite = new FileWrite(info);
					flash.setText("Sign up successfully!");
					flash.setVisible(true);
				}
				fileRead();
				/*
				for (String string : confidential.keySet()){
					System.out.println(string + " " + confidential.get(string));
				}
				*/
			}
		});
		
		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		user.setBounds(200, 200, 120, 40);
		psd.setBounds(200, 230, 120, 40);
		
		textField.setBounds(280, 210, 200, 30);
		passwordField.setBounds(280, 240, 200, 30);
	
		loginButton.setBounds(200, 280, 100, 40);
		signupButton.setBounds(300, 280, 100, 40);
		exitButton.setBounds(400, 280, 100, 40);
		
		flash.setBounds(300, 330, 100, 100);
		flash.setSize(500, 100);
		

		this.add(user);
		this.add(psd);
		this.add(textField);
		this.add(passwordField);
		this.add(loginButton);
		this.add(signupButton);
		this.add(exitButton);
		this.add(flash);
		
		this.setSize(800, 800);
		this.setVisible(true);
	}
	
	public void fileRead() {
		FileRead fileRead = new FileRead("confidential.txt");
		confidential = fileRead.getConfidential();
	}
	
	public static void main(String args[]) {
		HomePage homePage = new HomePage("CIT594");
	}
	
}
