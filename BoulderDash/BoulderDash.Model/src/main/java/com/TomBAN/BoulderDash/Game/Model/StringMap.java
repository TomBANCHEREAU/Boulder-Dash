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
	private int width;
	private int height;
	private String[] stringMap;
	private int diamondNeeded;
	private int playerCount;
	private int world, level;
	private Score[] highScore;
	private int id;
	private int timeToFinish;
	private ArrayList<Network> networks;

	public StringMap(int width, int height, int diamondNeeded, int playerCount, String[] stringMap, int world,
			int level,Score[] score,int time,int id) {
		setWidth(width);
		setHeight(height);
		setStringMap(stringMap);
		setDiamondNeeded(diamondNeeded);
		setPlayerCount(playerCount);
		networks = new ArrayList<Network>();
		this.world = world;
		this.level = level;
		this.highScore =score;
		this.id = id;
		this.timeToFinish=time;
	}
	public StringMap(int width, int height, int diamondNeeded, int playerCount, String stringMap, int world,
			int level,Score[] score,int time, int id) {
		this(width, height, diamondNeeded, playerCount, stringMap.split("\n"), world, level,score,time,id);
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
					Network n=getNetwork((int)stringMap[y].charAt(i+1)-48);
					blocks[x][y] = new PressurePlate(x,y,n);
					i++;
					break;
				case ']':
					Network n2=getNetwork((int)stringMap[y].charAt(i+1)-48);
					blocks[x][y] = new Door(x, y, n2);
					i++;
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
		return new Map(width, height, blocks, players, diamondNeeded,networks);

	}

	public void addScore(Score newScore) {
		for(int i=0;i<highScore.length;i++) {
			if(highScore[i]==null) {
				highScore[i] = newScore;
				return;
			}else if(newScore.getScore() > highScore[i].getScore()) {
				Score tmp = highScore[i];
				highScore[i] = newScore;
				newScore = tmp;
			}
		}
	}
	
	private Network getNetwork(int id) {
		for(Network network : networks) {
			if(network.getId() == id) {
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
	
	
	//Getters
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

	public String[] getStringMap() {
		return stringMap;
	}

	public int getMapID() {
		return id;
	}

	public Score[] getScores() {
		return highScore;
	}

}
