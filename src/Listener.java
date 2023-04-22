import javax.swing.*;
import java.awt.event.*;

public class Listener implements KeyListener, ActionListener{
	public final int FPS = 30;
	private final Timer timer = new Timer(1000/FPS, this);
	private boolean[] keysPressed = new boolean[2048];
	
	private Player player;
	private Screen screen;
	public Listener(Player p, Screen s) {
		player = p;
		screen = s;
		timer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		double dt = (double)timer.getDelay()/1000;
		screen.requestFocus();
		if(keysPressed[KeyEvent.VK_LEFT]) {
			player.turn(true,dt);
		}
		if(keysPressed[KeyEvent.VK_RIGHT]) {
			player.turn(false,dt);
		}
		if(keysPressed[32] && player.isDead()) {
			SmashyRoadMain.restart();
		}
		SmashyRoadMain.run(dt);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keysPressed[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keysPressed[e.getKeyCode()] = false;
	}
}
