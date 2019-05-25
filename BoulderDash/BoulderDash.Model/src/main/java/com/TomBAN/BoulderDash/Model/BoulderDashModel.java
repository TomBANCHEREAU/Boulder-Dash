package com.TomBAN.BoulderDash.Model;

import java.util.ArrayList;
import java.util.Observable;

import com.TomBAN.BoulderDash.Model.BlockList.Player;
import com.TomBAN.Ticker.Tickable;
import com.TomBAN.Ticker.TickerManager;

public class BoulderDashModel extends Observable implements Tickable {
	private StringMap strMap;
	private Map map;
	private ArrayList<ControllableController> controllers;
	private int diamond = 0;
	private ModelStatut modelStatut = ModelStatut.WaitingStart;
	public BoulderDashModel(StringMap strMap) {
		// TODO Auto-generated constructor stub
		this.strMap = strMap;
		controllers = new ArrayList<ControllableController>();
	}

	public void addController(ControllableController controller) {
		controllers.add(controller);
	}

	public ArrayList<Player> getPlayers() {
		return map.getPlayers();
	}

	public void gameLoop() {
		map.updateAllBlock();
	}

	public boolean isFinished() {
		return false;
	}

	public Map getMap() {
		return map;
	}

	public void start() {
		this.map = strMap.toRealMap(controllers);
		TickerManager.addTicker(this);
		TickerManager.get(this).setTickRate(20);
		TickerManager.start(this);
		modelStatut = ModelStatut.Playing;
	}

	@Override
	public void tick() {
		if (won()) {
			// System.out.println("WON !!!!!!");
			// TickerManager.stop(this);
		} else if (loose()) {
			this.map = strMap.toRealMap(controllers);
			// System.out.println("LOOSE !!!!!!");
		}else {
			gameLoop();
		}
		setChanged();
		notifyObservers();
//		System.out.println("time : "+TickerManager.get(this).getMillisSinceStart()/10/100.0);
	}

	public boolean won() {
		for (Player p : map.getPlayers()) {
			if (!p.hasWin()) {
				return false;
			}
		}
		return true;
	}

	public boolean loose() {
		for (Player p : map.getPlayers()) {
			if (p.isDead()) {
				return true;
			}
		}
		return false;
	}

	public void addDiamond() {
		diamond++;
	}

	public ModelStatut getModelStatut() {
		return modelStatut;
	}


}
