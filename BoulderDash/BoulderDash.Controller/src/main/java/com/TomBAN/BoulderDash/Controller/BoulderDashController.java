package com.TomBAN.BoulderDash.Controller;

import java.awt.Container;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import com.TomBAN.BoulderDash.Frame.SimplyPanel;
import com.TomBAN.BoulderDash.Model.BoulderDashModel;
import com.TomBAN.BoulderDash.Model.StringMap;
import com.TomBAN.BoulderDash.Model.BlockList.Player;
import com.TomBAN.BoulderDash.PlayerController.ArroKeyBoardController;
import com.TomBAN.BoulderDash.PlayerController.IJKLKeyBoardController;
import com.TomBAN.BoulderDash.PlayerController.KeyBoardController;
import com.TomBAN.BoulderDash.PlayerController.NumPKeyBoardController;
import com.TomBAN.BoulderDash.PlayerController.ZQSDKeyBoardController;
import com.TomBAN.BoulderDash.View.BoulderDashGraphicsBuilder;

public class BoulderDashController {
	private static final String URL = "jdbc:mysql://localhost:3306/a1-project5?useSSL=false&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	private BoulderDashModel[] models;
	private KeyBoardController[] controllers;
	private final JFrame[] frames;
	private final GameOption gameOption;

	public BoulderDashController(JFrame frame, GameOption gameOption) {
		this.frames = new JFrame[(gameOption.isDualScreen()) ? 2 : 1];
		frames[0] = frame;
		if (gameOption.isDualScreen()) {
			frames[1] = new JFrame();
		}
		this.gameOption = gameOption;

		this.models = new BoulderDashModel[gameOption.getModelNumber()];
		final StringMap srtMap = getStringMap(0);
		for (int i = 0; i < models.length; i++) {
			models[i] = new BoulderDashModel(srtMap.toRealMap());
		}

		setUpControllers();
		bindPlayerToControllers();
		bindControllersToFrames();

		final SimplyPanel[] panels = new SimplyPanel[gameOption.getPlayerNumber()];
		final ArrayList<Player> players = getAllPlayers();

		for (int i = 0; i < panels.length; i++) {
			panels[i] = new SimplyPanel(new BoulderDashGraphicsBuilder(
					models[i * gameOption.getModelNumber() / gameOption.getPlayerNumber()], players.get(i)));
		}

		if (gameOption.getPlayerNumber() == 1) {
			frames[0].setContentPane(panels[0]);
		} else if (gameOption.getPlayerNumber() == 2) {
			if (!gameOption.isDualScreen()) {
				final JSplitPane splitedScreen = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panels[0], panels[1]);
				splitedScreen.setDividerLocation(0.5);
//				splitedScreen.setDividerSize(0);
				frames[0].setContentPane(splitedScreen);
				splitedScreen.setDividerLocation(0.5);
			} else {
				frames[0].setContentPane(panels[0]);
				frames[1].setContentPane(panels[1]);
			}
		} else {
			final JSplitPane[] splitedScreen = { new JSplitPane(JSplitPane.VERTICAL_SPLIT, panels[0], panels[1]),
					new JSplitPane(JSplitPane.VERTICAL_SPLIT, panels[2], panels[3]) };

			splitedScreen[0].setDividerLocation(0.5);
			splitedScreen[1].setDividerLocation(0.5);
			
			if (!gameOption.isDualScreen()) {
				final JSplitPane splitedsplitedScreen = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitedScreen[0], splitedScreen[1]);
				frames[0].setContentPane(splitedsplitedScreen);
				splitedsplitedScreen.setDividerLocation(0.5);

				splitedScreen[0].setDividerLocation(0.5);
				splitedScreen[1].setDividerLocation(0.5);
			} else {
				frames[0].setContentPane(splitedScreen[0]);
				frames[1].setContentPane(splitedScreen[1]);

				splitedScreen[0].setDividerLocation(0.5);
				splitedScreen[1].setDividerLocation(0.5);
			}
		}

		for (BoulderDashModel model : models) {
			model.start();
		}
		for (JFrame framse : frames) {
			framse.repaint();
		}
		// TODO Auto-generated constructor stub
	}

	private void bindControllersToFrames() {
		for (JFrame frame : frames) {
			for (KeyBoardController controller : controllers) {
				frame.addKeyListener(controller);
			}
		}
	}

	private ArrayList<Player> getAllPlayers() {
		final ArrayList<Player> players = new ArrayList<Player>();
		for (int i = 0; i < models.length; i++) {
			players.addAll(models[i].getPlayers());
		}
		return players;
	}

	private void bindPlayerToControllers() {
		final ArrayList<Player> players = getAllPlayers();
		for (int i = 0; i < controllers.length; i++) {
			controllers[i].bindControllable(players.get(i));
		}
	}

	private void setUpControllers() {
		this.controllers = new KeyBoardController[gameOption.getPlayerNumber()];
		switch (gameOption.getPlayerNumber()) {
		case 4:
			controllers[3] = new NumPKeyBoardController();
		case 3:
			controllers[2] = new IJKLKeyBoardController();
		case 2:
			controllers[1] = new ArroKeyBoardController();
		case 1:
			controllers[0] = new ZQSDKeyBoardController();
		}
	}

	public StringMap getStringMap(int id) {
//		try {
//			MySQL.Connect(URL, USER, PASSWORD);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		// TODO
		return new StringMap(76, 9, 2, 2,
				"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
						+ "@######O########@###O   ##O O  ##@O@O@@@@@@@V########### O####O#########O#V@\n"
						+ "@#A####V########@###@## #O# #@ @###@##VVVVVV@@@@@@@@@@@@ ######   O#####O#V@\n"
						+ "@###################@########@V@##V@#@@@@@@@@O#####O###@ ###O##O##V#####O#V@\n"
						+ "@@@@@@@ @@@@@@@@@@@@@@@@@@@@@@@@#@@@#@##O#########OV#### #####O#  ######V##@\n"
						+ "@O      ######@#####OV@##    V#@#####@  #   A  A  #####@O#####V  # ##### #O@\n"
						+ "@# ####O#@@@@@@#######@##     #@@@@@@@ # ####V####O ########O##O##V##### O#@\n"
						+ "@VO####O#######################O     A  VO########OVO##@O############### ¤#@\n"
						+ "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

	}

}
