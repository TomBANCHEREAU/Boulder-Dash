package com.TomBAN.BoulderDash.StartMenu.Model;

import java.util.ArrayList;
import java.util.Observable;

import com.TomBAN.BoulderDash.Game.Model.Controllable;
import com.TomBAN.BoulderDash.Game.Model.MovementOrder;

public class StartMenuModel extends Observable implements Controllable {
	ArrayList<LeftRightSelector<?>> selectors;

	public StartMenuModel() {
		selectors = new ArrayList<LeftRightSelector<?>>();
		// TODO Auto-generated constructor stub
	}

	public void addSelector(LeftRightSelector<?> a) {
		selectors.add(a);
	}

	@Override
	public void executeOrder(MovementOrder order) {
		switch (order) {
		case GoLeft:
			selectors.get(selectors.size() - 1).left();
			break;
		case GoRight:
			selectors.get(selectors.size() - 1).right();
			break;
		case GoUp:
			if(selectors.size()>1) {
				selectors.remove(selectors.size() - 1);
			}
		default:
			break;
		}
		notifyObservers();

	}

	@Override
	public void notifyObservers() {
		setChanged();
		super.notifyObservers();
	}
	
	public int getSelectorNumber() {
		return selectors.size();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<LeftRightSelector<?>> getSelectors() {
		return (ArrayList<LeftRightSelector<?>>) selectors.clone();
	}
}
