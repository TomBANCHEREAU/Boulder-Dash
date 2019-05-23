package com.TomBAN.BoulderDash.Model.BlockList;

import com.TomBAN.BoulderDash.Model.Controllable;
import com.TomBAN.BoulderDash.Model.Killable;
import com.TomBAN.BoulderDash.Model.MovableBlock;
import com.TomBAN.BoulderDash.Model.MovementOrder;
import com.TomBAN.BoulderDash.Model.StrategyList.PlayerStrategy;

public class Player extends MovableBlock implements Killable,Controllable{
	


	private static final String IMAGE = "STAND.png";
	private MovementOrder currentOrder = MovementOrder.StopMovement;
	public Player(int x, int y) {
		super(IMAGE, x, y);
		setStrategy(new PlayerStrategy());
	}

	@Override
	public void getKilled() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executeOrder(MovementOrder order) {
		setCurrentOrder(order);
	}

	public MovementOrder getCurrentOrder() {
		return currentOrder;
	}

	public void setCurrentOrder(MovementOrder currentOrder) {
		this.currentOrder = currentOrder;
	}

}
