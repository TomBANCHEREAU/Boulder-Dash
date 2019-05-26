package com.TomBAN.BoulderDash.Main;

import javax.swing.JFrame;

import com.TomBAN.BoulderDash.Frame.BoulderDashFrame;
import com.TomBAN.BoulderDash.Game.Controller.BoulderDashController;
import com.TomBAN.BoulderDash.Game.Controller.GameMode;
import com.TomBAN.BoulderDash.Game.Controller.GameOption;
import com.TomBAN.BoulderDash.Ressource.RessourceManager;
import com.TomBAN.BoulderDash.StartMenu.Controller.StartMenuController;

public class BoulderDash {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("안녕");
		RessourceManager.getInstance().loadImages("Default");
		RessourceManager.getInstance().loadLanguageList();
		JFrame frame = new BoulderDashFrame(1280, 720);
		GameOption gameOption = StartMenuController.getGameOption(frame);
		//System.out.println("qsd");
		new BoulderDashController(frame, gameOption);
		//BoulderDashController controller = new BoulderDashController(new BoulderDashFrame(1920,1080),GameMode.SingleCoop);
	}

}
