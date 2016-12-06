
package GUI;

import javax.swing.*;
import java.awt.Dimension;
import User.User;
import yelpAPI.YelpAPI;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.border.LineBorder;
import org.json.simple.JSONObject;
import JSONParse.JSONParse;
/*
public class SearchPage extends JFrame{
	private JTextField term;
	private JTextField location;
	private JTextArea historyText;
	public SearchPage(User user) {	
		setTitle("Search");
		setBounds(new Rectangle(450, 150, 0, 0));
		setResizable(false);
		setSize(new Dimension(600, 500));
		setTitle("Search");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		getContentPane().setLayout(null);
		getContentPane().setBackground(UIManager.getColor("Button.highlight"));
		
		
		JButton searchButton = new JButton("Find Now!");
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String item = term.getText();
				String loc = location.getText();
				
				System.out.println(item);
				System.out.println(loc);
				ArrayList<String> reStrings = YelpAPI.search(item, loc, 3);
				ArrayList<JSONObject> value = new ArrayList<>();
			
				for (String string : reStrings){
					JSONObject jsonObject = JSONParse.getJSON(string);
					
					JSONObject jsonObject1 = new JSONObject();
					jsonObject1.put("name", jsonObject.get("name"));
					jsonObject1.put("rating", jsonObject.get("rating"));
					jsonObject1.put("review_count", jsonObject.get("review_count"));
					jsonObject1.put("display_phone", jsonObject.get("display_phone"));
					jsonObject1.put("id", jsonObject.get("id"));
					jsonObject1.put("categories", jsonObject.get("categories"));
					jsonObject1.put("location", jsonObject.get("location"));	
					value.add(jsonObject1);
				}
				
				
				
				user.writeSearchHistory(item + " " + loc, value);

			}
		});
		
		
		searchButton.setFont(new Font("Hiragino Kaku Gothic StdN", Font.PLAIN, 32));
		searchButton.setBounds(290, 130, 310, 60);
		getContentPane().add(searchButton);
		
		term = new JTextField();
		term.setFont(new Font("Arial", Font.PLAIN, 20));
		term.setBounds(380, 5, 220, 50);
		getContentPane().add(term);

		
		location = new JTextField();
		location.setFont(new Font("Arial", Font.PLAIN, 20));
		location.setBounds(380, 70, 220, 50);
		getContentPane().add(location);
		
		JLabel lbFind = new JLabel("Find");
		lbFind.setFont(new Font("Hiragino Kaku Gothic Std", Font.PLAIN, 24));
		lbFind.setHorizontalAlignment(SwingConstants.CENTER);
		lbFind.setPreferredSize(new Dimension(30, 30));
		lbFind.setBounds(290, 5, 85, 50);
		getContentPane().add(lbFind);
		
		JLabel lbNear = new JLabel("Near");
		lbNear.setPreferredSize(new Dimension(30, 30));
		lbNear.setHorizontalAlignment(SwingConstants.CENTER);
		lbNear.setFont(new Font("Hiragino Kaku Gothic Std", Font.PLAIN, 24));
		lbNear.setBounds(290, 70, 85, 50);
		getContentPane().add(lbNear);
		
		JScrollPane historyPane = new JScrollPane();
		historyPane.setBorder(new LineBorder(SystemColor.windowBorder));
		historyPane.setBackground(SystemColor.window);
		historyPane.setBounds(6, 245, 142, 227);
		getContentPane().add(historyPane);
		
		historyText = new JTextArea();
		historyText.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 18));
		historyText.setLineWrap(true);
		historyText.setWrapStyleWord(true);
		
		String history = "";
		
		if (user.getSearchHistory() == null){
			history = "No Search Text.";
		} else {
			for (String string : user.getSearchHistory().keySet()){
				history += string + "\n";
				for (JSONObject jsonObject : user.getSearchHistory().get(string)){
					history += jsonObject.get("name") + "\n" + jsonObject.get("display_phone") + "\n";
				}
			}
		}
		
		historyText.setText(history);
		historyPane.setViewportView(historyText);
		
		JLabel sh = new JLabel("Search History");
		sh.setFont(new Font("Apple SD Gothic Neo", Font.BOLD, 20));
		sh.setHorizontalAlignment(SwingConstants.LEFT);
		sh.setBounds(6, 196, 142, 42);
		getContentPane().add(sh);
		
		JLabel sr = new JLabel("Search Result");
		sr.setHorizontalAlignment(SwingConstants.CENTER);
		sr.setFont(new Font("Apple SD Gothic Neo", Font.BOLD, 20));
		sr.setBounds(160, 196, 434, 42);
		getContentPane().add(sr);
		
		JLabel currUser = new JLabel("User Login");
		currUser.setHorizontalAlignment(SwingConstants.LEFT);
		currUser.setFont(new Font("Apple SD Gothic Neo", Font.BOLD, 18));
		currUser.setBounds(6, 144, 110, 40);
		getContentPane().add(currUser);
		
		JTextPane loginText = new JTextPane();
		loginText.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 18));
		loginText.setEditable(false);
		loginText.setBounds(108, 144, 170, 40);
		
		loginText.setText(user.getUsername());
		getContentPane().add(loginText);
		
		JLabel title = new JLabel("");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setIcon(new ImageIcon("/Users/Ljw/eclipse/workspace/CIT594_Project/yelp.png"));
		title.setBounds(5, 5, 273, 130);
		getContentPane().add(title);
		
		JScrollPane resultPane = new JScrollPane();
		resultPane.setBorder(new LineBorder(SystemColor.windowBorder));
		resultPane.setBackground(SystemColor.window);
		resultPane.setBounds(160, 245, 434, 227);
		getContentPane().add(resultPane);
		
		JTextArea resultText = new JTextArea();
		resultText.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 18));
		resultText.setLineWrap(true);
		resultText.setWrapStyleWord(true);
		resultPane.setViewportView(resultText);
	}
}
*/

public class SearchPage extends JFrame{
	private JTextField term;
	private JTextField location;
	private JTextArea historyText;
	public SearchPage(User user) {	
		setTitle("Search");
		setResizable(false);
		setSize(new Dimension(1000, 700));
		setTitle("Search");
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		getContentPane().setLayout(null);
		getContentPane().setBackground(UIManager.getColor("Button.highlight"));
		
		term = new JTextField();
		term.setFont(new Font("Arial", Font.PLAIN, 20));
		term.setBounds(380, 5, 200, 50);
		getContentPane().add(term);
		
		location = new JTextField();
		location.setFont(new Font("Arial", Font.PLAIN, 20));
		location.setBounds(675, 5, 200, 50);
		getContentPane().add(location);
		
		JLabel lbFind = new JLabel("Find");
		lbFind.setFont(new Font("Hiragino Kaku Gothic Std", Font.PLAIN, 24));
		lbFind.setHorizontalAlignment(SwingConstants.CENTER);
		lbFind.setPreferredSize(new Dimension(30, 30));
		lbFind.setBounds(290, 5, 85, 50);
		getContentPane().add(lbFind);
		
		JLabel lbNear = new JLabel("Near");
		lbNear.setPreferredSize(new Dimension(30, 30));
		lbNear.setHorizontalAlignment(SwingConstants.CENTER);
		lbNear.setFont(new Font("Hiragino Kaku Gothic Std", Font.PLAIN, 24));
		lbNear.setBounds(592, 8, 85, 50);
		getContentPane().add(lbNear);
		
		JScrollPane historyPane = new JScrollPane();
		historyPane.setBorder(new LineBorder(SystemColor.windowBorder));
		historyPane.setBackground(SystemColor.window);
		historyPane.setBounds(6, 245, 274, 427);
		getContentPane().add(historyPane);
		
		historyText = new JTextArea();
		
		
		String history = "";
		
		if (user.getSearchHistory() == null){
			history = "No Search Text.";
		} else {
			for (String string : user.getSearchHistory().keySet()){
				history += string + "\n\n";
				for (JSONObject jsonObject : user.getSearchHistory().get(string)){
					history += jsonObject.get("name") + "\n" + jsonObject.get("display_phone") + "\n" + ((JSONObject)jsonObject.get("location")).get("display_address") + "\n\n";
				}
				history += "\n";
			}
		}
		
		historyText.setText(history);
		
		historyText.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 18));
		historyText.setLineWrap(true);
		historyText.setWrapStyleWord(true);
		historyPane.setViewportView(historyText);
		
		JLabel sh = new JLabel("Search History");
		sh.setFont(new Font("Apple SD Gothic Neo", Font.BOLD, 20));
		sh.setHorizontalAlignment(SwingConstants.CENTER);
		sh.setBounds(5, 196, 275, 42);
		getContentPane().add(sh);
		
		JLabel sr = new JLabel("Search Result");
		sr.setHorizontalAlignment(SwingConstants.CENTER);
		sr.setFont(new Font("Apple SD Gothic Neo", Font.BOLD, 20));
		sr.setBounds(290, 196, 700, 42);
		getContentPane().add(sr);
		
		JLabel currUser = new JLabel("User Login:");
		currUser.setHorizontalAlignment(SwingConstants.LEFT);
		currUser.setFont(new Font("Apple SD Gothic Neo", Font.BOLD, 18));
		currUser.setBounds(5, 145, 110, 40);
		getContentPane().add(currUser);
		
		JLabel title = new JLabel("");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setIcon(new ImageIcon("yelp.png"));
		title.setBounds(5, 5, 275, 130);
		getContentPane().add(title);
		
		JScrollPane resultPane = new JScrollPane();
		resultPane.setBorder(new LineBorder(SystemColor.windowBorder));
		resultPane.setBackground(SystemColor.window);
		resultPane.setBounds(290, 245, 700, 427);
		getContentPane().add(resultPane);
		
		JTextArea resultText = new JTextArea();
		resultText.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 18));
		resultText.setLineWrap(true);
		resultText.setWrapStyleWord(true);
		resultPane.setViewportView(resultText);
		
		JLabel loginText = new JLabel("");
		
		loginText.setText(user.getUsername());
		
		loginText.setBounds(125, 145, 153, 40);
		getContentPane().add(loginText);
		
		String[] def = {"1", "2", "3", "4", "5"};
		JComboBox<Object> filterLimit = new JComboBox<>(def);
		filterLimit.setSelectedIndex(0);
		filterLimit.setMaximumRowCount(10);
		filterLimit.setBounds(887, 6, 103, 43);
		getContentPane().add(filterLimit);
		
		JButton searchButton = new JButton("Find Now!");
		
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String item = term.getText();
				String loc = location.getText();
				
				System.out.println(item);
				System.out.println(loc);
				ArrayList<String> reStrings = YelpAPI.search(item, loc, Integer.parseInt((String)filterLimit.getSelectedItem()));
				ArrayList<JSONObject> value = new ArrayList<>();
			
				for (String string : reStrings){
					JSONObject jsonObject = JSONParse.getJSON(string);
					
					JSONObject jsonObject1 = new JSONObject();
					jsonObject1.put("name", jsonObject.get("name"));
					jsonObject1.put("rating", jsonObject.get("rating"));
					jsonObject1.put("review_count", jsonObject.get("review_count"));
					jsonObject1.put("display_phone", jsonObject.get("display_phone"));
					jsonObject1.put("id", jsonObject.get("id"));
					jsonObject1.put("categories", jsonObject.get("categories"));
					jsonObject1.put("location", jsonObject.get("location"));	
					value.add(jsonObject1);
				}
				
				String reString = "";
				for (JSONObject jsonObject : value){
					reString += jsonObject.get("name") + " " + jsonObject.get("display_phone") + "\n";
				}
				resultText.setText(reString);
				
				
				user.writeSearchHistory(item + " " + loc, value);

			}
		});
		searchButton.setFont(new Font("Hiragino Kaku Gothic StdN", Font.PLAIN, 32));
		searchButton.setBounds(290, 61, 704, 108);
		getContentPane().add(searchButton);
	}
	
}
