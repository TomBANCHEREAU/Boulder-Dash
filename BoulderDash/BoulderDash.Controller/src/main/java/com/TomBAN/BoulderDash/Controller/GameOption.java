package com.TomBAN.BoulderDash.Controller;

import java.util.ArrayList;
import java.util.HashMap;

import com.TomBAN.BoulderDash.Ressource.RessourceManager;

public final class GameOption {
	private final static HashMap<GameMode, ArrayList<Integer>> POSSIBLE_PLAYER_NUMBER_PER_GAMEMODE;
	static {
		POSSIBLE_PLAYER_NUMBER_PER_GAMEMODE = new HashMap<GameMode, ArrayList<Integer>>();
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
	
	public GameOption(final int playerNumber, final GameMode gameMode, final String language) {
		this(playerNumber, gameMode, language, false);
	}
	public GameOption(final int playerNumber, final GameMode gameMode, final String language,boolean dualScreen) {
		setGameMode(gameMode,playerNumber);
		setLanguage(language);
		setDualScreen(dualScreen);
	}
	
	
	
	


	public void setDualScreen(boolean dualScreen) {
		if (gameMode == GameMode.SinglePlayer) {
			throw new RuntimeException("Dual Screen is not available for the SinglePlayer Mode");
		}
		this.dualScreen = dualScreen;
	}

	private void setGameMode(final GameMode gameMode,int playerNumber) {
		if (gameMode == null) {
			throw new RuntimeException("GameMode cannot be null");
		}
		if (getPossiblePlayerNumber(gameMode).contains(playerNumber)) {
			this.gameMode = gameMode;
			this.playerNumber = playerNumber;
		} else {
			throw new RuntimeException("Invalid Player number for the " + gameMode + " GameMode : " + playerNumber + " != " + getPossiblePlayerNumber(gameMode));
		}
	}

	private void setLanguage(String language) {
		if (RessourceManager.getInstance().getLanguageList().contains(language)) {
			this.language = language;
		} else {
			throw new RuntimeException("Unknown Language : \"" + language + "\"");
		}
	}
	
	
	
	
	
	
	
	
	public static ArrayList<String> getPossibleLanguages() {
		return RessourceManager.getInstance().getLanguageList();
	}
	public static ArrayList<Integer> getPossiblePlayerNumber(GameMode gameMode) {
		return POSSIBLE_PLAYER_NUMBER_PER_GAMEMODE.get(gameMode);
	}
	public static ArrayList<GameMode> getPossibleGameMode(int playerNumber) {
		ArrayList<GameMode> possibleGameMode = new ArrayList<GameMode>();
		for (GameMode gameMode : GameMode.values()) {
			if (POSSIBLE_PLAYER_NUMBER_PER_GAMEMODE.get(gameMode).contains(playerNumber)) {
				possibleGameMode.add(gameMode);
			}
		}
		return possibleGameMode;
	}
	
	
	
	
	
	
	
	
	
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
		if(gameMode == GameMode.SinglePlayer || gameMode == GameMode.MultiCoop) {
			return 1;
		}
		if(gameMode == GameMode.MultiCoopRace) {
			return 2;
		}
		if(gameMode == GameMode.MultiPlayerRace) {
			return playerNumber;
		}
		throw new NullPointerException();
	}
	public int getPlayerNumberPerMap() {
		if(gameMode == GameMode.SinglePlayer || gameMode == GameMode.MultiPlayerRace) {
			return 1;
		}
		if(gameMode == GameMode.MultiCoopRace) {
			return 2;
		}
		if(gameMode == GameMode.MultiCoop) {
			return playerNumber;
		}
		throw new NullPointerException();
	}
}
