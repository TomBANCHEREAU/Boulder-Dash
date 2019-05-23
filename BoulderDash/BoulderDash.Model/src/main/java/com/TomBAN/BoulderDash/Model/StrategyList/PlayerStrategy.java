package com.TomBAN.BoulderDash.Model.StrategyList;

import com.TomBAN.BoulderDash.Model.Block;
import com.TomBAN.BoulderDash.Model.Breakable;
import com.TomBAN.BoulderDash.Model.Strategy;
import com.TomBAN.BoulderDash.Model.BlockList.Player;

public class PlayerStrategy implements Strategy {

	@Override
	public void strategy(Block this__) {
		if(this__ instanceof Player) {
			
			Player this_ = (Player)(this__);
			int xGoal=this_.getxIndex(),yGoal=this_.getyIndex();
			
			switch (this_.getCurrentOrder()) {
			case GoUp:
				yGoal-=1;
				break;
			case GoLeft:
				xGoal-=1;
				break;
			case GoDown:
				yGoal+=1;
				break;
			case GoRight:
				xGoal+=1;
				break;
			case StopMovement:return;
			}
			
			final Block goal = this_.getMap().getBlockAt(xGoal, yGoal);
			System.out.println(goal+"\n"+xGoal+"\n"+yGoal);
			
			if(goal == null) {
				moveTo(this_,xGoal, yGoal);
			}else {
				if(goal instanceof Breakable) {
					((Breakable) goal).getBroken();
					moveTo(this_,xGoal, yGoal);
				}
			}
		
		}
	}

	private void moveTo(Player this_, int xGoal, int yGoal) {
		this_.getMap().moveBlock(this_, xGoal, yGoal);
		this_.setStrategy(new AnimationStrategy(this, xGoal, yGoal, 4));
	}
}
