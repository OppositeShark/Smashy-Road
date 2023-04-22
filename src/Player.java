import java.awt.*;

public class Player extends Entity{
	public final double CAPTURETIME = 3.0;
	private final double turnSpeed = 0.3;
	
	private Car car;
	private boolean hasCar;
	private Vector AverageVec = new Vector(1000,1000);
	private Vector lastXY = new Vector(0,0);
	private double timeLeft = CAPTURETIME;
	private double timeAlive = 0.0;
	private boolean dead = false;
	
	public Player(Car initialCar) {
		super(new Vector(0,0), new Vector(10,10), 0.0, new Vector(0,0), 10, true);
		this.setPower(0.4);
		this.car = initialCar;
		Entity.remove(initialCar);
		this.hasCar = true;
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
		if(this.dead) {
			if(this.hasCar) {
				this.car.explode(dt);
			}
		}
		else {
			//Move
			if(this.hasCar) {
				this.car.run(dt);
			}
			else {
				this.move(dt);
			}
			//Add To Time Alive
			this.timeAlive += dt;
			//Check if Captured via average change in position
			this.AverageVec.multToThis(999.0);
			this.AverageVec.addToThis((this.lastXY.sub(this.getXY())).mult(1/dt));
			this.AverageVec.multToThis(0.001);
			if(this.AverageVec.getMagnitude() < 70) {
				this.timeLeft -= dt;
			}
			else {
				this.timeLeft = CAPTURETIME;
			}
			if(this.timeLeft < 0) {
				this.dead = true;
				this.timeLeft = 0;
			}
			this.lastXY = this.getXY();
		}
	}
	public void reset() {
		if(this.hasCar) {
			this.car.reset();
		}
		super.reset();
		this.dead = false;
		this.timeLeft = CAPTURETIME;
		this.timeAlive = 0.0;
		Entity.add(this);
	}
	public double getTimeAlive() {
		return this.timeAlive;
	}
	public double getTimeLeft() {
		return this.timeLeft;
	}
	public boolean isDead() {
		return this.dead;
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
	public Entity getPlayerObj() {
		if(this.hasCar) {
			return this.car;
		}
		else {
			return this;
		}
	}
}
