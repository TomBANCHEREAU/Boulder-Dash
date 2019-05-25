package com.TomBAN.BoulderDash.Game.Model.StrategyList;

import com.TomBAN.BoulderDash.Game.Model.Block;
import com.TomBAN.BoulderDash.Game.Model.Direction;
import com.TomBAN.BoulderDash.Game.Model.Instable;
import com.TomBAN.BoulderDash.Game.Model.Killable;
import com.TomBAN.BoulderDash.Game.Model.MovableBlock;
import com.TomBAN.BoulderDash.Game.Model.Strategy;

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
				return;
			}else if(wasFalling && under instanceof Killable){
				((Killable) under).getKilled();
				((MovableBlock) this_).move(Direction.Down, 4);
				return;
			}else if(under instanceof Instable){
				final Block left = this_.getMap().getBlockAt(this_.getxIndex()-1, this_.getyIndex());
				final Block leftDown = this_.getMap().getBlockAt(this_.getxIndex()-1, this_.getyIndex()+1);
				if(left==null && leftDown==null && !wasFalling) {
					this_.getMap().moveBlock(this_, this_.getxIndex()-1, this_.getyIndex());
					this_.setX(this_.getxIndex());
					this_.setY(this_.getyIndex());
					((MovableBlock) this_).move(Direction.Down, 4);
					wasFalling=true;
					return;
				}
				final Block right = this_.getMap().getBlockAt(this_.getxIndex()+1, this_.getyIndex());
				final Block rightDown = this_.getMap().getBlockAt(this_.getxIndex()+1, this_.getyIndex()+1);
				if(right==null && rightDown==null && !wasFalling) {
					this_.getMap().moveBlock(this_, this_.getxIndex()+1, this_.getyIndex());
					this_.setX(this_.getxIndex());
					this_.setY(this_.getyIndex());
					((MovableBlock) this_).move(Direction.Down, 4);
					wasFalling=true;
					return;
				}
				
			}
		}
		wasFalling=false;
	}

}
