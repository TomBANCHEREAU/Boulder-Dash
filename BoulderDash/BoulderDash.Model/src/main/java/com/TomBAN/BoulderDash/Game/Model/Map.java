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


	public Map(int width, int height, Block[][] blocks, ArrayList<Player> players,int diamondNeeded) {
		this.width = width;
		this.height = height;
		this.blocks = blocks;
		this.players = players;
		this.diamondNeeded=diamondNeeded;
		bindMapToBlocks();
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
	
	
	public void moveBlock(Block b, Direction d) {
		if (b instanceof MovableBlock) {
			switch (d) {
			case Up:
				moveBlock(b, b.getxIndex(), b.getyIndex() - 1);
				break;
			case Left:
				moveBlock(b, b.getxIndex(), b.getyIndex() - 1);
				break;
			case Down:
				moveBlock(b, b.getxIndex(), b.getyIndex() - 1);
				break;
			case Right:
				moveBlock(b, b.getxIndex(), b.getyIndex() - 1);
				break;

			default:
				break;
			}
		}

	}

	public void moveBlock(Block b, int x, int y) {
		if (x >= 0 && x < width && y >= 0 && y < height) {
			blocks[b.getxIndex()][b.getyIndex()] = null;
			blocks[x][y] = b;
			b.setxIndex(x);
			b.setyIndex(y);
		}

	}

	public void removeBlock(Block b) {
		if (blocks[b.getxIndex()][b.getyIndex()] == b) {
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
	public int getScore() {
		// TODO Auto-generated method stub
		return 0;
	}
}
