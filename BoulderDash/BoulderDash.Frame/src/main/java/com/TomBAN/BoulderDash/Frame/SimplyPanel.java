package com.TomBAN.BoulderDash.Frame;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class SimplyPanel extends JPanel implements GraphicsObserver{
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
	}
//	@Override
//	public int getWidth() {
//		return (int) (super.getWidth());
//	}
//	@Override
//	public int getHeight() {
//		return (int) (super.getHeight());
//	}
}
