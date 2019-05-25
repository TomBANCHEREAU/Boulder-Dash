package com.TomBAN.BoulderDash.Game.Model.StrategyList;

import com.TomBAN.BoulderDash.Game.Model.Block;
import com.TomBAN.BoulderDash.Game.Model.Breakable;
import com.TomBAN.BoulderDash.Game.Model.Direction;
import com.TomBAN.BoulderDash.Game.Model.MovableBlock;
import com.TomBAN.BoulderDash.Game.Model.MovementOrder;
import com.TomBAN.BoulderDash.Game.Model.Pushable;
import com.TomBAN.BoulderDash.Game.Model.Strategy;
import com.TomBAN.BoulderDash.Game.Model.BlockList.Exit;
import com.TomBAN.BoulderDash.Game.Model.BlockList.Player;

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
//			System.out.println(goal+"\n"+xGoal+"\n"+yGoal);
			
			if(goal == null) {
				this_.move(Direction.values()[this_.getCurrentOrder().ordinal()],4);
			}else {
				if(goal instanceof Breakable) {
					this_.move(Direction.values()[this_.getCurrentOrder().ordinal()],4);
					((Breakable) goal).getBroken();
				}else if (goal instanceof Pushable) {

					final Block nextBlock = this_.getMap().getBlockAt(xGoal*2 - this_.getxIndex(), yGoal*2 - this_.getyIndex());
					if(nextBlock==null && (this_.getCurrentOrder() == MovementOrder.GoLeft || this_.getCurrentOrder() == MovementOrder.GoRight)) {
						((MovableBlock) goal).move(Direction.values()[this_.getCurrentOrder().ordinal()],4);
						this_.move(Direction.values()[this_.getCurrentOrder().ordinal()],4);
					}
				}else if(goal instanceof Exit) {
					((Exit) goal).GoOut(this_);
				}
			}
		
		}
	}


}
