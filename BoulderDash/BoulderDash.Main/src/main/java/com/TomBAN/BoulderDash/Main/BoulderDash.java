package com.TomBAN.BoulderDash.Main;

import javax.swing.JFrame;

import com.TomBAN.BoulderDash.Frame.BoulderDashFrame;
import com.TomBAN.BoulderDash.Game.Controller.BoulderDashController;
import com.TomBAN.BoulderDash.Game.Controller.GameOption;
import com.TomBAN.BoulderDash.Ressource.RessourceManager;
import com.TomBAN.BoulderDash.StartMenu.Controller.StartMenuController;

/**
 * @author TomBANCHEREAU
 *	This class is just... the main.
 */
public class BoulderDash {

	/**
	 * With a main methods
	 * @param args
	 */
	public static void main(String[] args) {
		RessourceManager.getInstance().loadLanguageList();
		JFrame frame = new BoulderDashFrame(1280, 720);
		GameOption gameOption = StartMenuController.getGameOption(frame);
		new BoulderDashController(frame, gameOption);
	}
}
