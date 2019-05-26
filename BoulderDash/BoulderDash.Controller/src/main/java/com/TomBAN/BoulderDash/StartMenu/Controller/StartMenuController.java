package com.TomBAN.BoulderDash.StartMenu.Controller;

import javax.swing.JFrame;

import com.TomBAN.BoulderDash.Frame.SimplyPanel;
import com.TomBAN.BoulderDash.Game.Controller.GameMode;
import com.TomBAN.BoulderDash.Game.Controller.GameOption;
import com.TomBAN.BoulderDash.Game.Model.Controllable;
import com.TomBAN.BoulderDash.Game.Model.MovementOrder;
import com.TomBAN.BoulderDash.PlayerController.KeyBoardController;
import com.TomBAN.BoulderDash.PlayerController.ZQSDKeyBoardController;
import com.TomBAN.BoulderDash.Ressource.RessourceManager;
import com.TomBAN.BoulderDash.StartMenu.Model.LeftRightSelector;
import com.TomBAN.BoulderDash.StartMenu.Model.StartMenuModel;
import com.TomBAN.BoulderDash.StartMenu.View.StartMenuGraphicsBuilder;

public class StartMenuController implements Controllable {
	private static GameOption gameOption; 
	private Object object;
	private KeyBoardController controller;
	private StartMenuModel model;

	public StartMenuController(JFrame frame, Object object) {
		this.object = object;
		model = new StartMenuModel();
		frame.setContentPane(new SimplyPanel(new StartMenuGraphicsBuilder(model)));
		controller = new ZQSDKeyBoardController();
		controller.bindControllable(this);
		frame.addKeyListener(controller);
		
		model.addSelector(new LeftRightSelector<String>(RessourceManager.getInstance().getLanguageList().toArray(new String[1]), RessourceManager.getInstance().getLanguageCodeList().toArray(new String[1])));

	}

	public static GameOption getGameOption(JFrame frame) {
		Object object = new Object();
		new StartMenuController(frame, object);

		try {
			synchronized (object) {
				object.wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return gameOption;
	}

	@Override
	public void executeOrder(MovementOrder order) {
		if (order == MovementOrder.GoDown) {
			String[] names;
			switch (model.getSelectorNumber()) {
			case 1:
				RessourceManager.getInstance().loadLanguages((String) model.getSelectors().get(0).getSelectedValue());
				names = new String[GameOption.getPossiblePlayerNumber(null).size()];
				for(int i=0;i<names.length;i++) {
					names[i] = GameOption.getPossiblePlayerNumber(null).get(i) + " " +RessourceManager.getInstance().getText((i==0)? "player" : "players");
				}
				model.addSelector(new LeftRightSelector<Integer>(names,GameOption.getPossiblePlayerNumber(null).toArray(new Integer[1])));
				model.notifyObservers();
				break;
			case 2:
				GameMode[] possible = GameOption.getPossibleGameMode((int) model.getSelectors().get(1).getSelectedValue()).toArray(new GameMode[1]);
				names = new String[possible.length];
				for(int i=0;i<names.length;i++) {
					names[i] = RessourceManager.getInstance().getText(possible[i].toString());
				}
				model.addSelector(new LeftRightSelector<GameMode>(names,possible));
				model.notifyObservers();
				break;
			case 3:
				gameOption = new GameOption((int)model.getSelectors().get(1).getSelectedValue(), (GameMode)model.getSelectors().get(2).getSelectedValue(), (String)model.getSelectors().get(0).getSelectedValue());
				synchronized (object) {
					object.notify();
				}
				break;
			default:
				break;
			}
			model.notifyObservers();
		} else if (order == MovementOrder.StopMovement) {

		} else {
			model.executeOrder(order);
		}

	}
}
