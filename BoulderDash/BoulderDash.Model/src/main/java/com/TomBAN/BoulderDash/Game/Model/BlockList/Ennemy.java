package com.TomBAN.BoulderDash.Game.Model.BlockList;

import com.TomBAN.BoulderDash.Game.Model.Direction;
import com.TomBAN.BoulderDash.Game.Model.MovableBlock;
import com.TomBAN.BoulderDash.Game.Model.BlockInterface.Killable;
import com.TomBAN.BoulderDash.Game.Model.StrategyList.ClockwiseEnnemyStrategy;
import com.TomBAN.BoulderDash.Game.Model.StrategyList.ExplosionStrategy;
import com.TomBAN.BoulderDash.Game.Model.StrategyList.RoundTripEnnemyStrategy;

public class Ennemy extends MovableBlock implements Killable {

	private static final String[][] IMAGE = {
			{ "EnemyA/ENEMY_0.png", "EnemyA/ENEMY_1.png", "EnemyA/ENEMY_2.png", "EnemyA/ENEMY_3.png" },
			{ "EnemyB/ENEMY_0.png", "EnemyB/ENEMY_1.png", "EnemyB/ENEMY_2.png", "EnemyB/ENEMY_3.png" } };

	public Ennemy(int x, int y, char type) {
		super(getImage(type), x, y);
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

	public static String[] getImage(char type) {
		switch (type) {
		case 'L':return IMAGE[0];
		case 'U':return IMAGE[0];
		case 'D':return IMAGE[1];
		case 'C':return IMAGE[1];
		}
		return null;
	}

	@Override
	public int getImageId() {
		// TODO Auto-generated method stub
		return getLastUpdate() % IMAGE[0].length;
	}

	@Override
	public void getKilled() {
		getMap().removeBlock(this);
		getMap().addBlock(new Diamond(getxIndex(), getyIndex()));
		new ExplosionStrategy(this).strategy();
	}

}
