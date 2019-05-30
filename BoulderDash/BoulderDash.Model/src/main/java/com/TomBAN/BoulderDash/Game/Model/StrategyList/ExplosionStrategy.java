package com.TomBAN.BoulderDash.Game.Model.StrategyList;

import com.TomBAN.BoulderDash.Game.Model.Block;
import com.TomBAN.BoulderDash.Game.Model.Breakable;
import com.TomBAN.BoulderDash.Game.Model.Direction;
import com.TomBAN.BoulderDash.Game.Model.Killable;
import com.TomBAN.BoulderDash.Game.Model.Strategy;
import com.TomBAN.BoulderDash.Game.Model.Unblowable;

public class ExplosionStrategy extends Strategy<Block> {

	public ExplosionStrategy(Block block) {
		super(block);
	}

	@Override
	public void strategy() {
		for(Direction d : Direction.values()) {
			for(int i=1;i<3;i++) {
				final Block goal = block.getNextBlock(d, i);
				if(goal!=null) {
					if(goal instanceof Unblowable) {
						
					}else if(goal instanceof Killable){
						((Killable) goal).getKilled();
						block.getMap().removeBlock(goal);
					}else if(goal instanceof Breakable){
						((Breakable) goal).getBroken();
						block.getMap().removeBlock(goal);
					}else {
						block.getMap().removeBlock(goal);
					}
					break;
				}
			}
		}
		//block.getMap().removeBlock(this);
		//block.getMap().moveBlock(b, x, y);
	}
	
}
