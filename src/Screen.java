import java.awt.*;
import java.awt.geom.AffineTransform;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;

public class Screen extends JPanel{
	/**
	private double angle = 45;
	private double scaley = 1;
	private double scalex = 1;
	private AffineTransform transform = new AffineTransform(
			scalex*-Math.cos(angle),scalex*Math.sin(angle),1150/2,
			scaley*Math.sin(angle),scaley*Math.cos(angle),650/2);
	private Graphics2D g2d;
	**/
	Player player;
	public Screen(Player player) {
		super();
		this.player = player;
	}
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g.create();
		double percentCapture = player.getTimeLeft()/player.CAPTURETIME;
		g2d.setPaint(new Color(
				(int)(255*percentCapture),
				0,
				0)
				);
		g2d.fillRect(0,0,this.getWidth(),this.getHeight());
		//Center On Player
		g2d.scale(1, 0.5);
		g2d.translate((int)(-this.player.getX() + this.getWidth()/2),(int)(-this.player.getY() + this.getHeight()));
		//Grid
		int d = 500;
		int leftX = (int)(this.player.getX() - this.getWidth()/2);
		int rightX = (int)(this.player.getX() + this.getWidth()/2);
		int topY = (int)(this.player.getY() - this.getHeight());
		int bottomY = (int)(this.player.getY() + this.getHeight());
		int xShift = -(int)this.player.getX()%d;
		int yShift = -(int)this.player.getY()%(2*d);
		
		g2d.setPaint(new Color(
				(int)(255*(1.0-percentCapture)),
				0,
				0)
				);
		g2d.setStroke(new BasicStroke(2));
		for(int i = -this.getWidth()*2-d; i < this.getWidth()*2+d; i+=d) {
			g2d.drawLine(i+leftX+xShift,topY+yShift-2*d,i+leftX+this.getHeight()*2+xShift+4*d,bottomY+yShift+2*d);
			g2d.drawLine(i+leftX+xShift+this.getWidth(),topY+yShift-2*d,i+leftX-this.getHeight()*2+xShift-4*d+this.getWidth(),bottomY+yShift+2*d);
		}

		g2d.setPaint(Color.WHITE);
		g2d.setStroke(new BasicStroke(3));
		//Drawing Entities
		Collections.sort(Entity.entities, Entity.EntityYValComparator);
		for(int i = 0; i<Entity.entities.size(); i++) {
			Entity.entities.get(i).draw(g2d);
		}
		//Timer
		g2d.setTransform(new AffineTransform());
		g2d.setPaint(Color.WHITE);
		g2d.setFont(new Font("Microsoft YaHei", Font.PLAIN, 50));
		g2d.drawString(String.format("%.1f",player.getTimeAlive()), 820, 90);
		//Dead Text
		if(player.isDead()) {
			g2d.setPaint(Color.RED);
			g2d.setFont(new Font("Microsoft YaHei", Font.PLAIN, 100));
			g2d.drawString("Time Alive: "+String.format("%.1f",player.getTimeAlive()), 100, 700);
			g2d.setFont(new Font("Microsoft YaHei", Font.PLAIN, 40));
			g2d.drawString("Press [Space] to Restart", 100, 780);
			g2d.rotate(0.4);
			g2d.setPaint(Color.RED);
			g2d.setFont(new Font("Microsoft YaHei", Font.PLAIN, 200));
			g2d.drawString("Dead", 920, -100);
		}

		g2d.dispose();
	}
}