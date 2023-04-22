import java.awt.*;

public class Particle extends Entity{
	private double time;
	private double increaseBy;
	private double zSpeed;
	private Color color;
	private Prism prism;
	public Particle(Vector xy, double size, double ang, Vector v, double zSpeed, double time, Color color, double increaseBy) {
		super(xy, ang, v);
		this.prism = new Prism(-size/2, -size/2, 0, size, size, size, color);
		this.time = time;
		this.increaseBy = increaseBy;
		this.zSpeed = zSpeed;
		this.color = color;
	}
	@Override
	public void draw(Graphics2D g2d) {
		Graphics2D copy = (Graphics2D)g2d.create();
		copy.translate(this.getX(),this.getY());
		this.prism.draw(copy, this.getAng());
		copy.dispose();
	}
	@Override
	public void run(double dt) {
		this.move(dt);
		//this.shift(Vector.getUnitAng(this.getAng()).mult(this.increaseBy*dt/2));
		
		this.prism.raise(this.zSpeed*dt);
		this.prism.grow(this.increaseBy*dt);
		this.time -= dt;
		if(this.time < 0) {
			Entity.remove(this);
		}
	}
}
