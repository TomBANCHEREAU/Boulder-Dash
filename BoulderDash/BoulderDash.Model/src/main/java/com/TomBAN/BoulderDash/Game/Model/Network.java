package com.TomBAN.BoulderDash.Game.Model;

import java.util.ArrayList;

public class Network {
	private boolean activated;
	private int id;
	private ArrayList<Block> activators;
	private ArrayList<Activable> activables;

	public Network(int id) {
		this.id = id;
		activated = false;
		activators = new ArrayList<Block>();
		activables = new ArrayList<Activable>();
	}

	public void activate(Block activator) {
		if (!activators.contains(activator)) {
			activators.add(activator);
		}
	}

	public void disactivate(Block activator) {
		if (activators.contains(activator)) {
			activators.remove(activator);
		}
	}

	public void update() {
		if (activated != (activators.size() > 0)) {
			if (!activated) {
				System.out.println("disact");
				for (Activable activable : activables) {
					activable.activate();
				}
			} else {
				System.out.println("act");
				for (Activable activable : activables) {
					activable.disactivate();
				}
			}
			activated = (activators.size() > 0);
		}
	}

	// Getters

	public boolean isActivated() {
		return activated;
	}

	public int getId() {
		return id;
	}

	public void addActivable(Activable activable) {
		if(!activables.contains(activable)) {
			activables.add(activable);
		}
	}
}
