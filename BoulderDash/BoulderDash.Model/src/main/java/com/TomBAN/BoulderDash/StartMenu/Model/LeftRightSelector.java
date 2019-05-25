package com.TomBAN.BoulderDash.StartMenu.Model;

public class LeftRightSelector<T> {
	private String[] names;
	private T[] vals;
	private int selected=0;
	public LeftRightSelector(T[] vals) {
		this.vals = vals;
		this.names = new String[vals.length];
		for(int i=0;i<vals.length;i++) {
			names[i]=vals[i].toString();
		}
	}
	public LeftRightSelector(String[] names, T[] vals) {
		this.names = names;
		this.vals = vals;
	}
	public String getSelectedString() {
		return names[selected];
	}
	public void left() {
		selected=(selected-1+vals.length)%vals.length;
	}
	public void right() {
		selected=(selected+1)%vals.length;
	}
}
