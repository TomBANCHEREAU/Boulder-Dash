package com.TomBAN.Ticker;

import java.util.ArrayList;

public final class TickerManager {
	public static double DEFAULT_TICKRATE = 60;
	public static int DEFAULT_NANO_SLEEP_TIME = 10000;
	private static ArrayList<Ticker> allTickers = new ArrayList<Ticker>();
	private static int LastTickerID = 0;

	public synchronized static void addTicker(Tickable newTickerInterface) throws NullPointerException {
		addTicker(newTickerInterface, "Ticker" + (LastTickerID), new Object());
	}

	public synchronized static void addTicker(Tickable newTickerInterface, String name)
			throws NullPointerException {
		addTicker(newTickerInterface, name, new Object());
	}

	public synchronized static void addTicker(Tickable newTickerInterface, Object syncKey)
			throws NullPointerException {
		addTicker(newTickerInterface, "Ticker" + (LastTickerID), syncKey);
	}

	public synchronized static void addTicker(Tickable newTickerInterface, String name, Object syncKey)
			throws NullPointerException {
		if (newTickerInterface == null) {
			throw new NullPointerException();
		} else {
			allTickers.add(new Ticker(newTickerInterface, name, syncKey, LastTickerID));
			LastTickerID++;
		}
	}

	public synchronized static TickerStatHandler get(Tickable a) {
		return getTicker(a);
	}

	private synchronized static Ticker getTicker(Tickable a) {
		for (Ticker t : allTickers) {
			if (t.getTicker() == a) {
				return t;
			}
		}
		return null;
	}

	public synchronized static void start(Tickable a) {
		Ticker t = getTicker(a);
		if (t != null) {
			t.start();
		}
	}

	public synchronized static void stop(Tickable a) {
		Ticker t = getTicker(a);
		if (t != null) {
			t.stop();
		}
	}

	public synchronized static void stopAll() {
		for (Ticker t : allTickers) {
			t.stop();
		}
		int i = 0;
		while (allTickers.size() > 0) {

			if (!allTickers.get(i).isRunning()) {
				allTickers.remove(i);
			}

			i++;
			if (i >= allTickers.size()) {
				i = 0;
			}
		}
	}

	public synchronized static void kill(Tickable a) {
		Ticker t = getTicker(a);
		if (t != null) {
			t.getThread().interrupt();
			allTickers.remove(t);
		}
	}

	public synchronized static void killAll() {
		while (allTickers.size() > 0) {
			allTickers.get(0).getThread().interrupt();
			allTickers.remove(0);
		}
	}
}
