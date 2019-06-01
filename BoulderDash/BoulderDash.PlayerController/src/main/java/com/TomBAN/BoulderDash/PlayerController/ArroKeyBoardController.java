package com.TomBAN.BoulderDash.PlayerController;

import java.awt.event.KeyEvent;

/**
 * @author TomBANCHEREAU This class is a specialization of the
 *         KeyBoardController class with preselected controls
 */
public class ArroKeyBoardController extends KeyBoardController {
	/**
	 * Create a new ArroKeyBoardController, this constructor is an equivalent of the KeyBoardController constructor :
	 * KeyBoardController(KeyEvent.VK_UP, KeyEvent.VK_LEFT, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT,KeyEvent.VK_NUMPAD0);
	 */
	public ArroKeyBoardController() {
		super(KeyEvent.VK_UP, KeyEvent.VK_LEFT, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT,KeyEvent.VK_NUMPAD0);
		// TODO Auto-generated constructor stub
	}

}
