package com.TomBAN.Ticker;

import java.util.ArrayList;

class Ticker implements Runnable, TickerStatHandler {
	private Object syncKey;
	private Thread thread;
	private boolean running = false;
	private boolean stoping = false;
	private boolean paused = false;
	private double tickRate;
	private Tickable ticker;
	private ArrayList<Long> NSPTs;
	private ArrayList<Long> ticks;
	private long startMillis;
	private int ID;
	{
		thread = new Thread(this);
		NSPTs = new ArrayList<Long>();
		ticks = new ArrayList<Long>();
		tickRate = TickerManager.DEFAULT_TICKRATE;
	}

	public Ticker(Tickable tickerInterface, String threadName, Object syncKey, int ID) {
		ticker = tickerInterface;
		this.syncKey = syncKey;
		thread.setName(threadName);
		this.ID = ID;
	}

	public Tickable getTicker() {
		synchronized (syncKey) {
			return ticker;
		}
	}

	public int getMilliSecPerTick() {
		synchronized (syncKey) {
			return (int) Math.floor(getNanoSecPerTick() / 1000000);
		}
	}

	public void start() {
		synchronized (syncKey) {
			if (!thread.isAlive()) {
				thread.start();
				return;
			}
			System.err.println("Thread\"" + thread.getName() + "\" already started");
		}
	}

	public Thread getThread() {
		return thread;
	}

	public double getActualTickRate() {
		synchronized (syncKey) {
			if (ticks.size() > 1) {
				long totalTime = ticks.get(ticks.size() - 1) - ticks.get(0);
				return (double) ((ticks.size() - 1) * 1000000000.0 / totalTime);
			}
			return getTickRate();
		}
	}

	public long getNanoSecPerTick() {
		synchronized (syncKey) {
			long sum = 0;
			for (long l : NSPTs) {
				sum += l;
			}
			return (long) ((NSPTs.size()!=0)? sum / NSPTs.size() : 1000000000/getTickRate());
		}
	}

	public void run() {
		synchronized (syncKey) {
			running = true;
			ticker.atStart();
		}
		boolean done = false;
		double LastTickStartTime = System.nanoTime();
		startMillis = System.currentTimeMillis();
		while (!done) {
			synchronized (syncKey) {
				if (stoping) {
					done = true;
				} else if (!paused) {
					if (LastTickStartTime + 1000000000.0 / getTickRate() - System.nanoTime() < 0) {
						do {
							LastTickStartTime += 1000000000.0 / getTickRate();
						} while (LastTickStartTime + 1000000000.0 / getTickRate() - System.nanoTime() < 0);
						ticks.add(System.nanoTime());
						if (ticks.size() > getTickRate() + 2) {
							ticks.remove(0);
						}
						ticker.tick();
						NSPTs.add(System.nanoTime() - ticks.get(ticks.size() - 1));
						if (NSPTs.size() > getTickRate() + 2) {
							NSPTs.remove(0);
						}
						if ((int) (1000 / tickRate - NSPTs.get(NSPTs.size() - 1)) - 1000 > 0) {
							try {
								Thread.sleep(0,(int) ((1000 / tickRate - NSPTs.get(NSPTs.size() - 1)) - 1000));
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
			try {
				Thread.sleep(0, TickerManager.DEFAULT_NANO_SLEEP_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		synchronized (syncKey) {
			ticker.atExit();
			running = false;
		}
	}

	public boolean isRunning() {
		synchronized (syncKey) {
			return running;
		}
	}

	public void stop() {
		synchronized (syncKey) {
			stoping = true;
		}
	}

	public double getTickRate() {
		synchronized (syncKey) {
			return tickRate;
		}
	}

	public void setTickRate(double tickRate) {
		synchronized (syncKey) {
			this.tickRate = tickRate;
		}
	}

	public int getID() {
		return ID;
	}

	@Override
	public long getMillisSinceStart() {
		synchronized (syncKey) {
			return System.currentTimeMillis()-startMillis;
		}
	}
}
