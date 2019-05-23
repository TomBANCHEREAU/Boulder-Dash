package com.TomBAN.BoulderDash.Model;

import java.util.ArrayList;

import com.TomBAN.BoulderDash.Model.BlockList.Player;

public class BoulderDashModel {
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
	public Map getMap() {
		return map;
	}
	
}
