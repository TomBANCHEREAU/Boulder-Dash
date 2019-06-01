package com.TomBAN.BoulderDash.PlayerController;

import java.awt.event.KeyEvent;

/**
 * @author TomBANCHEREAU
 * This class is a specialization of the KeyBoardController class with preselected controls
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
