package com.TomBAN.BoulderDash.Frame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

/**
 * The panel used with the graphicsBuilder interface to draw on a frame 
 * @author TomBANCHEREAU
 */
public class SimplyPanel extends JPanel implements GraphicsObserver,Observer{
	private static final long serialVersionUID = -8980161293106213468L;
	private GraphicsBuilder graphicsBuilder;
	public SimplyPanel(GraphicsBuilder graphicsBuilder) {
		setGraphicsBuilder(graphicsBuilder);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		graphicsBuilder.draw((Graphics2D) g, this);
	}
	public void setGraphicsBuilder(GraphicsBuilder graphicsBuilder) {
		if(this.graphicsBuilder!=null) {
			if(this.graphicsBuilder.getObservable()!=null) {
				this.graphicsBuilder.getObservable().deleteObserver(this);
			}
		}
		this.graphicsBuilder = graphicsBuilder;
		if(graphicsBuilder!=null) {			
			if(graphicsBuilder.getObservable()!=null) {
				graphicsBuilder.getObservable().addObserver(this);
			}
		}
	}
	public GraphicsBuilder getGraphicsBuilder() {
		return graphicsBuilder;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
	}
}
