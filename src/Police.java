import java.util.Random;
import java.awt.Graphics2D;
import java.lang.Math;
public class Police extends Entity{
	static int numPolice = 0;
	static Player player;
	
	
	private static double sharpness = 100;
	
	Car car;
	private double turnSpeed = 0.01;
	private boolean hasCar = true;
	Random r = new Random();
	
	public Police(Car car) {
		super(car.getXY(), car.getSize(), car.getAng(), car.getV(), car.getMass());
		this.car = car;
		Entity.remove(car);
		this.hasCar = true;
		numPolice += 1;
	}
	public void turn(double ang, double dt) {
		if(this.hasCar) {
			this.car.turn(ang,dt);
		}
		else {
			this.turn(ang,dt);
		}
	}
	public void turn(boolean right, double dt) {
		if(this.hasCar) {
			this.car.turn(right,dt);
		}
		else {
			if(right) {
				this.turn(this.turnSpeed,dt);
			}
			else {
				this.turn(-this.turnSpeed,dt);
			}
		}
	}
	static public void setPlayer(Player p) {
		player = p;
	}
	public Entity getPlayerObj() {
		if(this.hasCar) {
			return this.car;
		}
		else {
			return this;
		}
	}
	@Override
	public void shift(Vector v) {
		if(this.hasCar) {
			this.car.shift(v);
		}
		else {
			this.shift(v);
		}
	}
	@Override
	public double getX() {
		if(this.hasCar) {
			return this.car.getX();
		}
		else {
			return this.getX();
		}
	}
	@Override
	public double getY() {
		if(this.hasCar) {
			return this.car.getY();
		}
		else {
			return this.getY();
		}
	}
	@Override
	public Vector getXY() {
		if(this.hasCar) {
			return this.car.getXY();
		}
		else {
			return this.getXY();
		}
	}
	@Override
	public Vector getV() {
		if(this.hasCar) {
			return this.car.getV();
		}
		else {
			return this.getV();
		}
	}
	@Override
	public void draw(Graphics2D gd2) {
		if(this.hasCar) {
			this.car.draw(gd2);
		}
		else {
			;
		}
	}
	@Override
	public void run(double dt) {
		if(this.hasCar) {
			this.car.run(dt);
		}
		else {
			this.move(dt);
		}
		//Aiming for player
		double targAng = Math.atan2(player.getY()-this.getY(),player.getX()-this.getX());
		double diffAng = (targAng-this.getV().getAng())%(2*Math.PI);
		diffAng += (diffAng<-Math.PI? (2*Math.PI):(diffAng>Math.PI? (2*-Math.PI):0));
		if(Math.abs(diffAng) > 1.4) {
			this.turn(diffAng*sharpness,dt);
		}
	}
}
