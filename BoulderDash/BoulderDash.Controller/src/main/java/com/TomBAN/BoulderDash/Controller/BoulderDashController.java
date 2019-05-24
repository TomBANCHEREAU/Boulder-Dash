package com.TomBAN.BoulderDash.Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import com.TomBAN.BoulderDash.Frame.BoulderDashFrame;
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
import com.TomBAN.mySQL.MySQL;

public class BoulderDashController {
	private static final String URL = "jdbc:mysql://localhost:3306/a1-project5?useSSL=false&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	private BoulderDashModel[] models;
	private KeyBoardController[] controllers;
	private final BoulderDashFrame[] frames;
	private final GameOption gameOption;

	public BoulderDashController(BoulderDashFrame frame, GameOption gameOption) {
		this.frames = new BoulderDashFrame[(gameOption.isDualScreen()) ? 2 : 1];
		frames[0] = frame;
		if (gameOption.isDualScreen()) {
			frames[1] = new BoulderDashFrame(1280,720);
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
			panels[0].setSize(frames[0].getContentPane().getWidth(), frames[0].getContentPane().getHeight());
			frames[0].setContentPane(panels[0]);
		} else if (gameOption.getPlayerNumber() == 2) {
			if (!gameOption.isDualScreen()) {
				final JSplitPane splitedScreen = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
				splitedScreen.setSize(frames[0].getContentPane().getWidth(), frames[0].getContentPane().getHeight());
				System.out.println(splitedScreen.getSize());
				frames[0].setContentPane(splitedScreen);
				splitedScreen.setDividerLocation(0.5);
//				splitedScreen.setDividerLocation(0.5);
//				//, panels[0], panels[1]
				splitedScreen.setTopComponent(panels[0]);
				splitedScreen.setBottomComponent(panels[1]);
//				frame.pack();
//				splitedScreen.setDividerSize(0);
			} else {
				panels[0].setSize(frames[0].getContentPane().getWidth(), frames[0].getContentPane().getHeight());
				panels[1].setSize(frames[1].getContentPane().getWidth(), frames[1].getContentPane().getHeight());
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
				splitedsplitedScreen.setSize(frames[0].getContentPane().getWidth(), frames[0].getContentPane().getHeight());
				frames[0].setContentPane(splitedsplitedScreen);
				splitedsplitedScreen.setDividerLocation(0.5);

				splitedScreen[0].setDividerLocation(0.5);
				splitedScreen[1].setDividerLocation(0.5);
			} else {
				splitedScreen[0].setSize(frames[0].getContentPane().getWidth(), frames[0].getContentPane().getHeight());
				splitedScreen[1].setSize(frames[0].getContentPane().getWidth(), frames[0].getContentPane().getHeight());
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
		try {
			MySQL.Connect(URL, USER, PASSWORD);
			ResultSet result = MySQL.getInstance().querySelect("call getMapFromId("+1+")");
			result.next();
			System.out.println(result.getString("Content"));
			return new StringMap(result.getInt("Width"), result.getInt("Height"), result.getInt("DiamondsNeeded"), result.getInt("PlayerNumber"), result.getString("Content"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		// TODO
//		return new StringMap(128, 24, 2, 2,
//				"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" + 
//				"@O#######################################################################################        @ O    O  O   O    O O   OOOOO@\n" + 
//				"@          ###########O##########################  #######O####### M#####################        @ # O  # O# O # O #OO#   #####@\n" + 
//				"@                     #                ########## M###############  #####################            #   O   #O  #  ##       @V@\n" + 
//				"@V         ## ######## ##################################################################        @  O   O#   O#    O     ####O#@\n" + 
//				"@           # ######OO OO                                                                        @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" + 
//				"@           # #####OOOVOOO                                                                                             O      O@\n" + 
//				"@           # #@@@@@@@@@@@@@@@        @ O   #  O@         @      @@@@@@@@@@@@@             @@@@@       @@@@@@@@@@@    O##     #@\n" + 
//				"@           # #       @               @ #   #O##@  O    O @      OOOOOO@OOOOOO          @@@            @      O       # #      @\n" + 
//				"@        O  # #       @   O#O#O#O#    @   #  O# @  # OO # @      ######@######         @ O             @C #####       # #      @\n" + 
//				"@       ###O# #       @   #O#O#O#O     @#   O# @ @  O##  @       OOOOOO@OOOOOO        @ O#             @####          # #      @\n" + 
//				"@       # ##  #       @   O#O#O#O#     @  OO#  @ @  #   O@       ######@######        @¤#O             @@@@@@@@       #L#      @\n" + 
//				"@       # #   #       @   #O#OVO#O      @ ##  @   @  O #@        OOOOOO@OOOOOO        @ O#             @OO            # #      @\n" + 
//				"@       # #   #       @   O#O#O#O#       @ O @     @#O#@         #####O@######         @#              @VP            # #      @\n" + 
//				"@       # #   #       @   #O#O#O#O        @V@       @V@  OO   OO ###OPV@COOOOO          @@@            @OO            # #      @\n" + 
//				"@       # #   #       @   O#O#O#O#         @         @###OO###OO#@@@@@@@@@@@@@   ####      @@@@@       @@@@@@@@@@@    # #      @\n" + 
//				"@       # #   ##############################          ###########                # M#                                 # #      @\n" + 
//				"@       # ######################                      O                          ####                                 # #     #@\n" + 
//				"@       #    L                 ############################################################O########################### #    OV@\n" + 
//				"@       ###################### ###O############O######O#########O#######   ###OO#O###O##  ####O#####################@@@@@@@@@@@@\n" + 
//				"@           ###################################O################O##O##### ##O####O#  O##O##############O@@@@@@@@@@@@@####      @\n" + 
//				"@ F  A  F   ################O #####       O   OO    ###    O ##############O##  ############O#######   ##################     #@\n" + 
//				"@           ########################  O  # ####   O        # ######      ############     ####O#####   #               L#    OV@\n" + 
//				"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

	}

}
