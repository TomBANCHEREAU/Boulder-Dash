package com.TomBAN.BoulderDash.Frame;

import static org.junit.Assert.assertEquals;

import java.awt.Graphics2D;
import java.util.Observable;

import javax.swing.JFrame;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

public class SimplyPanelTest {
	private SimplyPanel panel;
	private static GraphicsBuilder graphicsBuilder;
	private static Observable observable;
	private static int repainted;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		observable = new Observable() {
			@Override
			public void notifyObservers() {
				setChanged();
				super.notifyObservers();
			}
		};
		graphicsBuilder = new GraphicsBuilder() {
			@Override
			public Observable getObservable() {
				return observable;
			}
			@Override
			public void draw(Graphics2D graph, GraphicsObserver observer) {
				repainted++;
			}
		};
	}

	@After
	public void tearDown() throws Exception {
		observable.deleteObservers();
		panel=null;
		repainted=0;
	}

	@Test
	public void observableSubcriptionTest() {
		assertEquals(0, observable.countObservers());
		panel = new SimplyPanel(graphicsBuilder);
		assertEquals(1, observable.countObservers());
	}
	@Test
	public void observableReSubcriptionTest() {
		assertEquals(0, observable.countObservers());
		panel = new SimplyPanel(graphicsBuilder);
		assertEquals(1, observable.countObservers());
		panel.setGraphicsBuilder(null);;
		assertEquals(0, observable.countObservers());
		panel.setGraphicsBuilder(graphicsBuilder);;
		assertEquals(1, observable.countObservers());
		panel.setGraphicsBuilder(graphicsBuilder);;
		assertEquals(1, observable.countObservers());
	}
	@Test
	public void observableNotificationTest() {
		panel = new SimplyPanel(graphicsBuilder);
		JFrame frame = new JFrame();
		frame.setSize(100, 100);
		frame.setVisible(true);
		frame.setContentPane(panel);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
		assertEquals(1, repainted);//Creation Paint
		observable.notifyObservers();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
		assertEquals(2, repainted);//Repaint Request
	}
}


























