package com.TomBAN.BoulderDash.Game.Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;

import com.TomBAN.BoulderDash.Frame.BoulderDashFrame;
import com.TomBAN.BoulderDash.Frame.SimplyPanel;
import com.TomBAN.BoulderDash.Game.Model.BoulderDashModel;
import com.TomBAN.BoulderDash.Game.Model.ModelStatut;
import com.TomBAN.BoulderDash.Game.Model.NewHighScoreListenner;
import com.TomBAN.BoulderDash.Game.Model.Score;
import com.TomBAN.BoulderDash.Game.Model.StringMap;
import com.TomBAN.BoulderDash.Game.View.BoulderDashGraphicsBuilder;
import com.TomBAN.BoulderDash.PlayerController.ArroKeyBoardController;
import com.TomBAN.BoulderDash.PlayerController.IJKLKeyBoardController;
import com.TomBAN.BoulderDash.PlayerController.KeyBoardController;
import com.TomBAN.BoulderDash.PlayerController.NumPKeyBoardController;
import com.TomBAN.BoulderDash.PlayerController.ZQSDKeyBoardController;
import com.TomBAN.BoulderDash.Ressource.RessourceManager;
import com.TomBAN.mySQL.MySQL;

public class BoulderDashController implements Observer, NewHighScoreListenner {
	private static final String URL = "jdbc:mysql://localhost:3306/a1-project5?useSSL=false&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	private BoulderDashModel[] models;
	private KeyBoardController[] controllers;
	private final JFrame[] frames;
	private final ArrayList<StringMap> strMap;
	private final GameOption gameOption;
	private int NextMapNumber = 0;

	public BoulderDashController(JFrame frame, GameOption gameOption) {
		this.frames = new BoulderDashFrame[(gameOption.isDualScreen()) ? 2 : 1];
		frames[0] = frame;
		if (gameOption.isDualScreen()) {
			frames[1] = new BoulderDashFrame(1280, 720);
		}
		this.gameOption = gameOption;

		setUpControllers();
		bindControllersToFrames();
		this.models = new BoulderDashModel[gameOption.getModelNumber()];
		for (int i = 0; i < models.length; i++) {
			models[i] = new BoulderDashModel(this, 5,
					JOptionPane.showInputDialog(RessourceManager.getInstance().getText("Name" + i)),this);
			for (int j = i * gameOption.getPlayerNumberPerMap(); j < (i + 1)
					* gameOption.getPlayerNumberPerMap(); j++) {
				models[i].addController(controllers[j]);
			}
		}

		final SimplyPanel[] panels = new SimplyPanel[gameOption.getPlayerNumber()];
		for (int i = 0; i < panels.length; i++) {
			panels[i] = new SimplyPanel(new BoulderDashGraphicsBuilder(models[i / gameOption.getPlayerNumberPerMap()],
					i % gameOption.getPlayerNumberPerMap()));
		}

		if (gameOption.getPlayerNumber() == 1) {
			panels[0].setSize(frames[0].getContentPane().getWidth(), frames[0].getContentPane().getHeight());
			frames[0].setContentPane(panels[0]);
		} else if (gameOption.getPlayerNumber() == 2) {
			if (!gameOption.isDualScreen()) {
				final JSplitPane splitedScreen = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
				splitedScreen.setSize(frames[0].getContentPane().getWidth(), frames[0].getContentPane().getHeight());
				frames[0].setContentPane(splitedScreen);
				splitedScreen.setDividerLocation(0.5);
				splitedScreen.setTopComponent(panels[0]);
				splitedScreen.setBottomComponent(panels[1]);
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
				final JSplitPane splitedsplitedScreen = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitedScreen[0],
						splitedScreen[1]);
				splitedsplitedScreen.setSize(frames[0].getContentPane().getWidth(),
						frames[0].getContentPane().getHeight());
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

		strMap = loadMapList(gameOption.getPlayerNumberPerMap());
		nextMap();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (AllWaitingForNextMap()) {
			if (AllLoose()) {
				endScreen();
			} else {
				nextMap();
			}
		}
	}

	private void nextMap() {
		if (NextMapNumber < strMap.size()) {
			for (BoulderDashModel model : models) {
				switch (strMap.get(NextMapNumber).getWorld()) {
				case 1:
					RessourceManager.getInstance().loadImages("Default");
					break;
				case 2:
					RessourceManager.getInstance().loadImages("World2");
					break;

				default:
					break;
				}
				model.nextMap(strMap.get(NextMapNumber));
			}
			// TODO
			NextMapNumber++;
		} else {
			endScreen();
		}
	}

	private void endScreen() {
		// TODO Auto-generated method stub
		System.out.println("endScreen");
	}

	@Override
	public synchronized void NewHighScoreEvent(Score score) {
		if (score != null) {
			if(score.isNew()==true) {

				try {
					MySQL.Connect(URL, USER, PASSWORD);
					MySQL.getInstance().queryUpdate("call addScore(" + score.getStringMap().getMapID()+ ","
							+ score.getScore() + ",'" + score.getName() + "')");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frames[0], e1.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
				}finally {
					MySQL.closeConnection();
				}
			}
		}
	}

	private ArrayList<StringMap> loadMapList(int playerPerMap) {
		ArrayList<StringMap> out = new ArrayList<StringMap>();
		try {
			MySQL.Connect(URL, USER, PASSWORD);
			ResultSet result = MySQL.getInstance().querySelect("call getMapListFromPlayerNumber(" + playerPerMap + ")");
			while (result.next()) {

				ResultSet highScore = MySQL.getInstance()
						.querySelect("call getHighScore(" + result.getInt("MapID") + ")");
				ArrayList<Score> hs = new ArrayList<Score>();
				while (highScore.next()) {
					hs.add(new Score(highScore.getInt("Score"), highScore.getString("PlayerName")));
				}
				Score[] sharray = new Score[10];
				for (int i = 0; i < hs.size(); i++) {
					sharray[i] = hs.get(i);
				}

				out.add(new StringMap(result.getInt("Width"), result.getInt("Height"), result.getInt("DiamondsNeeded"),
						result.getInt("PlayerNumber"), result.getString("Content"), result.getInt("WorldNumber"),
						result.getInt("LevelNumber"), sharray, result.getInt("TimeToFinish"), result.getInt("MapID")));
			}
			return out;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(frames[0], e.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		} finally {
			MySQL.closeConnection();
		}
		return null;
	}

	private boolean AllWaitingForNextMap() {
		for (BoulderDashModel model : models) {
			if (model.getModelStatut() != ModelStatut.WaitingNextMap && !model.loose()) {
				return false;
			}
		}
		return true;
	}

	private boolean AllLoose() {
		for (BoulderDashModel model : models) {
			if (!model.loose()) {
				return false;
			}
		}
		return true;
	}

	private void bindControllersToFrames() {
		for (JFrame frame : frames) {
			for (KeyBoardController controller : controllers) {
				frame.addKeyListener(controller);
			}
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

}
