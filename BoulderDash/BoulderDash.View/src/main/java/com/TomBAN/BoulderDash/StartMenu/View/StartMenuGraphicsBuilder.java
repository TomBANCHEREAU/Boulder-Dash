package com.TomBAN.BoulderDash.StartMenu.View;

import java.awt.Color;
import java.awt.Font;
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
			String str=model.getSelectors().get(i).getSelectedString();
			final int y=200+i*100;
			graph.setFont(new Font("", 0, 25));
			if(i==model.getSelectorNumber()-1) {
				str="< "+str+" >";
			}
			final int w=graph.getFontMetrics().stringWidth(str);
			graph.setColor(Color.white);
			graph.drawString(str, observer.getWidth()/2-w/2, y);
			if(i==model.getSelectorNumber()-1) {
				graph.setFont(new Font("", 0, 15));
				final String strL=model.getSelectors().get(i).getLeftString()+"";
				final String strR=model.getSelectors().get(i).getRightString()+"";
				
				final int wL = graph.getFontMetrics().stringWidth(strL);
				final int wR = graph.getFontMetrics().stringWidth(strR);
				
				final int maxW=Math.max(wL,wR);
				final int xL = observer.getWidth()/2 - w/2 - maxW/2 -50 - wL/2;
				final int xR = observer.getWidth()/2 + w/2 + maxW/2 +50 - wR/2;
				graph.setColor(Color.gray);
				graph.drawString(strR, xR, y);
				graph.drawString(strL, xL, y);
				
			}
		}
	}

	@Override
	public Observable getObservable() {
		// TODO Auto-generated method stub
		return model;
	}
//
//	@Override
//	public void setPanel(SimplyPanel panel) {
//		// TODO Auto-generated method stub
//		
//	}

}
