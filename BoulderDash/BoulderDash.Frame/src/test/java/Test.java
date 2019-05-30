import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;

import javax.swing.JFrame;

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
																														// frame.getContentPane();
		frame.setContentPane(new Panel());
		frame.repaint();
		frame.addKeyListener(this);

		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		new Test();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		
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
