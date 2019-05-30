package com.TomBAN.BoulderDash.Game.Model.BlockList;

import com.TomBAN.BoulderDash.Game.Model.Block;
import com.TomBAN.BoulderDash.Game.Model.BlockInterface.Unblowable;

public class BlockController extends Block implements Unblowable{
	private static final String[] IMAGE = {"PressurePlate/PRESSURE_PLATE_0.png","PressurePlate/PRESSURE_PLATE_1.png"};

	public BlockController(int x, int y) {
		super(IMAGE, x, y);
		// TODO Auto-generated constructor stub
	}

}
