package com.TomBAN.BoulderDash.Game.Controller;

import java.util.ArrayList;
import java.util.HashMap;

import com.TomBAN.BoulderDash.Ressource.RessourceManager;

/**
 * This class permit to save all game Option
 * @author TomBANCHEREAU
 */
public final class GameOption {
	private final static HashMap<GameMode, ArrayList<Integer>> POSSIBLE_PLAYER_NUMBER_PER_GAMEMODE;
	static {
		POSSIBLE_PLAYER_NUMBER_PER_GAMEMODE = new HashMap<GameMode, ArrayList<Integer>>();
		POSSIBLE_PLAYER_NUMBER_PER_GAMEMODE.put(null, new ArrayList<Integer>());
		POSSIBLE_PLAYER_NUMBER_PER_GAMEMODE.get(null).add(1);
		POSSIBLE_PLAYER_NUMBER_PER_GAMEMODE.get(null).add(2);
		POSSIBLE_PLAYER_NUMBER_PER_GAMEMODE.get(null).add(4);
		POSSIBLE_PLAYER_NUMBER_PER_GAMEMODE.put(GameMode.SinglePlayer, new ArrayList<Integer>());
		POSSIBLE_PLAYER_NUMBER_PER_GAMEMODE.get(GameMode.SinglePlayer).add(1);
		POSSIBLE_PLAYER_NUMBER_PER_GAMEMODE.put(GameMode.MultiPlayerRace, new ArrayList<Integer>());
		POSSIBLE_PLAYER_NUMBER_PER_GAMEMODE.get(GameMode.MultiPlayerRace).add(2);
		POSSIBLE_PLAYER_NUMBER_PER_GAMEMODE.get(GameMode.MultiPlayerRace).add(4);
		POSSIBLE_PLAYER_NUMBER_PER_GAMEMODE.put(GameMode.MultiCoop, new ArrayList<Integer>());
		POSSIBLE_PLAYER_NUMBER_PER_GAMEMODE.get(GameMode.MultiCoop).add(2);
		POSSIBLE_PLAYER_NUMBER_PER_GAMEMODE.get(GameMode.MultiCoop).add(4);
		POSSIBLE_PLAYER_NUMBER_PER_GAMEMODE.put(GameMode.MultiCoopRace, new ArrayList<Integer>());
		POSSIBLE_PLAYER_NUMBER_PER_GAMEMODE.get(GameMode.MultiCoopRace).add(4);
	}

	private int playerNumber;
	private GameMode gameMode;
	private String language;
	private boolean dualScreen = false;

	/**
	 * 
	 * this constructor is the same as GameOption(playerNumber,gameMode,language,false)
	 * 
	 * @param playerNumber
	 * Number of player (1,2,4)
	 * @param gameMode
	 * game mode (SinglePlayer,MultiPlayerRace,MultiCoop,MultiCoopRace)
	 * @param language
	 * language of the game
	 * @throws RuntimeException
	 * can throw RuntimeException if the parameters are not compatible.Exemple:
	 * 2 player with the SinglePlayer GameMode will throw an Exception
	 */
	public GameOption(final int playerNumber, final GameMode gameMode, final String language) throws RuntimeException {
		this(playerNumber, gameMode, language, false);
	}

	/**
	 * @param playerNumber
	 * Number of player (1,2,4)
	 * @param gameMode
	 * game mode (SinglePlayer,MultiPlayerRace,MultiCoop,MultiCoopRace)
	 * @param language
	 * language of the game
	 * @param dualScreen
	 * a boolean value which tell if the game will be split on 2 JFrame (Only 2 or 4 Player)
	 * @throws RuntimeException
	 * can throw RuntimeException if the parameters are not compatible.Exemple:
	 * 2 player with the SinglePlayer GameMode will throw an Exception
	 */
	public GameOption(final int playerNumber, final GameMode gameMode, final String language, boolean dualScreen) throws RuntimeException {
		setGameMode(gameMode, playerNumber);
		setLanguage(language);
		setDualScreen(dualScreen);
	}

	private void setDualScreen(boolean dualScreen) {
		if (gameMode == GameMode.SinglePlayer && dualScreen) {
			throw new RuntimeException("Dual Screen is not available for the SinglePlayer Mode");
		}
		this.dualScreen = dualScreen;
	}

	private void setGameMode(final GameMode gameMode, int playerNumber) {
		if (gameMode == null) {
			throw new RuntimeException("GameMode cannot be null");
		}
		if (getPossiblePlayerNumber(gameMode).contains(playerNumber)) {
			this.gameMode = gameMode;
			this.playerNumber = playerNumber;
		} else {
			throw new RuntimeException("Invalid Player number for the " + gameMode + " GameMode : " + playerNumber
					+ " != " + getPossiblePlayerNumber(gameMode));
		}
	}

	private void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * return an ArrayList of String containing all available languages  
	 * @return
	 */
	public static ArrayList<String> getPossibleLanguages() {
		return RessourceManager.getInstance().getLanguageList();
	}

	/**
	 * Return an ArrayList of Integer containing all possible playerNumber for a specified GameMode
	 * @param gameMode
	 * @return
	 */
	public static ArrayList<Integer> getPossiblePlayerNumber(GameMode gameMode) {
		return POSSIBLE_PLAYER_NUMBER_PER_GAMEMODE.get(gameMode);
	}

	/**
	 * Return an ArrayList of GameMode containing all possible GameMode for a specified player number
	 * @param playerNumber
	 * @return
	 */
	public static ArrayList<GameMode> getPossibleGameMode(int playerNumber) {
		ArrayList<GameMode> possibleGameMode = new ArrayList<GameMode>();
		for (GameMode gameMode : GameMode.values()) {
			if (POSSIBLE_PLAYER_NUMBER_PER_GAMEMODE.get(gameMode).contains(playerNumber)) {
				possibleGameMode.add(gameMode);
			}
		}
		return possibleGameMode;
	}

	// Getters
	public boolean isDualScreen() {
		return dualScreen;
	}

	public String getLanguage() {
		return language;
	}

	public GameMode getGameMode() {
		return gameMode;
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public int getModelNumber() {
		if (gameMode == GameMode.SinglePlayer || gameMode == GameMode.MultiCoop) {
			return 1;
		}
		if (gameMode == GameMode.MultiCoopRace) {
			return 2;
		}
		if (gameMode == GameMode.MultiPlayerRace) {
			return playerNumber;
		}
		throw new NullPointerException();
	}

	public int getPlayerNumberPerMap() {
		if (gameMode == GameMode.SinglePlayer || gameMode == GameMode.MultiPlayerRace) {
			return 1;
		}
		if (gameMode == GameMode.MultiCoopRace) {
			return 2;
		}
		if (gameMode == GameMode.MultiCoop) {
			return playerNumber;
		}
		throw new NullPointerException();
	}
}
