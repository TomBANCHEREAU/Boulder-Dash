package com.TomBAN.BoulderDash.PlayerController;

import java.awt.event.KeyEvent;

/**
 * This class is a specialization of the KeyBoardController class with preselected controls
 * @author TomBANCHEREAU
 */
public class ZQSDKeyBoardController extends KeyBoardController {
	/**
	 * Create a new ZQSDKeyBoardController, this constructor is an equivalent of the KeyBoardController constructor :
	 * KeyBoardController(KeyEvent.VK_Z, KeyEvent.VK_Q, KeyEvent.VK_S, KeyEvent.VK_D,KeyEvent.VK_E);
	 */
	public ZQSDKeyBoardController() {
		super(KeyEvent.VK_Z, KeyEvent.VK_Q, KeyEvent.VK_S, KeyEvent.VK_D,KeyEvent.VK_E);
	}
}
