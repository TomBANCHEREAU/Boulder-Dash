package com.TomBAN.BoulderDash.Game.Model.BlockList;

import com.TomBAN.BoulderDash.Game.Model.Block;

public class Exit extends Block {
	private static final String[] IMAGE = { "Exit/EXIT_CLOSED.png","Exit/EXIT_0.png","Exit/EXIT_1.png","Exit/EXIT_2.png", "Exit/EXIT_OPENED.png" };
	private int OpenUpdate=0;
	public Exit(int x, int y) {
		super(IMAGE, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getImageId() {
		if(getMap().getDiamond() >= getMap().getDiamondNeeded() && OpenUpdate < IMAGE.length-1) {
			OpenUpdate++;
		}
		return OpenUpdate;
	}
//	public void getOut() {
//		if (getMap().getBlockAt(getxIndex(), getyIndex()) instanceof Player) {
//			((Player) (getMap().getBlockAt(getxIndex(), getyIndex()))).setWin();
//		}
//		getMap().moveBlock(this, getxIndex(), getyIndex());
//	}

	public void GoOut(Player player) {
		if (getMap().getDiamond() >= getMap().getDiamondNeeded()) {
			player.setWin();
			getMap().removeBlock(player);
		}
	}
	@Override
	public void update(int update) {
		super.update(update);
	}

}
