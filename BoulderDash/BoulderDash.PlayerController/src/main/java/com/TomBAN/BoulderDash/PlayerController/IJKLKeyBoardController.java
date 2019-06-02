package com.TomBAN.BoulderDash.PlayerController;

import java.awt.event.KeyEvent;

/**
 * This class is a specialization of the KeyBoardController class with preselected controls
 * @author TomBANCHEREAU 
 */
public class IJKLKeyBoardController extends KeyBoardController {
	/**
	 * Create a new IJKLKeyBoardController, this constructor is an equivalent of the KeyBoardController constructor :
	 * KeyBoardController(KeyEvent.VK_I, KeyEvent.VK_J, KeyEvent.VK_K, KeyEvent.VK_L, KeyEvent.VK_O);
	 */
	public IJKLKeyBoardController() {
		super(KeyEvent.VK_I, KeyEvent.VK_J, KeyEvent.VK_K, KeyEvent.VK_L, KeyEvent.VK_O);
	}
}
