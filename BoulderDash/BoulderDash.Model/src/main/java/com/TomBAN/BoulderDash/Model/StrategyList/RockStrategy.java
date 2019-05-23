package com.TomBAN.BoulderDash.Model.StrategyList;

import com.TomBAN.BoulderDash.Model.Block;
import com.TomBAN.BoulderDash.Model.Direction;
import com.TomBAN.BoulderDash.Model.Killable;
import com.TomBAN.BoulderDash.Model.MovableBlock;
import com.TomBAN.BoulderDash.Model.Strategy;

public class RockStrategy implements Strategy {
	
	private boolean wasFalling;

	public RockStrategy(boolean b) {
		this.wasFalling = b;
	}

	@Override
	public void strategy(Block this_) {
		if(this_ instanceof MovableBlock) {
			final Block under = this_.getMap().getBlockAt(this_.getxIndex(), this_.getyIndex()+1);
			if(under==null) {
				((MovableBlock) this_).move(Direction.Down, 4);
				wasFalling=true;
			}else if(wasFalling && under instanceof Killable){
				((Killable) under).getKilled();
				((MovableBlock) this_).move(Direction.Down, 4);
			}else {
				wasFalling=false;
			}
		}
	}

}
