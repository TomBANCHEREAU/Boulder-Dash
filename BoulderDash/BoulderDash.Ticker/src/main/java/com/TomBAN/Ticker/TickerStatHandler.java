package com.TomBAN.Ticker;

public interface TickerStatHandler {
	public double getTickRate();
	public double getActualTickRate();
	public void setTickRate(double tickRate);
	public long getNanoSecPerTick();
	public int getMilliSecPerTick();
	public boolean isRunning();
//	public boolean isPaused();
//	public void pause();
//	public void unpause();
}
