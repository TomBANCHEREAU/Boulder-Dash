package com.TomBAN.BoulderDash.Frame;

import java.awt.Graphics2D;
import java.util.Observable;

public interface GraphicsBuilder {
	public void draw(final Graphics2D graph,final GraphicsObserver observer);
	public Observable getObservable();
	public void setPanel(SimplyPanel panel);
}
