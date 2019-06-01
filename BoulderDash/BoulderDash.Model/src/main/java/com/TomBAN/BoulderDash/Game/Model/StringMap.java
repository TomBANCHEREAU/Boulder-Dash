package com.TomBAN.BoulderDash.Game.Model;

import java.util.ArrayList;

import com.TomBAN.BoulderDash.Game.Model.BlockList.Boundary;
import com.TomBAN.BoulderDash.Game.Model.BlockList.Diamond;
import com.TomBAN.BoulderDash.Game.Model.BlockList.Dirt;
import com.TomBAN.BoulderDash.Game.Model.BlockList.Door;
import com.TomBAN.BoulderDash.Game.Model.BlockList.Ennemy;
import com.TomBAN.BoulderDash.Game.Model.BlockList.Exit;
import com.TomBAN.BoulderDash.Game.Model.BlockList.HardBoundary;
import com.TomBAN.BoulderDash.Game.Model.BlockList.Player;
import com.TomBAN.BoulderDash.Game.Model.BlockList.PressurePlate;
import com.TomBAN.BoulderDash.Game.Model.BlockList.Rock;

public class StringMap {
	private static final ArrayList<Character> basicChar = new ArrayList<Character>();
	private static final ArrayList<Character> DiamondChar = new ArrayList<Character>();
	private static final ArrayList<Character> specialChar = new ArrayList<Character>();
	static {
		DiamondChar.add('V');
		DiamondChar.add('L');
		DiamondChar.add('U');
		DiamondChar.add('C');
		DiamondChar.add('D');
		basicChar.add('O');
		basicChar.add('A');
		basicChar.add('V');
		basicChar.add('#');
		basicChar.add('!');
		basicChar.add('@');
		basicChar.add('$');
		basicChar.add('L');
		basicChar.add('U');
		basicChar.add('C');
		basicChar.add('D');
		basicChar.add(' ');
		specialChar.add('[');
		specialChar.add(']');
		specialChar.add('_');
	}
	private int width;
	private int height;
	private String[] stringMap;
	private int diamondNeeded;
	private int playerCount;
	private int world, level;
	private Score[] highScore;
	private int id;
	private int timeToFinish;

	public StringMap(int width, int height, int diamondNeeded, int playerCount, String[] stringMap, int world,
			int level, Score[] score, int time, int id) {
		setWidth(width);
		setHeight(height);
		setDiamondNeeded(diamondNeeded);
		setPlayerCount(playerCount);
		setStringMap(stringMap);
		this.world = world;
		this.level = level;
		this.highScore = score;
		this.id = id;
		this.timeToFinish = time;
	}

	public StringMap(int width, int height, int diamondNeeded, int playerCount, String stringMap, int world, int level,
			Score[] score, int time, int id) {
		this(width, height, diamondNeeded, playerCount, stringMap.split("\r?\n"), world, level, score, time, id);
	}

	public Map toRealMap(ArrayList<ControllableController> controllers) {
		final Block[][] blocks = new Block[width][height];
		final ArrayList<Player> players = new ArrayList<Player>();
		final ArrayList<Network> networks = new ArrayList<Network>();
		for (int y = 0; y < height; y++) {
			for (int x = 0, i = 0; x < width && i < stringMap[y].length(); x++, i++) {
				Network network = null;
				if (specialChar.contains(stringMap[y].charAt(i))) {
					network = getNetwork(networks,(int) stringMap[y].charAt(i + 1) - 48);
				}

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
				case '!':
					blocks[x][y] = new HardBoundary(x, y);
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
				case '_':
					blocks[x][y] = new PressurePlate(x, y, network);
					break;
				case ']':
					blocks[x][y] = new Door(x, y, network, false);
					break;
				case '[':
					blocks[x][y] = new Door(x, y, network, true);
					break;
				default:
				case ' ':
					blocks[x][y] = null;
				}
				if (specialChar.contains(stringMap[y].charAt(i))) {
					i++;
				}
			}
		}
		for (int i = 0; i < controllers.size(); i++) {
			controllers.get(i).bindControllable(players.get(i));
		}
		return new Map(width, height, blocks, players, diamondNeeded, networks);

	}

	public void addScore(Score newScore) {
		for (int i = 0; i < highScore.length; i++) {
			if (highScore[i] == null) {
				highScore[i] = newScore;
				return;
			} else if (newScore.getScore() > highScore[i].getScore()) {
				Score tmp = highScore[i];
				highScore[i] = newScore;
				newScore = tmp;
			}
		}
	}

	private Network getNetwork(ArrayList<Network> networks,int id) {
		for (Network network : networks) {
			if (network.getId() == id) {
				return network;
			}
		}
		Network n = new Network(id);
		networks.add(n);
		return n;
	}

	// Setters

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
		}
		int playerCount = 0;
		int diamondCount = 0;
		boolean haveExit = false;
		for (int y = 0; y < height; y++) {
			int i, x;
			for (x = 0, i = 0; i < stringMap[y].length(); x++, i++) {
				if (DiamondChar.contains(stringMap[y].charAt(i))) {
					diamondCount++;
				}
				if (basicChar.contains(stringMap[y].charAt(i))) {
					switch (stringMap[y].charAt(i)) {
					case 'A':
						playerCount++;
						break;
					case '$':
						haveExit = true;
						break;

					default:
						break;
					}
				} else if (specialChar.contains(stringMap[y].charAt(i))) {
					i++;
				} else {
					throw new RuntimeException("unknown Block : (char:'" + stringMap[y].charAt(i) + "',int: "
							+ (int) stringMap[y].charAt(i) + ",hex: " + Integer.toHexString(stringMap[y].charAt(i))
							+ ")");
				}
			}

			if (this.width != x) {
				throw new RuntimeException("The specified width does not fit with the String length (Specified width = "
						+ this.width + " String width = " + x + " at line : " + y + ")");
			}
		}

		if (!haveExit) {
			throw new RuntimeException("The map does not have an Exit");
		}
		if (playerCount != this.playerCount) {
			throw new RuntimeException(
					"The specified player count does not fit with the player count present on the map (Specified player count = "
							+ this.playerCount + " map player count = " + playerCount);
		}
		if (this.diamondNeeded > diamondCount) {
			throw new RuntimeException(
					"The specified diamondNeeded does not fit with the diamond count on the map (Specified diamondNeeded = "
							+ this.diamondNeeded + " map diamond count = " + diamondCount);
		}

		this.stringMap = stringMap;
	}

	private void setDiamondNeeded(int diamondNeeded) {
		if (diamondNeeded < 0) {
			throw new RuntimeException(
					"The player count of the map cannot be less than 0 : " + diamondNeeded + " ! >= 0");
		}
		this.diamondNeeded = diamondNeeded;
	}

	private void setPlayerCount(int playerCount) {
		if (playerCount <= 0) {
			throw new RuntimeException(
					"The player count of the map cannot be less or equals than 0 : " + playerCount + " ! > 0");
		}
		this.playerCount = playerCount;
	}

	// Getters
	public int getTimeToFinish() {
		return timeToFinish;
	}

	public int getLevel() {
		return level;
	}

	public int getWorld() {
		return world;
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

	public int getMapID() {
		return id;
	}

	public Score[] getScores() {
		return highScore;
	}

}
