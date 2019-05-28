package com.TomBAN.BoulderDash.Game.Model;

import java.util.ArrayList;

import com.TomBAN.BoulderDash.Game.Model.BlockList.Boundary;
import com.TomBAN.BoulderDash.Game.Model.BlockList.Diamond;
import com.TomBAN.BoulderDash.Game.Model.BlockList.Dirt;
import com.TomBAN.BoulderDash.Game.Model.BlockList.Ennemy;
import com.TomBAN.BoulderDash.Game.Model.BlockList.Exit;
import com.TomBAN.BoulderDash.Game.Model.BlockList.Player;
import com.TomBAN.BoulderDash.Game.Model.BlockList.Rock;

public class StringMap {
	private int width;
	private int height;
	private String[] stringMap;
	private int diamondNeeded;
	private int playerCount;
	private int world, level;

	public StringMap(int width, int height, int diamondNeeded, int playerCount, String[] stringMap, int world,
			int level) {
		setWidth(width);
		setHeight(height);
		setStringMap(stringMap);
		setDiamondNeeded(diamondNeeded);
		setPlayerCount(playerCount);
		this.world = world;
		this.level = level;
	}

	public StringMap(int width, int height, int diamondNeeded, int playerCount, String stringMap, int world,
			int level) {
		this(width, height, diamondNeeded, playerCount, stringMap.split("\n"), world, level);
	}

	public int getWorld() {
		return world;
	}

	public Map toRealMap(ArrayList<ControllableController> controllers) {
		final Block[][] blocks = new Block[width][height];
		final ArrayList<Player> players = new ArrayList<Player>();
		for (int y = 0; y < height; y++) {
//			if (stringMap[y].length() >= width) {// TODO
			for (int x = 0, i = 0; x < width && i < stringMap[y].length(); x++, i++) {
				switch (stringMap[y].charAt(i)) {
				case 'O':
					blocks[x][y] = new Rock(x, y);
					break;
				case 'A':
					final Player player = new Player(x, y);
					blocks[x][y] = player;
					players.add(player);
					break;
				case 'V':
					blocks[x][y] = new Diamond(x, y);
					break;
				case '#':
					blocks[x][y] = new Dirt(x, y);
					break;
				case '@':
					blocks[x][y] = new Boundary(x, y);
					break;
				case '$':
					blocks[x][y] = new Exit(x, y);
					break;
				case 'L':
					blocks[x][y] = new Ennemy(x, y, 'L');
					break;
				case 'U':
					blocks[x][y] = new Ennemy(x, y, 'U');
					break;
				case 'C':
					blocks[x][y] = new Ennemy(x, y, 'C');
					break;
				case 'D':
					blocks[x][y] = new Ennemy(x, y, 'D');
					break;
				default:
					System.err.println("unknown Block : (char:'" + stringMap[y].charAt(i) + "',int: "
							+ (int) stringMap[y].charAt(i) + ",hex: " + Integer.toHexString(stringMap[y].charAt(i))
							+ ")");
				case ' ':
					blocks[x][y] = null;
				}
			}
//			} else {
//				throw new RuntimeException("invalid map width " + line[y].length() + " != " + width);// TODO
//			}
		}
		for (int i = 0; i < controllers.size(); i++) {
			controllers.get(i).bindControllable(players.get(i));
		}
		return new Map(width, height, blocks, players, diamondNeeded);

	}

	public int getLevel() {
		return level;
	}

	private void setWidth(int width) {
		if (width <= 0) {
			throw new RuntimeException("The width of the map cannot be less or equals than 0 : " + width + " ! > 0");
		}
		this.width = width;
	}

	private void setHeight(int height) {
		if (height <= 0) {
			throw new RuntimeException("The height of the map cannot be less or equals than 0 : " + height + " ! > 0");
		}
		this.height = height;
	}

	private void setStringMap(String[] stringMap) {
		if (stringMap.length != height) {
			throw new RuntimeException("The specified height does not fit with the String Array (Specified height = "
					+ height + " String Array height = " + stringMap.length);
		} // TODO
			// TODO
			// TODO
			// TODO

		this.stringMap = stringMap;
	}

	private void setDiamondNeeded(int diamondNeeded) {
		if (diamondNeeded < 0) {
			throw new RuntimeException(
					"The player count of the map cannot be less than 0 : " + diamondNeeded + " ! >= 0");
		}
		// TODO
		// TODO

		this.diamondNeeded = diamondNeeded;
	}

	private void setPlayerCount(int playerCount) {
		if (playerCount <= 0) {
			throw new RuntimeException(
					"The player count of the map cannot be less or equals than 0 : " + playerCount + " ! > 0");
		}
		// TODO
		// TODO
		this.playerCount = playerCount;
	}

	public int getDiamondNeeded() {
		return diamondNeeded;
	}

	public int getPlayerCount() {
		return playerCount;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String[] getStringMap() {
		return stringMap;
	}

}
