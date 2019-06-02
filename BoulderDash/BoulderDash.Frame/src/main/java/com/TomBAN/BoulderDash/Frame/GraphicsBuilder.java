package com.TomBAN.BoulderDash.Frame;

import java.awt.Graphics2D;
import java.util.Observable;

/**
 * interface used with the SimplyPanel to draw on it
 * @author TomBANCHEREAU
 */
public interface GraphicsBuilder {
	public void draw(final Graphics2D graph,final GraphicsObserver observer);
	public Observable getObservable();
}
