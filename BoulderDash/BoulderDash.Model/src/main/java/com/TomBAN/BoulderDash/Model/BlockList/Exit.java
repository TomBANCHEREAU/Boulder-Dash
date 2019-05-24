package com.TomBAN.BoulderDash.Model.BlockList;

import com.TomBAN.BoulderDash.Model.Block;
import com.TomBAN.BoulderDash.Model.Breakable;

public class Exit extends Block implements Breakable {
	private static final String[] IMAGE = {"DIRT.png"};
	
	public Exit( int x, int y) {
		super(IMAGE, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void getBroken() {
		if(getMap().getBlockAt(getxIndex(), getyIndex()) instanceof Player) {
			((Player)(getMap().getBlockAt(getxIndex(), getyIndex()))).setWin();
		}
		getMap().moveBlock(this, getxIndex(), getyIndex());
	}

}
