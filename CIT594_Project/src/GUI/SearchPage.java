package GUI;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SearchPage extends JFrame{
	private BufferedImage image3;
	private BufferedImage image4;
	
	
	JLabel background;
	
	public SearchPage(String name) {
		
		super(name);
		
		try {
			image3 = ImageIO.read(new File("imag3.jpg"));
			background = new JLabel(new ImageIcon(image3));
			this.setContentPane(background);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		this.setSize(800, 800);
		this.setVisible(true);
	}
}
