package com.TomBAN.BoulderDash.Game.Model.BlockList;

import com.TomBAN.BoulderDash.Game.Model.Block;
import com.TomBAN.BoulderDash.Game.Model.BlockInterface.Unblowable;

public class HardBoundary extends Block implements Unblowable{
	private static final String[] IMAGE = {"HARDBOUNDARY.png"};

	public HardBoundary(int x,int y) {
		super(IMAGE, x, y);
	}
}
