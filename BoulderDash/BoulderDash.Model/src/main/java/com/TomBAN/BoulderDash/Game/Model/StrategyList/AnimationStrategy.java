package com.TomBAN.BoulderDash.Game.Model.StrategyList;

import com.TomBAN.BoulderDash.Game.Model.MovableBlock;
import com.TomBAN.BoulderDash.Game.Model.Strategy;

public class AnimationStrategy extends Strategy<MovableBlock> {
	private final Strategy next;
	private final int xGoal,yGoal;
	private int time;



	public AnimationStrategy(MovableBlock block, Strategy next, int xGoal, int yGoal, int time) {
		super(block);
		this.next = next;
		this.xGoal = xGoal;
		this.yGoal = yGoal;
		this.time = time;
	}



	@Override
	public void strategy() {
		// TODO Auto-generated method stub
		block.setX(block.getX() + (-block.getX()+xGoal)/time);
		block.setY(block.getY() + (-block.getY()+yGoal)/time);
		time--;
		if(time==0) {
			block.setStrategy(next);
			block.setX(block.getxIndex());
			block.setY(block.getyIndex());
			next.strategy();
		}
	}
	public int getTime() {
		return time;
	}


}
