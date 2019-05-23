package com.TomBAN.BoulderDash.Model;

import java.util.ArrayList;
import java.util.Observable;

import com.TomBAN.BoulderDash.Model.BlockList.Player;
import com.TomBAN.Ticker.Tickable;
import com.TomBAN.Ticker.TickerManager;

public class BoulderDashModel extends Observable implements Tickable{
	private Map map;

	public BoulderDashModel(Map map) {
		// TODO Auto-generated constructor stub
		this.map = map;
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
		TickerManager.addTicker(this);
		TickerManager.get(this).setTickRate(20);
		TickerManager.start(this);
	}

	@Override
	public void tick() {
		gameLoop();
		setChanged();
		notifyObservers();
	}
	
}
