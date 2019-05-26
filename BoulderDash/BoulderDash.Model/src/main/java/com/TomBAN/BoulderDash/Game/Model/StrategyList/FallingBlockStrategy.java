package com.TomBAN.BoulderDash.Game.Model.StrategyList;

import com.TomBAN.BoulderDash.Game.Model.Block;
import com.TomBAN.BoulderDash.Game.Model.Direction;
import com.TomBAN.BoulderDash.Game.Model.Instable;
import com.TomBAN.BoulderDash.Game.Model.Killable;
import com.TomBAN.BoulderDash.Game.Model.MovableBlock;
import com.TomBAN.BoulderDash.Game.Model.Strategy;

public class FallingBlockStrategy extends Strategy<MovableBlock> {
	private boolean wasFalling = false;

	public FallingBlockStrategy(MovableBlock block) {
		super(block);
	}

	@Override
	public void strategy() {
		final Block under = block.getMap().getBlockAt(block.getxIndex(), block.getyIndex() + 1);
		if (under == null) {
			((MovableBlock) block).move(Direction.Down, 4);
			wasFalling = true;
			return;
		} else if (wasFalling && under instanceof Killable) {
			((Killable) under).getKilled();
			((MovableBlock) block).move(Direction.Down, 4);
			return;
		} else if (under instanceof Instable) {
			final Block left = block.getMap().getBlockAt(block.getxIndex() - 1, block.getyIndex());
			final Block leftDown = block.getMap().getBlockAt(block.getxIndex() - 1, block.getyIndex() + 1);
			if (left == null && leftDown == null && !wasFalling) {
				block.getMap().moveBlock(block, block.getxIndex() - 1, block.getyIndex());
				block.setX(block.getxIndex());
				block.setY(block.getyIndex());
				((MovableBlock) block).move(Direction.Down, 4);
				wasFalling = true;
				return;
			}
			final Block right = block.getMap().getBlockAt(block.getxIndex() + 1, block.getyIndex());
			final Block rightDown = block.getMap().getBlockAt(block.getxIndex() + 1, block.getyIndex() + 1);
			if (right == null && rightDown == null && !wasFalling) {
				block.getMap().moveBlock(block, block.getxIndex() + 1, block.getyIndex());
				block.setX(block.getxIndex());
				block.setY(block.getyIndex());
				block.move(Direction.Down, 4);
				wasFalling = true;
				return;
			}

		}
		wasFalling = false;
	}

}
