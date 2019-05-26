package com.TomBAN.BoulderDash.Game.Model;

import java.util.ArrayList;
import java.util.Observable;

import com.TomBAN.BoulderDash.Game.Model.BlockList.Player;
import com.TomBAN.Ticker.Tickable;
import com.TomBAN.Ticker.TickerManager;

public class BoulderDashModel extends Observable implements Tickable {
	private StringMap strMap;
	private Map map;
	private ArrayList<ControllableController> controllers;
	private int life = 3;

	private ModelStatut modelStatut = ModelStatut.WaitingStart;
	private int statutAvancement = 0;
	private Chrono chrono;

	public BoulderDashModel(StringMap strMap) {
		// TODO Auto-generated constructor stub
		this.strMap = strMap;
		controllers = new ArrayList<ControllableController>();
		chrono = new Chrono();
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
		chrono.start();
	}

	public Chrono getChrono() {
		return chrono;
	}

	@Override
	public void tick() {
		if (won()) {
			chrono.stop();
			// System.out.println("WON !!!!!!");
			// TickerManager.stop(this);
		} else if (loose()) {
			this.map = strMap.toRealMap(controllers);
			life--;
			// System.out.println("LOOSE !!!!!!");
		}
		gameLoop();
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

	public ModelStatut getModelStatut() {
		return modelStatut;
	}

	public int getLife() {
		return life;
	}

}
