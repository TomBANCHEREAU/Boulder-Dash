package com.TomBAN.BoulderDash.Game.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Observable;

import com.TomBAN.BoulderDash.Frame.GraphicsBuilder;
import com.TomBAN.BoulderDash.Frame.GraphicsObserver;
import com.TomBAN.BoulderDash.Frame.SimplyPanel;
import com.TomBAN.BoulderDash.Game.Model.Block;
import com.TomBAN.BoulderDash.Game.Model.BoulderDashModel;
import com.TomBAN.BoulderDash.Game.Model.Map;
import com.TomBAN.BoulderDash.Game.Model.ModelStatut;
import com.TomBAN.BoulderDash.Game.Model.BlockList.Player;
import com.TomBAN.BoulderDash.Ressource.RessourceManager;

public class BoulderDashGraphicsBuilder implements GraphicsBuilder {
	private static final int TILE_SIZE = 16;
	private SimplyPanel panel;
	private final BoulderDashModel model;
	private final int playerId;
	private int currentCenterX, currentCenterY;
	private double currentScale = 0;
	private int timeSinceWin;

	public BoulderDashGraphicsBuilder(BoulderDashModel model, int i) {
		this.model = model;
		this.playerId = i;
	}

	@Override
	public void draw(Graphics2D graph, GraphicsObserver observer) {

		if (model.getModelStatut() == ModelStatut.StartLevelScreen
				|| model.getModelStatut() == ModelStatut.WaitingStart) {
			drawLoadingScreen(graph, observer);
			return;
		}

		// TODO Auto-generated method stub
		final double time = 30;
		Player p = model.getPlayers().get(playerId);
		if (currentScale == 0) {
			currentScale = observer.getWidth() / (16 * TILE_SIZE);
		}
		if (p.hasWin()) {
			if (timeSinceWin < time) {
				timeSinceWin++;
			}
			currentScale = currentScale * (time - timeSinceWin) / time + Math.min(
					(observer.getWidth() / (model.getMap().getWidth() * TILE_SIZE + 4)) * timeSinceWin / time,
					(observer.getHeight() / (model.getMap().getHeight() * TILE_SIZE + 4)) * timeSinceWin / time);
			currentCenterX = (int) (currentCenterX * (time - timeSinceWin) / time
					+ (model.getMap().getWidth() / 2) * TILE_SIZE * timeSinceWin / time);
			currentCenterY = (int) (currentCenterY * (time - timeSinceWin) / time
					+ (model.getMap().getHeight() / 2) * TILE_SIZE * timeSinceWin / time);
		} else {
			timeSinceWin = 0;
			currentScale = observer.getWidth() / (16 * TILE_SIZE);
			currentCenterX = constrain(currentCenterX,
					(int) ((p.getX() + 0.5) * TILE_SIZE - observer.getWidth() / currentScale / 6),
					(int) ((p.getX() + 0.5) * TILE_SIZE + observer.getWidth() / currentScale / 6));
			currentCenterY = constrain(currentCenterY,
					(int) ((p.getY() + 0.5) * TILE_SIZE - observer.getHeight() / currentScale / 6),
					(int) ((p.getY() + 0.5) * TILE_SIZE + observer.getHeight() / currentScale / 6));
		}

		final double scale = currentScale;
		final int centerX = (int) (currentCenterX * scale);
		final int centerY = (int) (currentCenterY * scale);
		drawMap(graph, observer, centerX, centerY, scale);
		showGUI(graph, observer);

		if ((model.getModelStatut() == ModelStatut.EndMapScreen && model.getStatutAvancement()>50)
				|| model.getModelStatut() == ModelStatut.WaitingNextMap) {
			drawScoreScreen(graph, observer);
		}

		return;
	}

	private void drawScoreScreen(Graphics2D graph, GraphicsObserver observer) {
		final int HighScoreX = observer.getWidth()/20;
		final int HighScoreH = observer.getWidth()/4;
		final int HighScoreY = observer.getHeight()-HighScoreX-HighScoreH;
		final int HighScoreW = observer.getWidth()*9/20;
		
		graph.setColor(new Color(255, 255, 255, 200));
		graph.fillRoundRect(HighScoreX, HighScoreY, HighScoreW, HighScoreH, 10, 10);
		graph.setColor(new Color(0, 0, 0));
		graph.drawRoundRect(HighScoreX, HighScoreY, HighScoreW, HighScoreH, 10, 10);
		
		
		final int ScoreX = observer.getWidth()/20;
		final int ScoreH = observer.getWidth()/4;
		final int ScoreY = observer.getHeight()-ScoreX-ScoreH;
		final int ScoreW = observer.getWidth()*9/20;
		
		graph.setColor(new Color(255, 255, 255, 200));
		graph.fillRoundRect(ScoreX, ScoreY, ScoreW, ScoreH, 10, 10);
		graph.setColor(new Color(0, 0, 0));
		graph.drawRoundRect(ScoreX, ScoreY, ScoreW, ScoreH, 10, 10);
	}

	private void showGUI(Graphics2D graph, GraphicsObserver observer) {
		graph.setFont(new Font("", 0, observer.getWidth() / 30));
		graph.drawImage(RessourceManager.getInstance().getImage("GUI/GUI_LIFE.png"), observer.getWidth() / 100,
				observer.getHeight() / 100, observer.getWidth() / 7, observer.getWidth() / 7 * 9 / 16, observer);

		graph.drawString("x" + model.getLife(), observer.getWidth() * 8 / 100, observer.getWidth() * 6 / 100);

		graph.setFont(new Font("", 0, observer.getWidth() / 40));
		graph.drawImage(RessourceManager.getInstance().getImage("GUI/GUI_DIAMOND.png"), observer.getWidth() / 100,
				observer.getHeight() / 100 + observer.getWidth() / 7 * 9 / 16, observer.getWidth() / 7,
				observer.getWidth() / 7 * 9 / 16, observer);

		graph.drawString("x" + model.getMap().getDiamond() + "/" + model.getMap().getDiamondNeeded(),
				observer.getWidth() * 6 / 100, observer.getWidth() * 11 / 200 + observer.getWidth() / 7 * 9 / 16);

		graph.drawImage(RessourceManager.getInstance().getImage("GUI/GUI_TIME.png"),
				observer.getWidth() * 99 / 100 - observer.getWidth() / 7, observer.getWidth() / 100,
				observer.getWidth() / 7, observer.getWidth() / 7 * 9 / 16, observer);

		final int sec0 = (int) ((model.getChrono().getTimeSinceStart()/1000L)%10);
		final int sec1 = (int) ((model.getChrono().getTimeSinceStart()/10000L)%6);
		final int min0 = (int) ((model.getChrono().getTimeSinceStart()/60000L));
		
		
		graph.drawString(""+min0+":"+sec1+""+sec0,
				observer.getWidth() * 99 / 100 - observer.getWidth() / 7 + observer.getWidth()/20,
				observer.getWidth() * 6 / 100);

	}

	private void drawLoadingScreen(Graphics2D graph, GraphicsObserver observer) {

		graph.setColor(Color.BLACK);
		graph.fillRect(0, 0, observer.getWidth(), observer.getHeight());
		graph.setFont(new Font("", Font.BOLD, 30));
		graph.setColor(Color.WHITE);

		if (model.getStrMap() == null) {
			final String str = RessourceManager.getInstance().getText("Loading") + "";
			final int w = graph.getFontMetrics().stringWidth(str);
			graph.drawString(str, observer.getWidth() / 2 - w / 2, observer.getHeight() / 2);

		} else {
			final String str = model.getStrMap().getWorld() + " -  " + model.getStrMap().getLevel();
			final int w = graph.getFontMetrics().stringWidth(str);
			graph.drawString(str, observer.getWidth() / 2 - w / 2, observer.getHeight() / 2);

		}

	}

	private void drawMap(Graphics2D graph, GraphicsObserver observer, final int centerX, final int centerY,
			final double scale) {

		final int originX = (centerX - observer.getWidth() / 2);
		final int originY = (centerY - observer.getHeight() / 2);

		graph.translate(-originX, -originY);
		graph.scale(scale, scale);

		final int scaledOriginX = (int) ((centerX - observer.getWidth() / 2) / scale);
		final int scaledOriginY = (int) ((centerY - observer.getHeight() / 2) / scale);

		final int minX = scaledOriginX / TILE_SIZE - 2;
		final int minY = scaledOriginY / TILE_SIZE - 2;

		final int maxX = (minX + (int) (observer.getWidth() / scale) / TILE_SIZE + 4);
		final int maxY = (minY + (int) (observer.getHeight() / scale) / TILE_SIZE + 4);

		for (int x = minX; x < maxX; x++) {
			for (int y = minY; y < maxY; y++) {
				graph.drawImage(model.getMap().getBackgroundImage(), x * TILE_SIZE, y * TILE_SIZE, observer);
			}
		}
		for (int x = minX; x < maxX; x++) {
			for (int y = minY; y < maxY; y++) {
				final Block b = model.getMap().getBlockAt(x, y);
				if (b != null) {
					final float bx = b.getX();
					final float by = b.getY();
					final Image image = b.getImage();
					if (image != null) {
						final int Xpos = (int) (bx * TILE_SIZE + TILE_SIZE / 2 - image.getWidth(observer) / 2);
						final int Ypos = (int) (by * TILE_SIZE + TILE_SIZE / 2 - image.getHeight(observer) / 2);
						graph.drawImage(image, Xpos, Ypos, observer);
					} else {
						graph.drawRect((int) (bx * TILE_SIZE), (int) (by * TILE_SIZE), TILE_SIZE, TILE_SIZE);
					}
				}

			}
		}
		graph.scale(1 / scale, 1 / scale);
		graph.translate(+originX, +originY);
	}

	@Override
	public Observable getObservable() {
		return model;
	}

	private static int constrain(int val, int min, int max) {
		if (val > max) {
			return max;
		}
		if (val < min) {
			return min;
		}
		return val;
	}

	public void setPanel(SimplyPanel panel) {
		this.panel = panel;
	}
}
