package com.TomBAN.BoulderDash.Game.Model;

import java.awt.Image;

import com.TomBAN.BoulderDash.Game.Model.StrategyList.AnimationStrategy;
import com.TomBAN.BoulderDash.Ressource.RessourceManager;

public class MovableBlock extends Block{
	private Image[][] images;
	private Direction direction;
	public MovableBlock(String[][] image,int x,int y) {
		super(image[0],x,y);
		direction=Direction.Down;
		images = new Image[4][];
		for(int i=1;i<5;i++) {
			images[i-1] = new Image[image[i].length];
			for(int j=0;j<image[i].length;j++) {
				images[i-1][j] = RessourceManager.getInstance().getImage(image[i][j]); 
			}
		}
	}
	public MovableBlock(String[] image,int x,int y) {
		super(image,x,y);
		direction=Direction.Down;
	}
	

	
	public void move(Direction d,int speed) {
		direction = d;
		int xGoal=this.getxIndex(),yGoal=this.getyIndex();
		
		switch (direction) {
		case Up:
			yGoal-=1;
			break;
		case Left:
			xGoal-=1;
			break;
		case Down:
			yGoal+=1;
			break;
		case Right:
			xGoal+=1;
		}
		getMap().moveBlock(this, xGoal, yGoal);
		this.setStrategy(new AnimationStrategy(this,getStrategy(), xGoal, yGoal, speed));
	}
	
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	@Override
	public Image getImage() {
		if(getStrategy() instanceof AnimationStrategy && images!=null) {
			return images[direction.ordinal()][getLastUpdate()%images[direction.ordinal()].length];
		}
		return super.getImage();
	}
	
	@Override
	public int getImageId() {
		return direction.ordinal();
	}
	
}
