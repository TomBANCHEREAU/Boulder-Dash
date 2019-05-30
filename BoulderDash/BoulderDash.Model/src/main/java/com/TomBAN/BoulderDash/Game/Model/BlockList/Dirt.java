package com.TomBAN.BoulderDash.Game.Model.BlockList;

import com.TomBAN.BoulderDash.Game.Model.MovableBlock;
import com.TomBAN.BoulderDash.Game.Model.BlockInterface.Breakable;

public class Dirt extends MovableBlock implements Breakable{
	private static final String[] IMAGE = {"DIRT.png"};

	public Dirt(int x,int y) {
		super(IMAGE,x,y);
	}

	@Override
	public void getBroken() {
		// TODO Auto-generated method stub
	}
}
