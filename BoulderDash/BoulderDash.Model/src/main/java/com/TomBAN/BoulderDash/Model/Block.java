package com.TomBAN.BoulderDash.Model;

import java.awt.Image;

import com.TomBAN.BoulderDash.Ressource.RessourceManager;

public class Block {
	private Strategy strategy;
	private Image image;
	private Map map;
	private float x, y;
	private int xIndex, yIndex;

	public Block(String image, int x, int y) {
		this.image = RessourceManager.getInstance().getImage(image);
		this.x = x;
		this.y = y;
	}
	
	public void update() {
		if(strategy!=null) {
			strategy.strategy(this);
		}
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public Image getImage() {
		return image;
	}

	public int getxIndex() {
		return xIndex;
	}

	public void setxIndex(int xIndex) {
		this.xIndex = xIndex;
	}

	public int getyIndex() {
		return yIndex;
	}

	public void setyIndex(int yIndex) {
		this.yIndex = yIndex;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Map getMap() {
		return map;
	}

	public Strategy getStrategy() {
		return strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
}
