package com.TomBAN.BoulderDash.Game.Model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import com.TomBAN.BoulderDash.Game.Model.BlockList.Player;
import com.TomBAN.Ticker.Tickable;
import com.TomBAN.Ticker.TickerManager;

public class BoulderDashModel extends Observable implements Tickable {
	private StringMap strMap;
	private Map map;
	private ArrayList<ControllableController> controllers;
	private Observable controller;
	private int life = 3;

	private ModelStatut modelStatut = ModelStatut.WaitingStart;
	private int statutAvancement = 0;
	private Chrono chrono;

	public BoulderDashModel(Observer observer, int life) {
		controller = new Observable() {
			@Override
			public void notifyObservers() {
				setChanged();
				super.notifyObservers();
			}
		};
		controller.addObserver(observer);
		// TODO Auto-generated constructor stub
		this.life = life;
		controllers = new ArrayList<ControllableController>();
		chrono = new Chrono();
	}

	public void nextMap(StringMap strMap) {
		this.strMap = strMap;
		start();
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

	public Map getMap() {
		return map;
	}

	public void start() {
		this.map = strMap.toRealMap(controllers);
		setStatut(ModelStatut.StartLevelScreen);
		if (TickerManager.get(this) == null) {
			TickerManager.addTicker(this);
			TickerManager.get(this).setTickRate(20);
			TickerManager.start(this);
		}
	}

	public Chrono getChrono() {
		return chrono;
	}

	public boolean loose() {
		return 0 >= life;
	}
	private void setStatut(ModelStatut statut) {
		this.modelStatut = statut;
		this.statutAvancement = 0;
		controller.notifyObservers();
	}
	@Override
	public void tick() {
		statutAvancement++;
		if (modelStatut == ModelStatut.StartLevelScreen && statutAvancement >= 50) {
			setStatut(ModelStatut.Playing);
			chrono.start();
		}
		if (modelStatut == ModelStatut.EndMapScreen && statutAvancement >= 200) {
			setStatut(ModelStatut.WaitingNextMap);
			chrono.start();
		}
		if(modelStatut == ModelStatut.Playing) {
			if (map.won()) {
				chrono.stop();
				life+=getMap().getDiamond()-getMap().getDiamondNeeded();
				setStatut(ModelStatut.EndMapScreen);
			} else if (map.loose()) {
				chrono.stop();
				this.map = strMap.toRealMap(controllers);
				chrono.start();
				life--;
			}
//			if(loose()) {
//				setStatut(ModelStatut.WaitingNextMap);
//				
//			}
			gameLoop();
		}
		setChanged();
		notifyObservers();
	}

	public StringMap getStrMap() {
		return strMap;
	}

	public ModelStatut getModelStatut() {
		return modelStatut;
	}

	public int getLife() {
		return life;
	}

}
