package com.TomBAN.BoulderDash.Game.Model.BlockList;

import com.TomBAN.BoulderDash.Game.Model.Direction;
import com.TomBAN.BoulderDash.Game.Model.Killable;
import com.TomBAN.BoulderDash.Game.Model.MovableBlock;
import com.TomBAN.BoulderDash.Game.Model.StrategyList.ClockwiseEnnemyStrategy;
import com.TomBAN.BoulderDash.Game.Model.StrategyList.RoundTripEnnemyStrategy;

public class Ennemy extends MovableBlock implements Killable {

	private static final String[][] IMAGE = {
			{ "Player/FACING_UP.png", "Player/FACING_LEFT.png", "Player/FACING_DOWN.png", "Player/FACING_RIGHT.png" },
			{ "Player/UP_0.png", "Player/UP_1.png" }, { "Player/LEFT_0.png", "Player/LEFT_1.png" },
			{ "Player/UP_0.png", "Player/UP_1.png" }, { "Player/RIGHT_0.png", "Player/RIGHT_1.png" } };

	public Ennemy(int x, int y, char type) {
		super(IMAGE, x, y);
		switch (type) {
		case 'L':
			setDirection(Direction.Left);
			setStrategy(new RoundTripEnnemyStrategy(this, getDirection()));
			break;
		case 'U':
			setDirection(Direction.Up);
			setStrategy(new RoundTripEnnemyStrategy(this, getDirection()));
			break;
		case 'D':
			setDirection(Direction.Up);
			setStrategy(new ClockwiseEnnemyStrategy(this, false));
			break;
		case 'C':
			setDirection(Direction.Up);
			setStrategy(new ClockwiseEnnemyStrategy(this, true));
			break;
		}
	}

	@Override
	public void getKilled() {
		getMap().removeBlock(this);
	}

}
