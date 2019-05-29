package com.TomBAN.BoulderDash.PlayerController;

import static org.junit.Assert.*;

import java.awt.Component;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.TomBAN.BoulderDash.Game.Model.Controllable;
import com.TomBAN.BoulderDash.Game.Model.MovementOrder;

public class KeyBoardControllerTest {
	private KeyBoardController keyBoardController;
	private MovementOrder expected;
	private Component source;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		keyBoardController = new KeyBoardController(KeyEvent.VK_Z,KeyEvent.VK_Q , KeyEvent.VK_S,KeyEvent.VK_D, KeyEvent.VK_E);
		Controllable controllable = new Controllable() {
			@Override
			public void executeOrder(MovementOrder order) {
				if(expected!=order) {
					fail(expected+"!="+order);
				}
			}
		};
		keyBoardController.bindControllable(controllable);
		source = new JFrame();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUpToStop() {
		expected = MovementOrder.GoUp;
		keyBoardController.keyPressed(new KeyEvent(source, 0, 0, 0, KeyEvent.VK_Z, 'Z'));
		expected = MovementOrder.StopMovement;
		keyBoardController.keyReleased(new KeyEvent(source, 0, 0, 0, KeyEvent.VK_Z, 'Z'));
	}
	@Test
	public void testDownToLeftToStop() {
		expected = MovementOrder.GoDown;
		keyBoardController.keyPressed(new KeyEvent(source, 0, 0, 0, KeyEvent.VK_S, 'S'));
		expected = MovementOrder.GoLeft;
		keyBoardController.keyPressed(new KeyEvent(source, 0, 0, 0, KeyEvent.VK_Q, 'Q'));
		expected = MovementOrder.GoLeft;
		keyBoardController.keyReleased(new KeyEvent(source, 0, 0, 0, KeyEvent.VK_S, 'S'));
		expected = MovementOrder.StopMovement;
		keyBoardController.keyReleased(new KeyEvent(source, 0, 0, 0, KeyEvent.VK_Q, 'Q'));
	}
	@Test
	public void testReset() {
		expected = MovementOrder.Reset;
		keyBoardController.keyPressed(new KeyEvent(source, 0, 0, 0, KeyEvent.VK_E, 'E'));
	}
}


















