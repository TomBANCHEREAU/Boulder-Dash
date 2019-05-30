package com.TomBAN.BoulderDash.Game.Model;

import java.awt.Image;

import com.TomBAN.BoulderDash.Ressource.RessourceManager;

public class Block {
	private Strategy strategy;
	private Image[] images;
	private Map map;
	private float x, y;
	private int xIndex, yIndex;
	private int lastUpdate = 0;

	public Block(String[] image, int x, int y) {
		this.images = new Image[image.length];
		for (int i = 0; i < image.length; i++) {
			this.images[i] = RessourceManager.getInstance().getImage(image[i]);
		}
		this.x = x;
		this.y = y;
		this.xIndex = x;
		this.yIndex = y;
	}

	public int getImageId() {
		return lastUpdate % images.length;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void update(int update) {
		if (strategy != null) {
			if (lastUpdate != update) {
				strategy.strategy();
			}
			lastUpdate = update;
		}
	}

	public Block getNextBlock(Direction d) {
		int xGoal = this.getxIndex(), yGoal = this.getyIndex();
		switch (d) {
		case Up:
			yGoal -= 1;
			break;
		case Left:
			xGoal -= 1;
			break;
		case Down:
			yGoal += 1;
			break;
		case Right:
			xGoal += 1;
			break;
		}
		return getMap().getBlockAt(xGoal, yGoal);
	}

	public Block getNextBlock(Direction d, int dist) {
		int xGoal = this.getxIndex(), yGoal = this.getyIndex();
		switch (d) {
		case Up:
			yGoal -= dist;
			break;
		case Left:
			xGoal -= dist;
			break;
		case Down:
			yGoal += dist;
			break;
		case Right:
			xGoal += dist;
			break;
		}
		return getMap().getBlockAt(xGoal, yGoal);
	}

	public Block getNextBlock(int dx, int dy) {
		int xGoal = this.getxIndex(), yGoal = this.getyIndex();
		yGoal += dx;
		xGoal += dy;
		return getMap().getBlockAt(xGoal, yGoal);
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public Image getImage() {
		return images[getImageId() % images.length];
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
