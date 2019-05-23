package com.TomBAN.BoulderDash.Model.BlockList;

import java.awt.Image;

import com.TomBAN.BoulderDash.Model.Controllable;
import com.TomBAN.BoulderDash.Model.Direction;
import com.TomBAN.BoulderDash.Model.Killable;
import com.TomBAN.BoulderDash.Model.MovableBlock;
import com.TomBAN.BoulderDash.Model.MovementOrder;
import com.TomBAN.BoulderDash.Model.StrategyList.AnimationStrategy;
import com.TomBAN.BoulderDash.Model.StrategyList.PlayerStrategy;

public class Player extends MovableBlock implements Killable,Controllable{
	


	private static final String[][] IMAGE = {{"Player/FACING_UP.png","Player/FACING_LEFT.png","Player/FACING_DOWN.png","Player/FACING_RIGHT.png"}
											,{"Player/UP_0.png","Player/UP_1.png"}
											,{"Player/LEFT_0.png","Player/LEFT_1.png"}
											,{"Player/UP_0.png","Player/UP_1.png"}
//											,{"Player/DOWN_0.png","Player/DOWN_1.png"}
											,{"Player/RIGHT_0.png","Player/RIGHT_1.png"}};
	private MovementOrder currentOrder = MovementOrder.StopMovement;
	public Player(int x, int y) {
		super(IMAGE, x, y);
		setStrategy(new PlayerStrategy());
	}

	@Override
	public void getKilled() {
		// TODO Auto-generated method stub
		getMap().removeBlock(this);
	}

	@Override
	public void executeOrder(MovementOrder order) {
		setCurrentOrder(order);
		if(order!=MovementOrder.StopMovement && !(getStrategy()instanceof AnimationStrategy) ) {
			setDirection(Direction.values()[order.ordinal()]);
		}
	}

	public MovementOrder getCurrentOrder() {
		return currentOrder;
	}

	public void setCurrentOrder(MovementOrder currentOrder) {
		this.currentOrder = currentOrder;
	}
}
