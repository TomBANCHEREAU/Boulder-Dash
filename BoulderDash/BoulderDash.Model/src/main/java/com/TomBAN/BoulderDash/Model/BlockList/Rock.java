package com.TomBAN.BoulderDash.Model.BlockList;

import com.TomBAN.BoulderDash.Model.MovableBlock;
import com.TomBAN.BoulderDash.Model.StrategyList.RockStrategy;

public class Rock extends MovableBlock {
	private static final String[] IMAGE = {"Rock/ROCK_0.png","Rock/ROCK_1.png","Rock/ROCK_2.png","Rock/ROCK_3.png"};
	public Rock(int x, int y) {
		super(IMAGE, x, y);
		setStrategy(new RockStrategy(false));
	}
}
