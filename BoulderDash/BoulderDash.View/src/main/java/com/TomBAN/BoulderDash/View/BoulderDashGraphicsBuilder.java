package com.TomBAN.BoulderDash.View;

import java.awt.Graphics2D;
import java.awt.Image;

import com.TomBAN.BoulderDash.Frame.GraphicsBuilder;
import com.TomBAN.BoulderDash.Frame.GraphicsObserver;
import com.TomBAN.BoulderDash.Model.Block;
import com.TomBAN.BoulderDash.Model.BoulderDashModel;
import com.TomBAN.BoulderDash.Model.Map;
import com.TomBAN.BoulderDash.Model.BlockList.Player;
import com.TomBAN.BoulderDash.Ressource.RessourceManager;

public class BoulderDashGraphicsBuilder implements GraphicsBuilder {
	private final BoulderDashModel model;
	private final Player p;
	private int currentCenterX, currentCenterY;

	public BoulderDashGraphicsBuilder(BoulderDashModel model, Player p) {
		this.model = model;
		this.p = p;
	}

	private int constrain(int val, int min, int max) {
		if (val > max) {
			return max;
		}
		if (val < min) {
			return min;
		}
		return val;
	}

	@Override
	public void draw(Graphics2D graph, GraphicsObserver observer) {
		final Image fond = RessourceManager.getInstance().getImage("BACKGROUND.png");
		// TODO Auto-generated method stub
		final int tileSize = 16;
		final double scale = 5;
		
		currentCenterX = constrain(currentCenterX,(int)( (p.getX()+0.5)*tileSize-observer.getWidth()/ scale / 6),(int)( (p.getX()+0.5)*tileSize+observer.getWidth()/ scale / 6));
		currentCenterY = constrain(currentCenterY,(int)( (p.getY()+0.5)*tileSize-observer.getHeight()/ scale / 6),(int)( (p.getY()+0.5)*tileSize+observer.getHeight()/ scale / 6));
		
		
		
		final int centerX = (int) (currentCenterX*scale);
		final int centerY = (int) (currentCenterY*scale);
		
		final int originX = (centerX - observer.getWidth() / 2);
		final int originY = (centerY - observer.getHeight() / 2);

		graph.translate(-originX, -originY);
		graph.scale(scale, scale);

		final int scaledOriginX = (int) ((centerX - observer.getWidth() / 2) / scale);
		final int scaledOriginY = (int) ((centerY - observer.getHeight() / 2) / scale);

		final int minX = scaledOriginX / tileSize - 1;
		final int minY = scaledOriginY / tileSize - 1;

		final int maxX = (minX + (int) (observer.getWidth() / scale) / tileSize + 3);
		final int maxY = (minY + (int) (observer.getHeight() / scale) / tileSize + 3);

		final Map map = model.getMap();

		for (int x = minX; x < maxX; x++) {
			for (int y = minY; y < maxY; y++) {

				graph.drawImage(fond, x * tileSize, y * tileSize, observer);

			}
		}
		for (int x = minX; x < maxX; x++) {
			for (int y = minY; y < maxY; y++) {
				final Block b = map.getBlockAt(x, y);
				if (b != null) {
					final float bx = b.getX();
					final float by = b.getY();
					final Image image = b.getImage();
					if (image != null) {
						final int Xpos = (int) (bx * tileSize + tileSize / 2 - image.getWidth(observer) / 2);
						final int Ypos = (int) (by * tileSize + tileSize / 2 - image.getHeight(observer) / 2);
						graph.drawImage(image, Xpos, Ypos, observer);
					} else {
						graph.drawRect((int) (bx * tileSize), (int) (by * tileSize), tileSize, tileSize);
					}
				}

			}
		}
		return;
	}
}
