import java.lang.Math;
public class Vector {
	private double x;
	private double y;
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public static double dot(Vector v1, Vector v2) {
		return (v1.x*v2.x) + (v1.y*v2.y); 
	}
	public double dot(Vector v2) {
		return (this.x*v2.x) + (this.y*v2.y); 
	}
	public static double cross(Vector v1, Vector v2) {
		return (v1.x*v2.y) - (v1.y*v2.x); 
	}
	public double cross(Vector v2) {
		return (this.x*v2.y) + (this.y*v2.x); 
	}
	public Vector clone() {
		return new Vector(this.x,this.y);
	}
	public static Vector add(Vector v1, Vector v2) {
		return new Vector(v1.x+v2.x, v1.y+v2.y);
	}
	public Vector add(Vector v2) {
		return new Vector(this.x+v2.x, this.y+v2.y);
	}
	public void addToThis(Vector v2) {
		this.x += v2.getX();
		this.y += v2.getY();
	}
	public static Vector sub(Vector v1, Vector v2) {
		return new Vector(v1.x-v2.x, v1.y-v2.y);
	}
	public Vector sub(Vector v2) {
		return new Vector(this.x-v2.x, this.y-v2.y);
	}
	public void subToThis(Vector v2) {
		this.x -= v2.getX();
		this.y -= v2.getY();
	}
	public static Vector mult(Vector v1, double n) {
		return new Vector(v1.getX()*n, v1.getY()*n);
	}
	public Vector mult(double n) {
		return new Vector(this.x*n, this.y*n);
	}
	public void multToThis(double n) {
		this.x *= n;
		this.y *= n;
	}
	public static Vector getUnitVec(double ang) {
		return new Vector(Math.cos(ang),Math.sin(ang));
	}
	public Vector getUnitVec() {
		double mag = this.getMagnitude();
		if(mag == 0) {
			return new Vector(1,0);
		}
		else {
			return new Vector(this.x/mag,this.y/mag);
		}
	}
	public Vector rotate90() {
		return new Vector(-this.y,this.x);
	}
	public double getAng() {
		return Math.atan2(this.y, this.x);
	}
	public double getMagnitude() {
		return Math.sqrt(Math.pow(this.getX(),2)+Math.pow(this.getY(),2));
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double x) {
		this.x = y;
	}
	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}
}
