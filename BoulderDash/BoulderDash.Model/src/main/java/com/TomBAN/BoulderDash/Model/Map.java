package com.TomBAN.BoulderDash.Model;

import java.util.ArrayList;

import com.TomBAN.BoulderDash.Model.BlockList.*;

public class Map {
	private final int width, height;
	private Block[][] blocks;
	private ArrayList<Player> players;
	private int update=0;
	
	public Map(int width, int height, String map) {
		players = new ArrayList<Player>();
		this.width = width;
		this.height = height;
		this.blocks = new Block[width][height];
		this.loadFromStr(map);
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	private void loadFromStr(String map) {
		String[] line = map.split("\n");
		if (line.length == height) {
			for (int y = 0; y < height; y++) {
				if (line[y].length() >= width) {
					for (int x = 0, i = 0; x < width && i < line[y].length(); x++, i++) {
						blocks[x][y] = null;
						switch (line[y].charAt(i)) {
						case 'O':
							addBlockAt(new Rock(x, y), x, y);
							break;
						case 'A':
							Player a = new Player(x, y);
							addBlockAt(a, x, y);
							players.add(a);
							break;
						case 'V':
							// TODO addBlockAt(new Rock(x, y), x, y);
							break;
						case '#':
							addBlockAt(new Dirt(x, y), x, y);
							break;
						case '@':
							addBlockAt(new Boundary(x, y), x, y);
							break;
						case '¤':
							//addBlockAt(new Rock(x, y), x, y);
							break;
						case ' ':
							break;
						default:
							System.err.println("unknown Block" + line[y].charAt(i));
							break;
						}
					}
				} else {
					throw new RuntimeException("invalid map width " + line[y].length() + " != " + width);
				}
			}
		} else {
			throw new RuntimeException("invalid map height" + line.length + " != " + height);
		}
	}

	private void addBlockAt(Block b, int x, int y) {
		blocks[x][y] = b;
		b.setMap(this);
	}

	public Block getBlockAt(int x, int y) {
		if (x >= 0 && x < width && y >= 0 && y < height) {
			return blocks[x][y];
		} else {
			return new OutOfTheMap(x, y);
		}
	}

	public void moveBlock(Block b,Direction d) {
		if(b instanceof MovableBlock) {
			switch (d) {
			case Up:
				moveBlock(b,b.getxIndex(),b.getyIndex()-1);
				break;
			case Left:
				moveBlock(b,b.getxIndex(),b.getyIndex()-1);
				break;
			case Down:
				moveBlock(b,b.getxIndex(),b.getyIndex()-1);
				break;
			case Right:
				moveBlock(b,b.getxIndex(),b.getyIndex()-1);
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

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public void removeBlock(Block b) {
		if (blocks[b.getxIndex()][b.getyIndex()] == b) {
			blocks[b.getxIndex()][b.getyIndex()] = null;
		}
	}

	public void updateAllBlock() {
		update++;
		for(int y=getHeight()-1;y>=0;y--) {
			for(int x=0;x<getWidth();x++) {
				if(blocks[x][y]!=null) {
					blocks[x][y].update(update);
				}
			}
		}
		
	}
}
