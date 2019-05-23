package com.TomBAN.BoulderDash.Model;

import java.util.ArrayList;
import java.util.Observable;

import com.TomBAN.BoulderDash.Model.BlockList.Player;

public class BoulderDashModel extends Observable{
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
		// TODO Auto-generated method stub
		
	}
	
}
