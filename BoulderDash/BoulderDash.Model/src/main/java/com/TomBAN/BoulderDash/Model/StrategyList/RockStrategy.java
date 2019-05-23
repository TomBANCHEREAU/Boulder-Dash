package com.TomBAN.BoulderDash.Model.StrategyList;

import com.TomBAN.BoulderDash.Model.Block;
import com.TomBAN.BoulderDash.Model.Direction;
import com.TomBAN.BoulderDash.Model.MovableBlock;
import com.TomBAN.BoulderDash.Model.Strategy;

public class RockStrategy implements Strategy {

	@Override
	public void strategy(Block this_) {
		if(this_ instanceof MovableBlock) {
			if(this_.getMap().getBlockAt(this_.getxIndex(), this_.getyIndex()+1)==null) {
				((MovableBlock) this_).move(Direction.Down, 4);
			}
		}
	}

}
