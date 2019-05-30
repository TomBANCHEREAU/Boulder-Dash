package com.TomBAN.BoulderDash.Game.Model.BlockList;

import com.TomBAN.BoulderDash.Game.Model.Activable;
import com.TomBAN.BoulderDash.Game.Model.Block;
import com.TomBAN.BoulderDash.Game.Model.Network;
import com.TomBAN.BoulderDash.Game.Model.BlockInterface.Killable;
import com.TomBAN.BoulderDash.Game.Model.BlockInterface.Unblowable;

public class Door extends Block implements Unblowable, Activable {
	private static final String[] IMAGE = { "Door/DOOR_0.png", "Door/DOOR_1.png", "Door/DOOR_2.png", "Door/DOOR_3.png",
			"Door/DOOR_4.png", "Door/DOOR_5.png", "Door/DOOR_6.png" };
//	private Network network;
	private int activationState;
	private boolean closing;

	public Door(int x, int y, Network network) {
		super(IMAGE, x, y);
		activationState = 0;
		closing=true;
//		this.network = network;
		network.addActivable(this);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void executeStrategy() {
		if (closing && activationState > 0) {
			activationState--;
		}
		if (!closing) {
			activationState++;
		}

		if (activationState > IMAGE.length - 1) {
			getMap().removeBlock(this);
		}
	}

	@Override
	public int getImageId() {
		return activationState;
	}

	@Override
	public void activate() {
		closing = false;
	}

	@Override
	public void disactivate() {
		Block here = getMap().getBlockAt(getxIndex(), getyIndex());
		if (here != this) {
			if (here instanceof Killable) {
				((Killable) here).getKilled();
			}
			getMap().addBlock(this);
		}
		activationState = IMAGE.length-1;
		closing = true;
	}
}
