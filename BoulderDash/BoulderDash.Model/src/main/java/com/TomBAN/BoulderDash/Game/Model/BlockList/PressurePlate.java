package com.TomBAN.BoulderDash.Game.Model.BlockList;

import com.TomBAN.BoulderDash.Game.Model.Block;
import com.TomBAN.BoulderDash.Game.Model.Direction;
import com.TomBAN.BoulderDash.Game.Model.Network;
import com.TomBAN.BoulderDash.Game.Model.BlockInterface.Unblowable;

public class PressurePlate extends Block implements Unblowable {
	private static final String[] IMAGE = { "PressurePlate/PRESSURE_PLATE_0.png",
			"PressurePlate/PRESSURE_PLATE_1.png" };
	private Network network;
	private boolean activated;

	public PressurePlate(int x, int y, Network network) {
		super(IMAGE, x, y);
		this.network = network;
	}

	@Override
	public void executeStrategy() {
		Block onTop = getNextBlock(Direction.Up);
		if (onTop != null) {
			getNetwork().activate(this);
		} else {
			getNetwork().disactivate(this);
		}
		setActivated(onTop != null);
	}

	@Override
	public int getImageId() {
		return (activated) ? 1 : 0;
	}

	public Network getNetwork() {
		return network;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

}
