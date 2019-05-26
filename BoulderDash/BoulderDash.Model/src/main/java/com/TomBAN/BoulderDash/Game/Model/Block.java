package com.TomBAN.BoulderDash.Game.Model;

import java.awt.Image;

import com.TomBAN.BoulderDash.Ressource.RessourceManager;

public class Block {
	private Strategy strategy;
	private Image[] images;
	private Map map;
	private float x, y;
	private int xIndex, yIndex;
	private int lastUpdate=0;
	public Block(String[] image, int x, int y) {
		this.images = new Image[image.length];
		for(int i=0;i<image.length;i++) {
			this.images[i] = RessourceManager.getInstance().getImage(image[i]);
		}
		this.x = x;
		this.y = y;
		this.xIndex=x;
		this.yIndex=y;
	}
	
	public int getImageId() {
		return lastUpdate%images.length;
	}
	
	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void update(int update) {
		if(strategy!=null) {
			if(lastUpdate!=update) {				
				strategy.strategy();
			}
			lastUpdate = update;
		}
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public Image getImage() {
		return images[getImageId()%images.length];
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

	public void bindMap(Map map) {
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
	public int getLastUpdate() {
		return lastUpdate;
	}
}
