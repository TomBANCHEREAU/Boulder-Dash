package com.TomBAN.Ticker;

public interface Tickable{
	public void tick();
	public default TickerStatHandler getTicker() {
		return TickerManager.get(this);
	}
	public default void atStart() {}
	public default void atExit() {}
}
