package com.TomBAN.BoulderDash.Model;

import java.util.ArrayList;
import java.util.Observable;

import com.TomBAN.BoulderDash.Model.BlockList.Player;
import com.TomBAN.Ticker.Tickable;
import com.TomBAN.Ticker.TickerManager;

public class BoulderDashModel extends Observable implements Tickable{
	private Map map;
	private int diamond=0;
	public BoulderDashModel(StringMap srtMap) {
		// TODO Auto-generated constructor stub
		this.map = srtMap.toRealMap();
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
		if(won()) {
			System.out.println("WON !!!!!!");
			TickerManager.stop(this);
		}else if(loose()) {

			System.out.println("LOOSE !!!!!!");
		}
		setChanged();
		notifyObservers();
//		System.out.println("time : "+TickerManager.get(this).getMillisSinceStart()/10/100.0);
	}
	
	public boolean won() {
		for(Player p : map.getPlayers()) {
			if(!p.hasWin()) {
				return false;
			}
		}
		return true;
	}
	public boolean loose() {
		for(Player p : map.getPlayers()) {
			if(p.isDead()) {
				return true;
			}
		}
		return false;
	}
	public void addDiamond() {
		diamond++;
	}
	
}
