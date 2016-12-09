
package GUI;

import javax.swing.*;

import java.awt.Color;
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
import org.omg.CORBA.INTERNAL;

import JSONParse.JSONParse;

public class SearchPage extends JFrame{
	private User curUser;
	private JTextField term;
	private JTextField location;
	private JTextArea historyText;
	private JButton Gmap, searchButton;
	private ArrayList<JSONObject> value;
	private JComboBox<Object> filterLimit;
	private JTextArea resultText;
	private JLabel sh, currUser, sr, title;
	private ArrayList<JLabel> ratingLabels = new ArrayList<>();
	
	public SearchPage(User user) {	
		curUser = user;
		setTitle("Search");
		setResizable(false);
		setSize(new Dimension(1000, 700));
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
		
		
		
		
		for (int i = 0; i < 10; i++){
			ratingLabels.add(new JLabel(""));
			getContentPane().add(ratingLabels.get(i));
			ratingLabels.get(i).setBounds(860, 7 + i * 23, 200, 500);
		}
		
		
		
		
		
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
		lbNear.setBounds(590, 5, 85, 50);
		getContentPane().add(lbNear);
		
		JScrollPane historyPane = new JScrollPane();
		historyPane.setBorder(new LineBorder(SystemColor.windowBorder));
		historyPane.setBackground(SystemColor.window);
		historyPane.setBounds(5, 245, 275, 425);
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
		
		sh = new JLabel("Search History");
		sh.setFont(new Font("Apple SD Gothic Neo", Font.BOLD, 20));
		sh.setHorizontalAlignment(SwingConstants.CENTER);
		sh.setBounds(5, 195, 275, 40);
		getContentPane().add(sh);
		
		sr = new JLabel("Search Result");
		sr.setHorizontalAlignment(SwingConstants.CENTER);
		sr.setFont(new Font("Apple SD Gothic Neo", Font.BOLD, 20));
		sr.setBounds(290, 195, 550, 40);
		getContentPane().add(sr);
		
		currUser = new JLabel("Current User:");
		currUser.setHorizontalAlignment(SwingConstants.LEFT);
		currUser.setFont(new Font("Apple SD Gothic Neo", Font.BOLD, 18));
		currUser.setBounds(5, 145, 110, 40);
		getContentPane().add(currUser);
		
		title = new JLabel("");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setIcon(new ImageIcon("pictures/ramsey.jpg"));
		title.setBounds(5, 5, 275, 150);
		getContentPane().add(title);
		
		JScrollPane resultPane = new JScrollPane();
		resultPane.setBorder(new LineBorder(SystemColor.windowBorder));
		resultPane.setBackground(SystemColor.window);
		resultPane.setBounds(290, 245, 550, 425);
		getContentPane().add(resultPane);
		
		resultText = new JTextArea();
		resultText.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 18));
		resultText.setLineWrap(true);
		resultText.setWrapStyleWord(true);
		resultPane.setViewportView(resultText);
		
		JLabel loginText = new JLabel("");
		loginText.setHorizontalAlignment(SwingConstants.LEFT);
		loginText.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 18));
		
		loginText.setText(user.getUsername());
		
		loginText.setBounds(125, 145, 150, 40);
		getContentPane().add(loginText);
		
		String[] def = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
		filterLimit = new JComboBox<>(def);
		filterLimit.setSelectedIndex(0);
		filterLimit.setMaximumRowCount(10);
		filterLimit.setBounds(885, 5, 105, 45);
		getContentPane().add(filterLimit);
		
		Gmap = new JButton("Show Map");
		Gmap.setFont(new Font("Hiragino Kaku Gothic StdN", Font.PLAIN, 16));
		Gmap.setBounds(835, 190, 155, 50);
		getContentPane().add(Gmap);
		
		searchButton = new JButton("Find Now!");
		searchButton.setFont(new Font("Hiragino Kaku Gothic StdN", Font.PLAIN, 32));
		searchButton.setBounds(290, 60, 705, 110);
		getContentPane().add(searchButton);
		addActionListers();
		getContentPane().setBackground(new Color(255, 255, 240));

	}
	
	private void addActionListers(){
		
		Gmap.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GoogleMap googleMap = new GoogleMap(curUser, value);
			}
		});
		
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				for (int i = 0; i < ratingLabels.size(); i++){
					ratingLabels.get(i).setVisible(false);
				}
				
				
				String item = term.getText();
				String loc = location.getText();
				
				//System.out.println(item);
				//System.out.println(loc);
				ArrayList<String> reStrings = YelpAPI.search(item, loc, Integer.parseInt((String)filterLimit.getSelectedItem()));
				value = new ArrayList<>();
			
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
				curUser.writeSearchHistory(item + " " + loc, value);
				
				
				int rating;
				for (int i = 0; i < value.size(); i++){
					rating = (int) Double.parseDouble(value.get(i).get("rating").toString());
					
					
					switch (rating) {
					case 0:
						ratingLabels.get(i).setIcon(new ImageIcon("pictures/0.png"));
						break;
						
					case 1:
						ratingLabels.get(i).setIcon(new ImageIcon("pictures/1.png"));
						break;
						
					case 2:
						ratingLabels.get(i).setIcon(new ImageIcon("pictures/2.png"));
						break;
						
					case 3:
						ratingLabels.get(i).setIcon(new ImageIcon("pictures/3.png"));
						break;
						
					case 4:
						ratingLabels.get(i).setIcon(new ImageIcon("pictures/4.png"));
						break;
						
					case 5:
						ratingLabels.get(i).setIcon(new ImageIcon("pictures/5.png"));
						break;
					default:
						break;
					}
					
					//ratingLabel.setBounds(600, 240 + i * 20, 200, 500);
					ratingLabels.get(i).setVisible(true);
				}
				
				
			}
		});
	}
}
