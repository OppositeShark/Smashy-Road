import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.lang.Math;

public class SmashyRoadMain{
	static JFrame window;
	static Screen screen;
	static Listener listener;
	static PhysicsSolver solver;
	static Player player;
	
	static Random random = new Random();
	
	static int divisions = 20;
	static int policeNum = 200;
	
	public static void main(String[] args) {
		solver = new PhysicsSolver();
		
		player = new Player(new Car(new Vector(100,0), 0 , 0));
		Police.setPlayer(player);
		restart();
		
		window = new JFrame("Cop Run");
        window.setSize(1150,650);
        window.setLocation(0,0);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBackground(Color.BLUE);
        
        screen = new Screen(player);
        window.setContentPane(screen);
        
        listener = new Listener(player, screen);
		screen.addKeyListener(listener);
		screen.setDoubleBuffered(true);
		
        window.setVisible(true);
        
	}
	public static void restart() {
		Entity.clear();
		for(int i = 0; i<policeNum; i++) {
			double ang = random.nextDouble()*6.28;
			new Police(new Car(Vector.getUnitVec(ang).mult(-1000/Math.cos(ang)), ang, 1));
		}
		player.reset();
	}
	public static void run(double dt) {
		for(int i = 0; i<divisions; i++) {
			solver.solve(dt/divisions);
		}
		screen.repaint();
	}
	
}