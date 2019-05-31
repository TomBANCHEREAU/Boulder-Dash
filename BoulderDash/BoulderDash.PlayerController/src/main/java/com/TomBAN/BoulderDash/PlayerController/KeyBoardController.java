package com.TomBAN.BoulderDash.PlayerController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.TomBAN.BoulderDash.Game.Model.Controllable;
import com.TomBAN.BoulderDash.Game.Model.ControllableController;
import com.TomBAN.BoulderDash.Game.Model.MovementOrder;

/**
 * @author Tom BANCHEREAU
 *
 *
**/
public class KeyBoardController implements KeyListener, ControllableController {
	private int keyUp, keyLeft, keyDown, keyRight, keyReset;
	private int lastKeyPressed;
	private Controllable controllable;

	/**
	 * @param keyUp
	 * @param keyLeft
	 * @param keyDown
	 * @param keyRight
	 * @param keyReset
	 */
	public KeyBoardController(int keyUp, int keyLeft, int keyDown, int keyRight, int keyReset) {
		this.keyUp = keyUp;
		this.keyLeft = keyLeft;
		this.keyDown = keyDown;
		this.keyRight = keyRight;
		this.keyReset=keyReset;
	}

	/**
	 * @param controllable
	 */
	@Override
	public void bindControllable(Controllable controllable) {
		this.controllable = controllable;
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		if (controllable != null) {
			if (e.getKeyCode() == keyUp) {
				controllable.executeOrder(MovementOrder.GoUp);
				lastKeyPressed = e.getKeyCode();
			}
			if (e.getKeyCode() == keyLeft) {
				controllable.executeOrder(MovementOrder.GoLeft);
				lastKeyPressed = e.getKeyCode();
			}
			if (e.getKeyCode() == keyDown) {
				controllable.executeOrder(MovementOrder.GoDown);
				lastKeyPressed = e.getKeyCode();
			}
			if (e.getKeyCode() == keyRight) {
				controllable.executeOrder(MovementOrder.GoRight);
				lastKeyPressed = e.getKeyCode();
			}
			if (e.getKeyCode() == keyReset) {
				controllable.executeOrder(MovementOrder.Reset);
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == lastKeyPressed) {
			controllable.executeOrder(MovementOrder.StopMovement);
		}
	}

}
