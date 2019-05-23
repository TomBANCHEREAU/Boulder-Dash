package com.TomBAN.BoulderDash.Controller;

import javax.swing.JFrame;

import com.TomBAN.BoulderDash.Frame.SimplyPanel;
import com.TomBAN.BoulderDash.Model.BoulderDashModel;
import com.TomBAN.BoulderDash.Model.Map;
import com.TomBAN.BoulderDash.PlayerController.KeyBoardController;
import com.TomBAN.BoulderDash.PlayerController.ZQSDKeyBoardController;
import com.TomBAN.BoulderDash.View.BoulderDashGraphicsBuilder;

public class BoulderDashController {
	private static final String URL = "jdbc:mysql://localhost:3306/a1-project5?useSSL=false&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	private BoulderDashModel model;
	private JFrame frame;
	public BoulderDashController(JFrame frame,GameMode gameMode) {
		this.frame = frame;
		//loadMap(0);
		switch (gameMode) {
		case SinglePlayer:
			model = new BoulderDashModel(loadMap(0));
			KeyBoardController controller = new ZQSDKeyBoardController();
			controller.setControllable(model.getPlayers().get(0));
			frame.addKeyListener(controller);
			frame.setContentPane(new SimplyPanel(new BoulderDashGraphicsBuilder(model,model.getPlayers().get(0))));
			while (true) {
				model.gameLoop();
				frame.repaint();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//break;
		case SingleCoop:
			
			break;
		case MultiPlayer:
			
			break;
		case MultiCoop:
			
			break;
		}
		// TODO Auto-generated constructor stub
	}
	public Map loadMap(int id) {
//		try {
//			MySQL.Connect(URL, USER, PASSWORD);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		//TODO 
		return new Map(100, 24, "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" + 
				"@                                                                                                                              @\n" + 
				"@          ###########O################                                                                                        @\n" + 
				"@                     #                                                                                                        @\n" + 
				"@          ## ######## ################                                                                                        @\n" + 
				"@           # #      # #                                                                                               O       @\n" + 
				"@           # #      OVO                                                                                              O#       @\n" + 
				"@           # #@@@@@@@@@@@@@@@        @ O   #  O@         @      @@@@@@@@@@@@@             @@@@@       @@@@@@@@@@@    # #      @\n" + 
				"@           # #       @               @ #    O##@  O    O @            @                @@@            @              # #      @\n" + 
				"@        O  # #       @               @   #  O# @  #  O # @            @               @ O             @              # #      @\n" + 
				"@       ###O# #       @                @#   O# @A@  O #  @             @              @ O#             @              # #      @\n" + 
				"@       # ##  #       @                @  OO#  @ @  #   O@             @              @¤#O             @@@@@@@@       # #      @\n" + 
				"@       # #   #       @                 @ ##  @   @    #@              @              @ O              @              # #      @\n" + 
				"@       # #   #       @                  @ O @     @ O#@               @               @#              @              # #      @\n" + 
				"@       # #   #       @                   @V@       @V@                @                @@@            @              # #      @\n" + 
				"@       # #   #       @                    @         @           @@@@@@@@@@@@@             @@@@@       @@@@@@@@@@@    # #      @\n" + 
				"@       # #   ##############################                                                                          # #      @\n" + 
				"@       # #                                           O                                                               # #      @\n" + 
				"@       # ############################################################################################################# #      @\n" + 
				"@       #                                                                                                               #      @\n" + 
				"@       #################################################################################################################     O@\n" + 
				"@                           O             O                O                                                                  #@\n" + 
				"@                           #             #       O        #                                                                 OV@\n" + 
				"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	}
	
	
	
	
	
}
