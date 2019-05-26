package com.TomBAN.BoulderDash.Game.Model.StrategyList;

import com.TomBAN.BoulderDash.Game.Model.Block;
import com.TomBAN.BoulderDash.Game.Model.Direction;
import com.TomBAN.BoulderDash.Game.Model.Strategy;
import com.TomBAN.BoulderDash.Game.Model.BlockList.Ennemy;
import com.TomBAN.BoulderDash.Game.Model.BlockList.Player;

public class ClockwiseEnnemyStrategy extends Strategy<Ennemy> {
	private boolean clockwise;
	private boolean binded=false;
	public ClockwiseEnnemyStrategy(Ennemy block,boolean clockwise) {
		super(block);
		this.clockwise=clockwise;
	}
	@Override
	public void strategy() {
		if(!binded) {
			for(int i=0;i<4;i++) {
				Direction d = Direction.values()[i];
				Block next = block.getNextBlock(d);
				if(next != null) {
					block.setDirection(Direction.values()[(i+2)%4]);
					binded=true;
					return;
				}
			}
			block.move(block.getDirection(), 3);
		}else {
			for(int i=0;i<4;i++) {
				Direction d = Direction.values()[(block.getDirection().ordinal()+((clockwise)?-i+1:i-1)+8)%4];
				Block next = block.getNextBlock(d);
				if(next == null) {
					block.move(d, 3);
					return;
				}else if(next instanceof Player){
					((Player) next).getKilled();
					block.move(d, 3);
					return;
				}
			}
			
		}
	}

}
