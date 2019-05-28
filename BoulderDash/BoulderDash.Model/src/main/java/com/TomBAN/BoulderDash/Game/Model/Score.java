package com.TomBAN.BoulderDash.Game.Model;

public final class Score {
	private final int score;
	private final String name;
	private final boolean isNew;
	private StringMap stringMap;

	public Score(final int score, final String name) {
		this(score, name, null, false);
	}

	public Score(final int score, final String name, StringMap map, final boolean isNew) {
		this.score = score;
		this.name = name;
		this.isNew = isNew;
		this.stringMap = map;
	}

	public int getScore() {
		return score;
	}

	public StringMap getStringMap() {
		return stringMap;
	}

	public String getName() {
		return name;
	}

	public boolean isNew() {
		return isNew;
	}
}
