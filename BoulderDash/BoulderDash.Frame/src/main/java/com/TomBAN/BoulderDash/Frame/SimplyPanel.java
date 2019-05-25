package com.TomBAN.BoulderDash.Frame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

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
		this.graphicsBuilder = graphicsBuilder;
		graphicsBuilder.getObservable().addObserver(this);
		graphicsBuilder.setPanel(this);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
	}
}
