package com.TomBAN.BoulderDash.PlayerController;

import java.awt.event.KeyEvent;
/**
 * This class is a specialization of the KeyBoardController class with preselected controls
 * @author TomBANCHEREAU
 */
public class NumPKeyBoardController extends KeyBoardController {
	/**
	 * Create a new NumPKeyBoardController, this constructor is an equivalent of the KeyBoardController constructor :
	 * KeyBoardController(KeyEvent.VK_NUMPAD8, KeyEvent.VK_NUMPAD4, KeyEvent.VK_NUMPAD5, KeyEvent.VK_NUMPAD6,KeyEvent.VK_NUMPAD9);
	 */
	public NumPKeyBoardController() {
		super(KeyEvent.VK_NUMPAD8, KeyEvent.VK_NUMPAD4, KeyEvent.VK_NUMPAD5, KeyEvent.VK_NUMPAD6,KeyEvent.VK_NUMPAD9);
	}

}
