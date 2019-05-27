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
	
	private ModelStatut modelStatut;
	private int statutAvancement = 0;
	private Chrono chrono;

	public BoulderDashModel(Observer observer,int life) {
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
		while(TickerManager.get(this)!=null) {
			TickerManager.kill(this);
		}
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
		TickerManager.addTicker(this);
		TickerManager.get(this).setTickRate(20);
		modelStatut = ModelStatut.WaitingStart;
		//Waiting
		TickerManager.start(this);
		modelStatut = ModelStatut.Playing;
		chrono.start();
	}

	public Chrono getChrono() {
		return chrono;
	}
	public boolean loose() {
		return 0>=life;
	}
	@Override
	public void tick() {
		if (map.won()) {
			chrono.stop();
			modelStatut = ModelStatut.WaitingNextMap;
			controller.notifyObservers();
		} else if (map.loose()) {
			chrono.stop();
			this.map = strMap.toRealMap(controllers);
			chrono.start();
			life--;
			controller.notifyObservers();
		}
		gameLoop();
		setChanged();
		notifyObservers();
	}



	public ModelStatut getModelStatut() {
		return modelStatut;
	}

	public int getLife() {
		return life;
	}

}
