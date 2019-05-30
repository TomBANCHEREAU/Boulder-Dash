package com.TomBAN.BoulderDash.Game.Model;

import java.awt.Image;
import java.util.ArrayList;

import com.TomBAN.BoulderDash.Game.Model.BlockList.*;
import com.TomBAN.BoulderDash.Ressource.RessourceManager;

public class Map {
	private final int width, height;
	private Block[][] blocks;
	private ArrayList<Player> players;
	private int update = 0;
	private Image background = RessourceManager.getInstance().getImage("BACKGROUND.png");
	private int diamond = 0;
	private int diamondNeeded;
	private ArrayList<Network> networks;



	public Map(int width, int height, Block[][] blocks, ArrayList<Player> players,int diamondNeeded,ArrayList<Network> networks) {
		this.width = width;
		this.height = height;
		this.blocks = blocks;
		this.players = players;
		this.diamondNeeded=diamondNeeded;
		this.networks = networks;
		bindMapToBlocks();
	}
	
	public void addBlock(Block b) {
		if(b!=null) {
			blocks[b.getxIndex()][b.getyIndex()] = b;
			b.bindMap(this);
		}
	}
	
	private void bindMapToBlocks() {
		for(Block[] blockList : blocks) {
			for(Block block : blockList) {
				if(block!=null) {
					block.bindMap(this);
				}
			}
		}
	}
	public void updateNetwork() {
		for (Network network : networks) {
			network.update();
		}
		
	}
//	private Network getNetwork(int id) {
//		for(Network network : networks) {
//			if(network.getId() == id) {
//				return network;
//			}
//		}
//		Network n = new Network(id);
//		networks.add(n);
//		return n;
//	}
	
	public void updateAllBlock() {
		update++;
		// for(int y=getHeight()-1;y>=0;y--) {
		for (int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				if (blocks[x][y] != null) {
					blocks[x][y].update(update);
				}
			}
		}

	}

	public void moveBlock(Block b, int x, int y) {
		if(getBlockAt(x, y) == null) {
			if (x >= 0 && x < width && y >= 0 && y < height) {
				blocks[b.getxIndex()][b.getyIndex()] = null;
				blocks[x][y] = b;
				b.setxIndex(x);
				b.setyIndex(y);
			}
		}

	}

	public void removeBlock(Block b) {
		if (getBlockAt(b.getxIndex(), b.getyIndex()) == b) {
			blocks[b.getxIndex()][b.getyIndex()] = null;
		}
	}


	
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	public Block getBlockAt(int x, int y) {
		if (x >= 0 && x < width && y >= 0 && y < height) {
			return blocks[x][y];
		} else {
			return new OutOfTheMap(x, y);
		}
	}
	
	
	public boolean won() {
		for (Player p : getPlayers()) {
			if (!p.hasWin()) {
				return false;
			}
		}
		return true;
	}

	public boolean loose() {
		for (Player p : getPlayers()) {
			if (p.isDead()) {
				return true;
			}
		}
		return false;
	}
	
	//Getters
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public Image getBackgroundImage() {
		return background;
	}
	public int getDiamond() {
		return diamond;
	}
	public void addDiamond() {
		this.diamond++;
	}
	public int getDiamondNeeded() {
		return diamondNeeded;
	}


}
