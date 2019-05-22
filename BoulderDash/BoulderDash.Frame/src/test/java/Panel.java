import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Panel extends JPanel{
	private Compteur compteur;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Panel() {
		compteur = new Compteur();
		JButton button = new JButton ("TWICE <3");
		
		add(button);
		
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println("TWICE <3");
				compteur.addtwice();
				System.out.println(compteur.getTwice());
			}
		});
	}
	public void paintComponent(Graphics g) {

	
	 Font font = new Font("Arial", Font.ITALIC, 40);
		g.setFont(font);
		g.setColor(Color.MAGENTA);
		g.drawString("Nous sommes beaux et forts comme TWICE !", 250, 230);
		//g.fillRect(100, 150, 25, 25);
		try {
			Image image = ImageIO.read(new File("images/Capture.PNG"));
			g.drawImage(image, 400, 250, this);
		} catch (IOException e) {
			
	 

	}

	}
}
