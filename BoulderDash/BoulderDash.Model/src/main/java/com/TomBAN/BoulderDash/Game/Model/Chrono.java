package com.TomBAN.BoulderDash.Game.Model;

public class Chrono {
	private long time = -1;
	private boolean started = true;

	public boolean isStarted() {
		return started;
	}

	public void start() {
		time = System.currentTimeMillis();
		started = true;
	}

	public long getTimeSinceStart() {
		if (started) {
			return (System.currentTimeMillis() - time);
		} else {
			return time;
		}
	}

	public void stop() {
		time = getTimeSinceStart();
		started = false;
	}
}
