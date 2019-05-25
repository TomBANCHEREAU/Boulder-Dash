package com.TomBAN.BoulderDash.StartMenu.Controller;

import javax.swing.JFrame;

import com.TomBAN.BoulderDash.Frame.SimplyPanel;
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
		return null;
	}

	@Override
	public void executeOrder(MovementOrder order) {
		System.out.println("sdq");
		if (order == MovementOrder.GoDown) {
			switch (model.getSelectorNumber()) {
			case 1:

				break;
			case 2:

				break;
			case 3:
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
