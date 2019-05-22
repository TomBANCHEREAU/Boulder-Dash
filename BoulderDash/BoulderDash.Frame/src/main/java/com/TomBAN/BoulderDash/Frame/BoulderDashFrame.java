/**
 * 
 */
package com.TomBAN.BoulderDash.Frame;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

/**
 * @author Tom BANCHEREAU
 *
 */
public class BoulderDashFrame extends JFrame implements Observer{
	private static final long serialVersionUID = -1116924846179363004L;
	private SimplyPanel panel;
	public BoulderDashFrame(int width,int height) {
		// TODO
		this.setSize(width, height);
		this.setTitle("BoulderDash");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		//this.panel=new SimplyPanel(graphicsBuilder);
		//this.setContentPane(panel);
	}
	public void setGraphicsBuilder(GraphicsBuilder graphicsBuilder) {
		panel.setGraphicsBuilder(graphicsBuilder);
		
	}
	public void update(Observable o, Object arg) {
		repaint();
	}

}
