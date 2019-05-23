package com.TomBAN.BoulderDash.PlayerController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.TomBAN.BoulderDash.Model.Controllable;
import com.TomBAN.BoulderDash.Model.MovementOrder;

public class KeyBoardController implements KeyListener {
	private int keyUp, keyLeft, keyDown, keyRight;
	private int lastKeyPressed;
	private Controllable controllable;

	public KeyBoardController(int keyUp, int keyLeft, int keyDown, int keyRight) {
		this.keyUp = keyUp;
		this.keyLeft = keyLeft;
		this.keyDown = keyDown;
		this.keyRight = keyRight;
	}
	public void setControllable(Controllable controllable) {
		this.controllable = controllable;
	}
	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == keyUp) {
			controllable.executeOrder(MovementOrder.GoUp);
			lastKeyPressed = e.getKeyCode();
		}
		if(e.getKeyCode() == keyLeft) {
			controllable.executeOrder(MovementOrder.GoLeft);
			lastKeyPressed = e.getKeyCode();
		}
		if(e.getKeyCode() == keyDown) {
			controllable.executeOrder(MovementOrder.GoDown);
			lastKeyPressed = e.getKeyCode();
		}
		if(e.getKeyCode() == keyRight) {
			controllable.executeOrder(MovementOrder.GoRight);
			lastKeyPressed = e.getKeyCode();
		}
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == lastKeyPressed) {
			 controllable.executeOrder(MovementOrder.StopMovement);
		}
	}

}
