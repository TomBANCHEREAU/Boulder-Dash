package com.TomBAN.BoulderDash.Game.Model;

public abstract class Strategy<T extends Block> {
	protected T block;
	public Strategy(T block) {
		this.block = block;
	}


	public abstract void strategy();

//	public T getBlock() {
//		return block;
//	}

}
