package com.TomBAN.BoulderDash.Game.Model.BlockList;

import com.TomBAN.BoulderDash.Game.Model.Block;

public class Exit extends Block {
	private static final String[] IMAGE = { "DIRT.png" };

	public Exit(int x, int y) {
		super(IMAGE, x, y);
		// TODO Auto-generated constructor stub
	}

	public void getOut() {
		if (getMap().getBlockAt(getxIndex(), getyIndex()) instanceof Player) {
			((Player) (getMap().getBlockAt(getxIndex(), getyIndex()))).setWin();
		}
		getMap().moveBlock(this, getxIndex(), getyIndex());
	}

	public void GoOut(Player player) {
		System.out.println(getMap().getDiamond());
		if (getMap().getDiamond() >= getMap().getDiamondNeeded()) {
			player.setWin();
			getMap().removeBlock(player);
		}
	}

}
