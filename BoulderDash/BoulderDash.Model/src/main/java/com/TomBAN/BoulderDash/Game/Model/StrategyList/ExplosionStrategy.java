package com.TomBAN.BoulderDash.Game.Model.StrategyList;

import com.TomBAN.BoulderDash.Game.Model.Block;
import com.TomBAN.BoulderDash.Game.Model.Strategy;
import com.TomBAN.BoulderDash.Game.Model.BlockInterface.Breakable;
import com.TomBAN.BoulderDash.Game.Model.BlockInterface.Killable;
import com.TomBAN.BoulderDash.Game.Model.BlockInterface.Unblowable;

public class ExplosionStrategy extends Strategy<Block> {

	public ExplosionStrategy(Block block) {
		super(block);
	}

	@Override
	public void strategy() {
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				final Block goal = block.getNextBlock(i, j);
				if (goal != null) {
					if (goal instanceof Unblowable) {

					} else if (goal instanceof Killable) {
						((Killable) goal).getKilled();
						blowBlock(goal);
					} else if (goal instanceof Breakable) {
						((Breakable) goal).getBroken();
						blowBlock(goal);
					} else {
						blowBlock(goal);
					}
				}
			}
		}
	}

	public void blowBlock(Block goal) {
		block.getMap().removeBlock(goal);

	}

}
