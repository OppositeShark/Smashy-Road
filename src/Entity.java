import javax.swing.*;
import java.awt.*;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Comparator;

public class Entity {
	public static ArrayList<Entity> entities = new ArrayList<Entity>();
	private Vector xy;
	private Vector size;
	private double ang;
	private Vector v;
	private double mass;
	
	private boolean physics;
	
	private double power;
	private double capSpeed;
	
	//Non Physics Object
	public Entity(Vector xy, double ang, Vector v) {
		this(xy, new Vector(0,0), ang, v, 0, false);
	}
	//Physics Object
	public Entity(Vector xy, Vector size, double ang, double mass) {
		this(xy, size, ang, new Vector(0,0), mass, true);
	}
	public Entity(Vector xy, Vector size, double ang, Vector v, double mass) {
		this(xy, size, ang, v, mass, true);
	}
	//Anything Else
	public Entity(Vector xy, Vector size, double ang, Vector v, double mass, boolean physics) {
		this.xy = xy.clone();
		this.size = size.clone();
		this.ang = ang;
		this.v = v.clone();
		this.mass = mass;
		this.physics = physics;
		entities.add(this);
	}
	public void move(double dt) {
		this.xy.addToThis(v.mult(dt));
	}
	public void shift(Vector v) {
		this.xy.addToThis(v);
	}
	public void turn(double ang, double dt) {
		this.ang += ang*dt;
	}
	public void accelerate(Vector accel, double dt) {
		this.v.addToThis(accel.mult(dt));
	}
	public void accelerate(double dt) {
		double speed = this.getDirSpeed();
		if(speed <= this.capSpeed) {
			speed = 1;
		}
		this.v.addToThis(Vector.getUnitVec(this.ang).mult(this.power/speed*dt));
	}
	public static void add(Entity e) {
		entities.add(e);
	}
	public static void remove(Entity e) {
		entities.remove(e);
	}
	public static void clear() {
		entities.clear();
	}
	public void draw(Graphics2D g2d) {
		Graphics2D copy = (Graphics2D)g2d.create();
		copy.setRenderingHint(
			      RenderingHints.KEY_ANTIALIASING,
			      RenderingHints.VALUE_ANTIALIAS_ON);
		copy.translate(this.xy.getX(),this.xy.getY());
		copy.rotate(this.ang);
		copy.setPaint(Color.WHITE);
		copy.setStroke(new BasicStroke(3));
		copy.drawRect(-(int)this.size.getX()/2,-(int)this.size.getY()/2,(int)this.size.getX(),(int)this.size.getY());
	    copy.dispose();
	}
	public void run(double dt) {
		if(this.getSpeed()>this.capSpeed) {
			this.v.multToThis(0.99);
		}
	}
	public void reset() {
		this.xy = new Vector(0,0);
		this.v = new Vector(0,0);
		this.ang = 0;
	}
    public static Comparator<Entity> EntityYValComparator = new Comparator<Entity>() {
		public int compare(Entity e1, Entity e2) {
		   return Double.compare(e1.getY(), e2.getY());
		 }
	};
	public void setX(double x) {
		this.xy.setX(x);
	}
	public void setY(double y) {
		this.xy.setY(y);
	}
	public void setSize(double width, double length) {
		this.size.setX(width);
		this.size.setY(length);
	}
	public void setAng(double ang) {
		this.ang = ang;
	}
	public void setMass(double mass) {
		this.mass = mass;
	}
	public void setV(Vector v) {
		this.v = v.clone();
	}
	public void setPower(double power) {
		this.power = power;
	}
	public void setCapSpeed(double capSpeed) {
		this.capSpeed = capSpeed;
	}
	public double getX() {
		return this.xy.getX();
	}
	public double getY() {
		return this.xy.getY();
	}
	public Vector getXY() {
		return this.xy.clone();
	}
	public double getWidth() {
		return this.size.getX();
	}
	public double getLength() {
		return this.size.getY();
	}
	public Vector getSize() {
		return this.size.clone();
	}
	public double getAng() {
		return this.ang;
	}
	public Vector getV() {
		return this.v.clone();
	}
	public double getSpeed() {
		return this.v.getMagnitude();
	}
	public double getDirSpeed() {
		return Vector.dot(Vector.getUnitVec(this.ang),this.v);
	}
	public double getPerpSpeed() {
		return Vector.cross(Vector.getUnitVec(this.ang),this.v);
	}
	public double getMass() {
		return this.mass;
	}
	public boolean hasPhysics() {
		return this.physics;
	}
	public double getPower() {
		return this.power;
	}
}
