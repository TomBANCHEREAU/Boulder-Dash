package com.TomBAN.BoulderDash.Controller;

import javax.swing.JFrame;

import com.TomBAN.BoulderDash.Model.BoulderDashModel;
import com.TomBAN.BoulderDash.Model.Map;
import com.TomBAN.BoulderDash.Model.StringMap;
import com.TomBAN.BoulderDash.PlayerController.ArroKeyBoardController;
import com.TomBAN.BoulderDash.PlayerController.IJKLKeyBoardController;
import com.TomBAN.BoulderDash.PlayerController.KeyBoardController;
import com.TomBAN.BoulderDash.PlayerController.NumPKeyBoardController;
import com.TomBAN.BoulderDash.PlayerController.ZQSDKeyBoardController;

public class BoulderDashController {
	private static final String URL = "jdbc:mysql://localhost:3306/a1-project5?useSSL=false&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	private BoulderDashModel[] model;
	private KeyBoardController[] controllers;
	private final JFrame frame;
	private final GameOption gameOption;

	public BoulderDashController(JFrame frame, GameOption gameOption) {
		this.frame = frame;
		this.gameOption = gameOption;
		this.model = new BoulderDashModel[gameOption.getModelNumber()];

//		switch (gameOption.getGameMode()) {
//		case SinglePlayer:
//			model = new BoulderDashModel(loadMap(0));
//			controllers = new KeyBoardController[1];
//			controllers[0] = new ZQSDKeyBoardController();
//			controllers[0].setControllable(model.getPlayers().get(0));
//			frame.addKeyListener(controllers[0]);
//			frame.setContentPane(new SimplyPanel(new BoulderDashGraphicsBuilder(model,model.getPlayers().get(0))));
//			while (!model.isFinished()) {
//				model.gameLoop();
//				frame.repaint();
//				try {
//					Thread.sleep(50);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//			break;
//		case MultiCoop:
//			model = new BoulderDashModel(loadMap(0));
//			controllers = new KeyBoardController[4];
//			controllers[0] = new ZQSDKeyBoardController();
//			controllers[1] = new IJKLKeyBoardController();
//			controllers[2] = new NumPKeyBoardController();
//			controllers[3] = new ArroKeyBoardController();
//			for (int i = 0; i < controllers.length; i++) {
//				controllers[i].setControllable(model.getPlayers().get(i));
//				frame.addKeyListener(controllers[i]);
//			}
//			frame.setContentPane(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
//					new JSplitPane(JSplitPane.VERTICAL_SPLIT, new SimplyPanel(new BoulderDashGraphicsBuilder(model,model.getPlayers().get(0))),new SimplyPanel(new BoulderDashGraphicsBuilder(model,model.getPlayers().get(1)))),
//					new JSplitPane(JSplitPane.VERTICAL_SPLIT, new SimplyPanel(new BoulderDashGraphicsBuilder(model,model.getPlayers().get(2))),new SimplyPanel(new BoulderDashGraphicsBuilder(model,model.getPlayers().get(3))))));
//			while (!model.isFinished()) {
//				model.gameLoop();
//				frame.repaint();
//				try {
//					Thread.sleep(50);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//			break;
//		case MultiPlayerRace:
//			
//			break;
//		case MultiCoopRace:
//			
//			break;
//		}
		// TODO Auto-generated constructor stub
	}

	private void setUpControllers() {
		this.controllers = new KeyBoardController[gameOption.getPlayerNumber()];
		switch (gameOption.getPlayerNumber()) {
		case 4:controllers[3] = new NumPKeyBoardController();
		case 3:controllers[2] = new IJKLKeyBoardController();
		case 2:controllers[1] = new ArroKeyBoardController();
		case 1:controllers[0] = new ZQSDKeyBoardController();
		}
	}

	public StringMap getStringMap(int id) {
//		try {
//			MySQL.Connect(URL, USER, PASSWORD);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		// TODO

		return new StringMap(76, 9, 2,0,
				"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
						+ "@######O########@###O   ##O O  ##@O@O@@@@@@@V########### O####O#########O#V@\n"
						+ "@#A####V########@###@## #O# #@ @###@##VVVVVV@@@@@@@@@@@@ ######   O#####O#V@\n"
						+ "@###################@########@V@##V@#@@@@@@@@O#####O###@ ###O##O##V#####O#V@\n"
						+ "@@@@@@@ @@@@@@@@@@@@@@@@@@@@@@@@#@@@#@##O#########OV#### #####O#  ######V##@\n"
						+ "@O      ######@#####OV@##    V#@#####@  #         #####@O#####V  # ##### #O@\n"
						+ "@# ####O#@@@@@@#######@##     #@@@@@@@ # ####V####O ########O##O##V##### O#@\n"
						+ "@VO####O#######################O        VO########OVO##@O############### ¤#@\n"
						+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

	}

}
