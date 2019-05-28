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
	public BoulderDashFrame(int width,int height) {
		this.setSize(width, height);
		this.setTitle("BoulderDash");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setResizable(false);
		this.setVisible(true);
	}
	public void update(Observable o, Object arg) {
		repaint();
	}

}
