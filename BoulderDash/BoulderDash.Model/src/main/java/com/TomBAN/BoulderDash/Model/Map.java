package com.TomBAN.BoulderDash.Model;

import java.util.ArrayList;

import com.TomBAN.BoulderDash.Model.BlockList.*;

public class Map {
	private final int width, height;
	private Block[][] blocks;
	private ArrayList<Player> players;
	private int update = 0;

	public Map(int width, int height, Block[][] blocks, ArrayList<Player> players) {
		this.width = width;
		this.height = height;
		this.blocks = blocks;
		this.players = players;
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
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
}
