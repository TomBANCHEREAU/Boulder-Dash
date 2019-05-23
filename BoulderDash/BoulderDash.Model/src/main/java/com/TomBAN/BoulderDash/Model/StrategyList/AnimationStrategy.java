package com.TomBAN.BoulderDash.Model.StrategyList;

import com.TomBAN.BoulderDash.Model.Block;
import com.TomBAN.BoulderDash.Model.Strategy;

public class AnimationStrategy implements Strategy {
	private final Strategy next;
	private final int xGoal,yGoal;
	private int time;



	public AnimationStrategy(Strategy next, int xGoal, int yGoal, int time) {
		this.next = next;
		this.xGoal = xGoal;
		this.yGoal = yGoal;
		this.time = time;
	}



	@Override
	public void strategy(Block this_) {
		// TODO Auto-generated method stub
		this_.setX(this_.getX() + (-this_.getX()+xGoal)/time);
		this_.setY(this_.getY() + (-this_.getY()+yGoal)/time);
		time--;
		if(time==0) {
			this_.setStrategy(next);
			next.strategy(this_);
		}
	}
	public int getTime() {
		return time;
	}
}
