package com.TomBAN.BoulderDash.Game.Model.BlockList;

import com.TomBAN.BoulderDash.Game.Model.Block;

public class OutOfTheMap extends Block{

	private static final String[] IMAGE = {"DIRT.png"};

	public OutOfTheMap(int x, int y) {
		super(IMAGE, x, y);
	}

}