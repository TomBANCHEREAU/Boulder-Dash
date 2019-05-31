package com.TomBAN.BoulderDash.Game.View;

import java.awt.Graphics;

import javax.swing.JPanel;

import com.TomBAN.BoulderDash.Game.Model.BoulderDashModel;

public class EndScreenPanel extends JPanel {
	private static final long serialVersionUID = 7651533392129341629L;
	private BoulderDashModel[] models;


	public EndScreenPanel(BoulderDashModel[] models) {
		this.models = models;
	}


	@Override
	protected void paintComponent(Graphics graph) {
	}
}
