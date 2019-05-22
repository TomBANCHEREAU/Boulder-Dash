package com.TomBAN.BoulderDash.Model.BlockList;

import com.TomBAN.BoulderDash.Model.Breakable;
import com.TomBAN.BoulderDash.Model.MovableBlock;

public class Dirt extends MovableBlock implements Breakable{
	private static final String IMAGE = "";

	public Dirt(int x,int y) {
		super(IMAGE,x,y);
	}

	@Override
	public void getBroken() {
		// TODO Auto-generated method stub
	}
}
