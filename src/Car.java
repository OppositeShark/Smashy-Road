import java.util.*;
import java.util.List;
import java.awt.*;

public class Car extends Entity{
	static Random random = new Random();
	static final String[] CARNAMES = {
			"Default",
			"Police",
			"Spider",
			"Tank",
			"Motorcycle",
			
	};
	//    Width, Length, Mass, Power, Cap Speed Turn Speed, Grip, Friction
	static final double[][] CARSTATS = {
			{50.0, 35.0, 40.0, 50550.0, 700, 0.01, 40.0, -0.8},
			{50.0, 35.0, 30.0, 1400.0, 1100, 0.05, 150.0, -0.99},
			{50.0, 35.0, 30.0, 1400.0, 900, 0.05, 150.0, -0.99},
			{10.0, 40.0, 30.0, 1400.0, 900, 0.05, 150.0, -0.99},
			{}
	};
	//Array of Prisms
	static final Prism[][] CARSHAPES = new Prism[][]{
			{new Prism(-25.0,-15.0, 5.0, 50.0, 30.0, 17.0, new Color(195,0,0)),
				new Prism(-20.0,12.5, 0.0, 10.0, 5.0, 10.0, new Color(0,0,0)),
				new Prism(-20.0,-17.5, 0.0, 10.0, 5.0, 10.0, new Color(0,0,0)),
				new Prism(10.0,12.5, 0.0, 10.0, 5.0, 10.0, new Color(0,0,0)),
				new Prism(10.0,-17.5, 0.0, 10.0, 5.0, 10.0, new Color(0,0,0)),
				new Prism(-25.0,-15.0, 17.0, 30.0, 30.0, 10.0, new Color(0,0,0))},
			{new Prism(-25.0,-15.0, 5.0, 50.0, 30.0, 17.0, new Color(0,0,195)),
				new Prism(-20.0,12.5, 0.0, 10.0, 5.0, 10.0, new Color(0,0,0)),
				new Prism(-20.0,-17.5, 0.0, 10.0, 5.0, 10.0, new Color(0,0,0)),
				new Prism(10.0,12.5, 0.0, 10.0, 5.0, 10.0, new Color(0,0,0)),
				new Prism(10.0,-17.5, 0.0, 10.0, 5.0, 10.0, new Color(0,0,0)),
				new Prism(-20.0,-15.0, 17.0, 30.0, 30.0, 8.0, new Color(255,255,255)),
				new Prism(-5.0,-9.0, 25.0, 5.0, 6.0, 4.0, new Color(255,0,0)),
				new Prism(-5.0,3.0, 25.0, 5.0, 6.0, 4.0, new Color(0,0,255))},
			{new Prism(-25.0, -15.0, 10.0, 50.0, 30.0, 20.0, new Color(0,0,0)),
				new Prism(20.0, 15.0, 15.0, 5.0, 5.0, 5.0, new Color(255,0,0)),
				new Prism(20.0, -20.0, 15.0, 5.0, 5.0, 5.0, new Color(255,0,0)),
				new Prism(10.0, -20.0, 0.0, 5.0, 5.0, 10.0, new Color(0,0,0)),
				new Prism(10.0, 15.0, 0.0, 5.0, 5.0, 10.0, new Color(0,0,0)),
				new Prism(-10.0, -20.0, 0.0, 5.0, 5.0, 10.0, new Color(0,0,0)),
				new Prism(-10.0, 15.0, 0.0, 5.0, 5.0, 10.0, new Color(0,0,0)),
				new Prism(-20.0, -20.0, 0.0, 5.0, 5.0, 10.0, new Color(0,0,0)),
				new Prism(-20.0, 15.0, 0.0, 5.0, 5.0, 10.0, new Color(0,0,0))},
			{new Prism(-5.0, -20.0, 0.0, 10.0, 10.0, 20.0, new Color(0,0,255)),
				new Prism(-5.0, 10.0, 0.0, 10.0, 10.0, 20.0, new Color(0,0,255)),
				new Prism(-7.0, -22.0, 20.0, 14.0, 44.0, 30.0, new Color(0,0,255)),
				new Prism(-5.0, -5.0, 50.0, 10.0, 10.0, 10.0, new Color(255,255,0)),
				new Prism(-7.0, -29.0, 25.0, 14.0, 14.0, 25.0, new Color(255,255,0)),
				new Prism(-7.0, 22.0, 25.0, 14.0, 14.0, 25.0, new Color(255,255,0))}
			};
	private double turnspeed;
	private double grip;
	private double friction;
	Prism[] shape;
	int type;
	public Car(Vector xy, double ang, int type) {
		super(xy,new Vector(CARSTATS[type][0], CARSTATS[type][1]), ang, new Vector(0,0), CARSTATS[type][2], true);
		this.type = type;
		this.setPower(CARSTATS[type][3]);
		this.setCapSpeed(CARSTATS[type][4]);
		this.turnspeed = CARSTATS[type][5];
		this.grip = CARSTATS[type][6];
		this.friction = CARSTATS[type][7];
		this.shape = CARSHAPES[type];
	}
	public void turn(boolean right, double dt) {
		if(right) {
			super.turn(-turnspeed*this.getSpeed(),dt);
		}
		else {
			super.turn(turnspeed*this.getSpeed(),dt);
		}
	}
	@Override
	public void turn(double angle, double dt) {
		if(Math.abs(angle) < turnspeed*this.getSpeed()) {
			super.turn(angle,dt);
		}
		else {
			if(angle>0) {
				super.turn(turnspeed*this.getSpeed(),dt);
			}
			else {
				super.turn(-turnspeed*this.getSpeed(),dt);
			}
		}
	}
	@Override
	public void draw(Graphics2D g2d) {
		Graphics2D copy = (Graphics2D)g2d.create();
		copy.translate(this.getX(),this.getY());
		for(Prism p : this.shape) {
			p.draw(copy, this.getAng());
		}
		copy.dispose();
	}
	static double puffTime = 0.2;
	private double lastPuff = puffTime;
	@Override
	public void run(double dt) {
		super.run(dt);
		this.move(dt);
		this.accelerate(dt);
		//Friction
		if(Math.abs(this.getPerpSpeed()) < this.grip) {
			this.accelerate(Vector.getUnitVec(this.getAng()).rotate90().mult(this.getPerpSpeed()*-1),1);
		}
		else {
			this.accelerate(Vector.getUnitVec(this.getAng()).rotate90().mult(this.getPerpSpeed()*(this.friction)),dt);
			/*
			if(this.lastPuff < 0) {
				Vector particleXY1 = this.getXY().add(Vector.getUnitAng(this.getAng()).mult(-this.getWidth()/2));
				Vector particleSpeed = this.getV().mult(1/2);
				new Particle(particleXY1,10.0,this.getAng(),particleSpeed,100.0,1.0,new Color(125,125,125),25.0);
				new Particle(particleXY1,10.0,this.getAng()+0.3,particleSpeed,100.0,1.0,new Color(125,125,125),25.0);
				new Particle(particleXY1,10.0,this.getAng()-0.3,particleSpeed,100.0,1.0,new Color(125,50,50),25.0);
				this.lastPuff = puffTime;
			}
			*/
		}
		this.lastPuff -= dt;
		if(this.getDirSpeed() < 100) {
			if(this.lastPuff < 0) {
				Vector particleXY1 = this.getXY().add(Vector.getUnitVec(this.getAng()).mult(-this.getWidth()/2));
				Vector particleSpeed = this.getV().mult(1/2);
				new Particle(particleXY1,10.0,this.getAng(),particleSpeed,100.0,1.0,new Color(125,50,50),25.0);
				particleXY1 = this.getXY().add(Vector.getUnitVec(this.getAng()+Math.PI/6).mult(-this.getWidth()/2));
				new Particle(particleXY1,10.0,this.getAng()+0.3,particleSpeed,100.0,1.0,new Color(125,50,50),25.0);
				particleXY1 = this.getXY().add(Vector.getUnitVec(this.getAng()).mult(-this.getWidth()/2));
				new Particle(particleXY1,10.0,this.getAng()-0.3,particleSpeed,100.0,1.0,new Color(125,50,50),25.0);
				this.lastPuff = puffTime;
			}
		}
	}
	static double explodePuffTime = 0.3;
	private double lastExplodePuff = explodePuffTime;
	public void explode(double dt) {
		if(this.lastExplodePuff < 0) {
			new Particle(
					this.getXY(),
					10.0,
					random.nextDouble()*6.28,
					new Vector(0,0),
					100.0,
					5.0,
					new Color((int)(random.nextDouble()*255),0,0),
					25.0);
			this.lastExplodePuff = explodePuffTime;
		}
		this.lastExplodePuff -= dt;
	}
}