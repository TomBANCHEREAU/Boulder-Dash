/**
 * 
 */
package com.TomBAN.BoulderDash.Model.BlockList;

import com.TomBAN.BoulderDash.Model.Breakable;
import com.TomBAN.BoulderDash.Model.MovableBlock;
import com.TomBAN.BoulderDash.Model.StrategyList.RockStrategy;

/**
 * @author TomBANCHEREAU
 *
 */
public class Diamond extends MovableBlock implements Breakable {
	private static final String[] IMAGE = {"Diamond/DIAMOND_0.png","Diamond/DIAMOND_1.png","Diamond/DIAMOND_2.png","Diamond/DIAMOND_3.png"};
	public Diamond(int x, int y) {
		super(IMAGE, x, y);
		setStrategy(new RockStrategy(false));
	}

	@Override
	public int getImageId() {
		// TODO Auto-generated method stub
		return (getLastUpdate()/2)%IMAGE.length;
	}
	@Override
	public void getBroken() {
		// TODO Auto-generated method stub

	}

}
