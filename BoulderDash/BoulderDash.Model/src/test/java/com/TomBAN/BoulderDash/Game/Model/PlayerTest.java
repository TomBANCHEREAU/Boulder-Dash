package com.TomBAN.BoulderDash.Game.Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.TomBAN.BoulderDash.Game.Model.BlockList.Player;
import com.TomBAN.BoulderDash.Game.Model.BlockList.Rock;

public class PlayerTest {
	private Map map;
	private static Controllable controllable;
	private static ControllableController controller;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		ArrayList<ControllableController> a = new ArrayList<ControllableController>();
		controller = new ControllableController() {
			@Override
			public void bindControllable(Controllable controllable) {
				PlayerTest.controllable = controllable;
			}
		};
		a.add(controller);
		map = new StringMap(4, 3, 0, 1, "    \n"+
										"VAO \n"+
										"##$ ", 0, 0, null, 0, 0).toRealMap(a);
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
//	public void testMapCreation() {
//		
//	}
	@Test
	public void testMovement() {
		final int oldY = ((Player) controllable).getyIndex();
		controllable.executeOrder(MovementOrder.GoUp);
		assertEquals(oldY, ((Player) controllable).getyIndex());
		if(!(map.getBlockAt(1, 1) instanceof Player)) {
			fail();
		}
		map.updateAllBlock();
		assertEquals(oldY-1, ((Player) controllable).getyIndex());
		if(map.getBlockAt(1, 1) instanceof Player) {
			fail();
		}
	}
	@Test
	public void testGetDiamond() {
		controllable.executeOrder(MovementOrder.GoLeft);
		assertEquals(0, map.getDiamond());
		map.updateAllBlock();
		assertEquals(1, map.getDiamond());
	}
	@Test
	public void testPushRock() {
		controllable.executeOrder(MovementOrder.GoRight);
		if(!(map.getBlockAt(2, 1) instanceof Rock) || (map.getBlockAt(3, 1) instanceof Rock)) {
			fail();
		}
		map.updateAllBlock();
		if((map.getBlockAt(2, 1) instanceof Rock) || !(map.getBlockAt(3, 1) instanceof Rock)) {
			fail();
		}
	}
}
