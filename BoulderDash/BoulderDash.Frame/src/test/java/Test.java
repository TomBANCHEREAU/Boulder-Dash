import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSplitPane;

import com.TomBAN.BoulderDash.Frame.GraphicsBuilder;
import com.TomBAN.BoulderDash.Frame.GraphicsObserver;
import com.TomBAN.BoulderDash.Frame.SimplyPanel;

public class Test extends Observable implements KeyListener {
	public Test() {
		// BoulderDashFrame frame = new BoulderDashFrame("aze", null, this, 1920, 1080);
		// TODO
		JFrame frame = new JFrame();
		frame.setSize(1200, 700);
		frame.setTitle("Jeu");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		Container c = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new SimplyPanel(new GA()), new SimplyPanel(new GB()));//= frame.getContentPane();
		frame.setContentPane(new Panel());
		frame.repaint();
		frame.addKeyListener(this);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		new Test();
	}

	public class GA implements GraphicsBuilder {

		public void draw(Graphics2D graph, GraphicsObserver observer) {
			// TODO Auto-generated method stub
			graph.fillRect(0, 0, observer.getWidth() / 2, 20);
		}

	}

	public class GB implements GraphicsBuilder {

		public void draw(Graphics2D graph, GraphicsObserver observer) {
			// TODO Auto-generated method stub
			graph.setBackground(Color.WHITE);

		}

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		System.out.println(arg0.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
