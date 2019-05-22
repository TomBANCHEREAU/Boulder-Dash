package com.TomBAN.BoulderDash.Model;

import com.TomBAN.BoulderDash.Model.BlockList.*;

public class Map {
	private final int width, height;
	private Block[][] blocks;

	public Map(int width, int height, String map) {
		this.width = width;
		this.height = height;
		this.blocks = new Block[width][height];
		this.loadFromStr(map);
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
							// TODO addBlockAt(new Spawn(x, y), x, y);
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
							addBlockAt(new Rock(x, y), x, y);
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
				moveBlock(b,b.getIntX(),b.getIntY()-1);
				break;
			case Left:
				moveBlock(b,b.getIntX(),b.getIntY()-1);
				break;
			case Down:
				moveBlock(b,b.getIntX(),b.getIntY()-1);
				break;
			case Right:
				moveBlock(b,b.getIntX(),b.getIntY()-1);
				break;
	
			default:
				break;
			}
		}
		
	}

	private void moveBlock(Block b, int x, int y) {
		if (x >= 0 && x < width && y >= 0 && y < height) {
			blocks[b.getIntX()][b.getIntY()] = null;
			blocks[x][y] = b;
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
		if (blocks[b.getIntX()][b.getIntY()] == b) {
			blocks[b.getIntX()][b.getIntY()] = null;
		}
	}
}
