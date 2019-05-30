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

public class PlayerStrategy extends Strategy<Player> {

	public PlayerStrategy(Player block) {
		super(block);
	}

	@Override
	public void strategy() {

		int xGoal = block.getxIndex(), yGoal = block.getyIndex();

		switch (block.getCurrentOrder()) {
		case GoUp:
			yGoal -= 1;
			break;
		case GoLeft:
			xGoal -= 1;
			break;
		case GoDown:
			yGoal += 1;
			break;
		case GoRight:
			xGoal += 1;
			break;
		case StopMovement:
			return;
		default:
			break;
		}

		final Block goal = block.getMap().getBlockAt(xGoal, yGoal);

		if (goal == null) {
			block.move(Direction.values()[block.getCurrentOrder().ordinal()], 4);
		} else {
			if (goal instanceof Breakable) {
				((Breakable) goal).getBroken();
				block.getMap().removeBlock(goal);
				block.move(Direction.values()[block.getCurrentOrder().ordinal()], 4);
			} else if (goal instanceof Pushable) {

				final Block nextBlock = block.getMap().getBlockAt(xGoal * 2 - block.getxIndex(),
						yGoal * 2 - block.getyIndex());
				if (nextBlock == null && (block.getCurrentOrder() == MovementOrder.GoLeft
						|| block.getCurrentOrder() == MovementOrder.GoRight)) {
					((MovableBlock) goal).move(Direction.values()[block.getCurrentOrder().ordinal()], 4);
					block.move(Direction.values()[block.getCurrentOrder().ordinal()], 4);
				}
			} else if (goal instanceof Exit) {
				((Exit) goal).GoOut(block);
			}
		}

	}

}
