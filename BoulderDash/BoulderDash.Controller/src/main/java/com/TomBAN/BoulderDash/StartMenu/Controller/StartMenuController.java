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

/**
 * this class permit to select the gameOption
 * @author TomBANCHEREAU
 */
public class StartMenuController implements Controllable {
	private static GameOption gameOption; 
	private Object object;
	private KeyBoardController controller;
	private StartMenuModel model;
	private JFrame frame;
	private StartMenuController(JFrame frame, Object object) {
		this.frame=frame;
		this.object = object;
		model = new StartMenuModel();
		frame.setContentPane(new SimplyPanel(new StartMenuGraphicsBuilder(model)));
		frame.getContentPane().setSize(frame.getWidth(), frame.getHeight());
		controller = new ZQSDKeyBoardController();
		controller.bindControllable(this);
		frame.addKeyListener(controller);
		
		model.addSelector(new LeftRightSelector<String>(RessourceManager.getInstance().getLanguageList().toArray(new String[1]), RessourceManager.getInstance().getLanguageCodeList().toArray(new String[1])));

	}

	/**
	 * this static methods will show the start menu and return the selected gameOption
	 * @param frame
	 * frame were the menu will be drown
	 * @return
	 * return the selected gameOption
	 */
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
				if((Integer)model.getSelectors().get(1).getSelectedValue() == 1) {
					setGameOption(new GameOption((int)model.getSelectors().get(1).getSelectedValue(), GameMode.SinglePlayer, (String)model.getSelectors().get(0).getSelectedValue()));
				}else {
					GameMode[] possible = GameOption.getPossibleGameMode((int) model.getSelectors().get(1).getSelectedValue()).toArray(new GameMode[1]);
					names = new String[possible.length];
					for(int i=0;i<names.length;i++) {
						names[i] = RessourceManager.getInstance().getText(possible[i].toString());
					}
					model.addSelector(new LeftRightSelector<GameMode>(names,possible));
					model.notifyObservers();
				}
				break;
			case 3:
				setGameOption(new GameOption((int)model.getSelectors().get(1).getSelectedValue(), (GameMode)model.getSelectors().get(2).getSelectedValue(), (String)model.getSelectors().get(0).getSelectedValue()));
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
	private void setGameOption(GameOption gameOption) {
		StartMenuController.gameOption = gameOption;
		frame.removeKeyListener(controller);
		synchronized (object) {
			object.notify();
		}
	}
}
