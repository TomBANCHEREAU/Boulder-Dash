package com.TomBAN.BoulderDash.Model;

import java.awt.Image;

public class Block {
	private Image image;
	private Map map;
	private float x, y;

	public Block(String image, int x, int y) {
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public int getIntX() {
		return (int) Math.floor(x);
	}

	public int getIntY() {
		return (int) Math.floor(y);
	}

	public Image getImage() {
		return image;
	}

	public void setMap(Map map) {
		this.map = map;
	}
}
