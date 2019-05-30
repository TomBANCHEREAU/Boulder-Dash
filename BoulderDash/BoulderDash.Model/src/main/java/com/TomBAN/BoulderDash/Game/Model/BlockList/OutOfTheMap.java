package com.TomBAN.BoulderDash.Game.Model.BlockList;

import com.TomBAN.BoulderDash.Game.Model.Block;
import com.TomBAN.BoulderDash.Game.Model.BlockInterface.Unblowable;

public class OutOfTheMap extends Block implements Unblowable{

	private static final String[] IMAGE = {"DIRT.png"};

	public OutOfTheMap(int x, int y) {
		super(IMAGE, x, y);
	}

}
