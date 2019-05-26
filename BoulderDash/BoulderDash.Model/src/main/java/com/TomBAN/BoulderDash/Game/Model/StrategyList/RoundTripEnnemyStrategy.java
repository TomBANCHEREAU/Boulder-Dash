package com.TomBAN.BoulderDash.Game.Model.StrategyList;

import com.TomBAN.BoulderDash.Game.Model.Block;
import com.TomBAN.BoulderDash.Game.Model.Direction;
import com.TomBAN.BoulderDash.Game.Model.Strategy;
import com.TomBAN.BoulderDash.Game.Model.BlockList.Ennemy;
import com.TomBAN.BoulderDash.Game.Model.BlockList.Player;

public class RoundTripEnnemyStrategy extends Strategy<Ennemy> {
	public RoundTripEnnemyStrategy(Ennemy block,Direction direction) {
		super(block);
		block.setDirection(direction);
	}

	@Override
	public void strategy() {
		Block goal = block.getBlockAtFront();
		if(goal == null) {
			block.move(block.getDirection(), 5);
		}else if(goal instanceof Player) {
			((Player) goal).getKilled();
			block.move(block.getDirection(), 5);
		}else {
			block.setDirection(Direction.values()[(block.getDirection().ordinal()+2)%4]);
		}
	}

}
