package com.TomBAN.BoulderDash.Model.BlockList;

import com.TomBAN.BoulderDash.Model.Block;

public class OutOfTheMap extends Block{

	private static final String[] IMAGE = {"DIRT.png"};

	public OutOfTheMap(int x, int y) {
		super(IMAGE, x, y);
	}

}
