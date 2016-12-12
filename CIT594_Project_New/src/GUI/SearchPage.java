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
import java.util.PriorityQueue;

import javax.swing.border.LineBorder;
import org.json.simple.JSONObject;
import org.omg.CORBA.INTERNAL;

import Sort.sort;

import JSONParse.JSONParse;

/**
 * SearchPage class
 * 
 *
 */
public class SearchPage extends JFrame {
	private User curUser;
	private JTextField term;
	private JTextField location;
	private JTextArea historyText;
	private JButton Gmap, searchButton, sortByButton;
	private ArrayList<JSONObject> value;
	private JComboBox<Object> filterLimit, sortBy;
	private JTextArea resultText;
	private JLabel sh, currUser, sr, title;
	private ArrayList<JLabel> ratingLabels = new ArrayList<>();

	/**
	 * SearchPage constructor
	 * 
	 * @param user
	 */
	public SearchPage(User user) {
		curUser = user;

		// set JFrame attributes
		setTitle("Search");
		setResizable(false);
		setSize(new Dimension(1200, 700));
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// absolute layout
		getContentPane().setLayout(null);
		getContentPane().setBackground(UIManager.getColor("Button.highlight"));

		// textField for term input
		term = new JTextField();
		term.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 24));
		term.setBounds(373, 5, 270, 50);
		getContentPane().add(term);

		// textField for location input
		location = new JTextField();
		location.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 24));
		location.setBounds(760, 5, 270, 50);
		getContentPane().add(location);

		// ratingLabel for search result
		for (int i = 0; i < 10; i++) {
			ratingLabels.add(new JLabel(""));
			getContentPane().add(ratingLabels.get(i));
			ratingLabels.get(i).setBounds(1060, 7 + i * 23, 200, 500);
		}

		// Find button
		JLabel lbFind = new JLabel("Find");
		lbFind.setFont(new Font("Hiragino Kaku Gothic Std", Font.PLAIN, 24));
		lbFind.setHorizontalAlignment(SwingConstants.CENTER);
		lbFind.setPreferredSize(new Dimension(30, 30));
		lbFind.setBounds(290, 5, 85, 50);
		getContentPane().add(lbFind);

		// Near label
		JLabel lbNear = new JLabel("Near");
		lbNear.setPreferredSize(new Dimension(30, 30));
		lbNear.setHorizontalAlignment(SwingConstants.CENTER);
		lbNear.setFont(new Font("Hiragino Kaku Gothic Std", Font.PLAIN, 24));
		lbNear.setBounds(655, 5, 85, 50);
		getContentPane().add(lbNear);

		// history scroll pane
		JScrollPane historyPane = new JScrollPane();
		historyPane.setBorder(new LineBorder(SystemColor.windowBorder));
		historyPane.setBackground(SystemColor.window);
		historyPane.setBounds(5, 245, 275, 425);
		getContentPane().add(historyPane);

		// history text in history pane
		historyText = new JTextArea();
		String history = "";

		// display search history
		if (user.getSearchHistory() == null) {
			history = "No Search Text.";
		} else {

			// load in local file and display user's search history
			for (String string : user.getSearchHistory().keySet()) {
				history += string + "\n\n";
				for (JSONObject jsonObject : user.getSearchHistory().get(string)) {
					history += jsonObject.get("name") + "\n" + jsonObject.get("display_phone") + "\n"
							+ ((JSONObject) jsonObject.get("location")).get("display_address") + "\n\n";
				}
				history += "\n";
			}
		}

		historyText.setText(history);
		historyText.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 18));
		historyText.setLineWrap(true);
		historyText.setWrapStyleWord(true);
		historyPane.setViewportView(historyText);

		// search history label
		sh = new JLabel("Search History");
		sh.setFont(new Font("Apple SD Gothic Neo", Font.BOLD, 28));
		sh.setHorizontalAlignment(SwingConstants.CENTER);
		sh.setBounds(5, 195, 275, 40);
		getContentPane().add(sh);

		// search result label
		sr = new JLabel("Search Result");
		sr.setHorizontalAlignment(SwingConstants.CENTER);
		sr.setFont(new Font("Apple SD Gothic Neo", Font.BOLD, 28));
		sr.setBounds(290, 195, 740, 40);
		getContentPane().add(sr);

		// current user label
		currUser = new JLabel("Current User:");
		currUser.setHorizontalAlignment(SwingConstants.LEFT);
		currUser.setFont(new Font("Apple SD Gothic Neo", Font.BOLD, 18));
		currUser.setBounds(5, 145, 110, 40);
		getContentPane().add(currUser);

		// current user display label
		JLabel loginText = new JLabel("");
		loginText.setHorizontalAlignment(SwingConstants.LEFT);
		loginText.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 22));
		loginText.setText(user.getUsername());
		loginText.setBounds(125, 145, 150, 40);
		getContentPane().add(loginText);

		// left top image label
		title = new JLabel("");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setIcon(new ImageIcon("pictures/ramsey.jpg"));
		title.setBounds(5, 5, 275, 150);
		getContentPane().add(title);

		// search result scroll pane
		JScrollPane resultPane = new JScrollPane();
		resultPane.setBorder(new LineBorder(SystemColor.windowBorder));
		resultPane.setBackground(SystemColor.window);
		resultPane.setBounds(290, 245, 740, 425);
		getContentPane().add(resultPane);

		// search result textField in result pane
		resultText = new JTextArea();
		resultText.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 18));
		resultText.setLineWrap(true);
		resultText.setWrapStyleWord(true);
		resultPane.setViewportView(resultText);

		// combo box for filtering number of result for each search
		String[] def = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		filterLimit = new JComboBox<>(def);
		filterLimit.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 16));
		filterLimit.setSelectedIndex(0);
		filterLimit.setMaximumRowCount(10);
		filterLimit.setBounds(1065, 45, 105, 45);
		getContentPane().add(filterLimit);

		// show map button
		Gmap = new JButton("Show Map");
		Gmap.setFont(new Font("Hiragino Kaku Gothic StdN", Font.PLAIN, 20));
		Gmap.setBounds(1039, 120, 155, 50);
		getContentPane().add(Gmap);

		// find button
		searchButton = new JButton("Find Now!");
		searchButton.setFont(new Font("Hiragino Kaku Gothic StdN", Font.PLAIN, 57));
		searchButton.setBounds(290, 60, 740, 110);
		getContentPane().add(searchButton);

		sortBy = new JComboBox<Object>(new Object[] {});
		sortBy.setModel(new DefaultComboBoxModel(new String[] { "Rating", "Review Count" }));
		sortBy.setSelectedIndex(0);
		sortBy.setMaximumRowCount(10);
		sortBy.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 16));
		sortBy.setBounds(787, 182, 105, 53);
		getContentPane().add(sortBy);

		sortByButton = new JButton("Sort By");
		sortByButton.setFont(new Font("Hiragino Kaku Gothic Std", Font.PLAIN, 16));
		sortByButton.setBounds(905, 190, 125, 40);
		sortByButton.setEnabled(false);
		getContentPane().add(sortByButton);

		// add all listeners to the pane and set background
		addActionListeners();
		getContentPane().setBackground(new Color(255, 255, 240));
		
		JLabel sLimit = new JLabel("Search Limit");
		sLimit.setHorizontalAlignment(SwingConstants.CENTER);
		sLimit.setFont(new Font("Apple SD Gothic Neo", Font.BOLD, 18));
		sLimit.setBounds(1040, 5, 150, 35);
		getContentPane().add(sLimit);

	}

	/**
	 * Add all listeners to relevant buttons in the pane
	 */
	private void addActionListeners() {

		// add listener on show map button
		Gmap.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GoogleMap googleMap = new GoogleMap(curUser, value);
			}
		});

		// add listener on find button
		searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				// get input from user for searching
				String item = term.getText();
				String loc = location.getText();
				
				if (item.trim().length() == 0 || loc.trim().length() == 0) {
					JOptionPane.showMessageDialog(getContentPane(), "Please enter item and location!");
					return;
				}

				for (int i = 0; i < ratingLabels.size(); i++) {
					ratingLabels.get(i).setVisible(false);
				}

				

				// call yelp API for searching
				ArrayList<String> reStrings = YelpAPI.search(item, loc,
						Integer.parseInt((String) filterLimit.getSelectedItem()));
				value = new ArrayList<>();

				// parse the result string from API
				// extract some of keys and their values storing for later use
				for (String string : reStrings) {
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

				// retrieve name and phone number of result businesses
				String reString = "";
				for (JSONObject jsonObject : value) {
					reString += jsonObject.get("name") + "   " + "TEL: " + jsonObject.get("display_phone")
							+ "    Review Count:" + jsonObject.get("review_count") + "\n";
				}

				// display them on result text area
				resultText.setText(reString);

				// write result into file
				curUser.writeSearchHistory(item + " " + loc, value);

				// display rating label according to each business's real rating
				int rating;
				for (int i = 0; i < value.size(); i++) {
					rating = (int) (Double.parseDouble(value.get(i).get("rating").toString()) + 0.6);

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
					ratingLabels.get(i).setVisible(true);
				}
				sortByButton.setEnabled(true);
			}
		});

		sortByButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String condition = sortBy.getSelectedItem().toString();
				PriorityQueue<JSONObject> res = new PriorityQueue<>();
				for (int i = 0; i < value.size(); i++) {
					System.out.println(value.get(i));
				}
				res = sort.sort(value, condition);
				

				String reString = "";

				int rating;
				int c = 0;
				while (!res.isEmpty()) {
					JSONObject js = res.poll();
					rating = (int) (Double.parseDouble(js.get("rating").toString()) + 0.6);

					reString += js.get("name") + "   " + "TEL: " + js.get("display_phone") + "    Review Count:"
							+ js.get("review_count") + "\n";
					switch (rating) {
					case 0:
						ratingLabels.get(c).setIcon(new ImageIcon("pictures/0.png"));
						break;

					case 1:
						ratingLabels.get(c).setIcon(new ImageIcon("pictures/1.png"));
						break;

					case 2:
						ratingLabels.get(c).setIcon(new ImageIcon("pictures/2.png"));
						break;

					case 3:
						ratingLabels.get(c).setIcon(new ImageIcon("pictures/3.png"));
						break;

					case 4:
						ratingLabels.get(c).setIcon(new ImageIcon("pictures/4.png"));
						break;

					case 5:
						ratingLabels.get(c).setIcon(new ImageIcon("pictures/5.png"));
						break;
					default:
						break;
					}
					ratingLabels.get(c).setVisible(true);
					c++;
				}
				resultText.setText(reString);
			}
		});
	}
}
