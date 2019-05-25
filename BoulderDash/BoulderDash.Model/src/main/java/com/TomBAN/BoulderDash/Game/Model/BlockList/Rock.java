package com.TomBAN.BoulderDash.Game.Model.BlockList;

import com.TomBAN.BoulderDash.Game.Model.Instable;
import com.TomBAN.BoulderDash.Game.Model.MovableBlock;
import com.TomBAN.BoulderDash.Game.Model.Pushable;
import com.TomBAN.BoulderDash.Game.Model.StrategyList.AnimationStrategy;
import com.TomBAN.BoulderDash.Game.Model.StrategyList.RockStrategy;

public class Rock extends MovableBlock implements Instable, Pushable {
	private static final String[] IMAGE = { "Rock/ROCK_0.png", "Rock/ROCK_1.png", "Rock/ROCK_2.png",
			"Rock/ROCK_3.png" };

	public Rock(int x, int y) {
		super(IMAGE, x, y);
		setStrategy(new RockStrategy(false));
	}

	@Override
	public int getImageId() {
		if (getStrategy() instanceof AnimationStrategy) {
			return (getLastUpdate() / 2) % IMAGE.length;
		}
		return 0;
	}

}
