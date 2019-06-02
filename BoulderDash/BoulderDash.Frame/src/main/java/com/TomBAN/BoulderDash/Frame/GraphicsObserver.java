package com.TomBAN.BoulderDash.Frame;

import java.awt.image.ImageObserver;

/**
 * interface which permit to pass an imageObserver and the width and height of the panel at once
 * @author TomBANCHEREAU
 */
public interface GraphicsObserver extends ImageObserver{
	public int getWidth();
	public int getHeight();
}
