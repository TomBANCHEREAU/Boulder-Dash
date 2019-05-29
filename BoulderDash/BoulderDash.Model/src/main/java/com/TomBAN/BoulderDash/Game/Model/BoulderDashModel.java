package com.TomBAN.BoulderDash.Game.Model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import com.TomBAN.BoulderDash.Game.Model.BlockList.Player;
import com.TomBAN.Ticker.Tickable;
import com.TomBAN.Ticker.TickerManager;

public class BoulderDashModel extends Observable implements Tickable {
	private StringMap strMap;
	private Map map;
	private ArrayList<ControllableController> controllers;
	private Observable controller;
	private int life = 3;
	private NewHighScoreListenner highScoreListenner;
	private ModelStatut modelStatut = ModelStatut.WaitingStart;
	private int statutAvancement = 0;
	private Chrono chrono;
	private String playerName;
	private Score score;
	private int totalScore = 0;

	public BoulderDashModel(Observer observer, int life, String playerName, NewHighScoreListenner highScoreListenner) {
		controller = new Observable() {
			@Override
			public void notifyObservers() {
				setChanged();
				super.notifyObservers();
			}
		};
		this.playerName = playerName;
		controller.addObserver(observer);
		// TODO Auto-generated constructor stub
		this.life = life;
		controllers = new ArrayList<ControllableController>();
		chrono = new Chrono();
		this.highScoreListenner = highScoreListenner;
	}

	public void nextMap(StringMap strMap) {
		this.strMap = strMap;
		start();
	}

	public void addController(ControllableController controller) {
		controllers.add(controller);
	}

	private void gameLoop() {
		map.updateAllBlock();
	}

	public void start() {
		this.map = strMap.toRealMap(controllers);
		setStatutWithoutNotify(ModelStatut.StartLevelScreen);
		if (TickerManager.get(this) == null) {
			TickerManager.addTicker(this);
			TickerManager.get(this).setTickRate(20);
			TickerManager.start(this);
		}
	}

	public boolean loose() {
		return life < 0;
	}

	private void setStatut(ModelStatut statut) {
		setStatutWithoutNotify(statut);
		controller.notifyObservers();
	}

	private void setStatutWithoutNotify(ModelStatut statut) {
		this.modelStatut = statut;
		this.statutAvancement = 0;
	}

	@Override
	public void tick() {
		statutAvancement++;
		if (modelStatut == ModelStatut.StartLevelScreen && statutAvancement >= 50) {
			setStatut(ModelStatut.Playing);
			chrono.start();
		}
		if (modelStatut == ModelStatut.Playing) {
			if (map.won()) {
				chrono.stop();
				setStatut(ModelStatut.EndMapScreen);
				if(life>=0) {
					life += getMap().getDiamond() - getMap().getDiamondNeeded();
					sendScore();
				}
			} else if (map.loose()) {
				chrono.stop();
				this.map = strMap.toRealMap(controllers);
				chrono.start();
				life--;
				controller.notifyObservers();
			}
		}
		if (modelStatut == ModelStatut.EndMapScreen && statutAvancement >= 200) {
			setStatut(ModelStatut.WaitingNextMap);
		}
		if (modelStatut == ModelStatut.EndMapScreen || modelStatut == ModelStatut.WaitingNextMap
				|| modelStatut == ModelStatut.Playing) {
			gameLoop();
		}
		setChanged();
		notifyObservers();
	}

	private void sendScore() {
		final int Diamond = getMap().getDiamond();
//		final int bonusDiamond = getMap().getDiamond() - getMap().getDiamondNeeded();
		final long time = getChrono().getTimeSinceStart();
//		final long timeRemaining = getStrMap().getTimeToFinish() * 1000 - time;
		final int world = getStrMap().getWorld();
		// final int score = (int) (timeRemaining/10)+1000*Diamond;
		final int score = world * 1000 * Diamond - (int) (time / 10 /5*5) + life;
		totalScore += score;
		this.score = new Score(score, getPlayerName(), getStrMap(), true);
		getStrMap().addScore(this.score);
		highScoreListenner.NewHighScoreEvent(this.score);

	}

	// Getters

	public int getTotalScore() {
		return totalScore;
	}

	public Score getScore() {
		return score;
	}

	public StringMap getStrMap() {
		return strMap;
	}

	public ModelStatut getModelStatut() {
		return modelStatut;
	}

	public int getLife() {
		return life;
	}

	public String getPlayerName() {
		return playerName;
	}

	public int getStatutAvancement() {
		return statutAvancement;
	}

	public ArrayList<Player> getPlayers() {
		return map.getPlayers();
	}

	public Map getMap() {
		return map;
	}

	public Chrono getChrono() {
		return chrono;
	}
}
