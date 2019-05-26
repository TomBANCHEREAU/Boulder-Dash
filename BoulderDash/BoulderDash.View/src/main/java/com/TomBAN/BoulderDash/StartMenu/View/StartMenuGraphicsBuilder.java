package com.TomBAN.BoulderDash.StartMenu.View;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Observable;

import com.TomBAN.BoulderDash.Frame.GraphicsBuilder;
import com.TomBAN.BoulderDash.Frame.GraphicsObserver;
import com.TomBAN.BoulderDash.Frame.SimplyPanel;
import com.TomBAN.BoulderDash.StartMenu.Model.StartMenuModel;

public class StartMenuGraphicsBuilder implements GraphicsBuilder {
	private StartMenuModel model;
	public StartMenuGraphicsBuilder(StartMenuModel model) {
		this.model = model;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void draw(Graphics2D graph, GraphicsObserver observer) {
		graph.setColor(Color.BLACK);
		graph.fillRect(0, 0, observer.getWidth(), observer.getHeight());
		for(int i=0;i<model.getSelectorNumber();i++) {
			graph.setColor(Color.white);
			graph.drawString(model.getSelectors().get(i).getSelectedString()+"", 100, i*100+100);
			
		}
	}

	@Override
	public Observable getObservable() {
		// TODO Auto-generated method stub
		return model;
	}

	@Override
	public void setPanel(SimplyPanel panel) {
		// TODO Auto-generated method stub
		
	}

}
