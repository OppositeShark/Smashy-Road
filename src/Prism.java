import java.awt.*;
import java.lang.Math;

public class Prism {
	private double x;
	private double y;
	private double z;
	private double width;
	private double length;
	private double height;
	
	private Color color;
	public Prism(double x, double y, double z, double w, double l, double h, Color color){
		this.x = x;
		this.y = y;
		this.z = z;
		this.width = w;
		this.length = l;
		this.height = h;
		this.color = color;
	}
	public void grow(double n) {
		this.x -= n/2;
		this.y -= n/2;
		this.z -= n/2;
		this.width += n/2;
		this.length += n/2;
		this.height += n/2;
	}
	public void shrink(double n) {
		this.x += n/2;
		this.y += n/2;
		this.z += n/2;
		this.width -= n/2;
		this.length -= n/2;
		this.height -= n/2;
	}
	public void raise(double n) {
		this.z += n;
	}
	public void draw(Graphics2D g2d, double ang) {
		ang %= (2*Math.PI);
		if(ang<0) {
			ang += 2*Math.PI;
		}
		double[] xy1;
		double[] xy2;
		double[] xy3;
		double[] xy4;
		if(ang<Math.PI/2) {
			xy4 = rotate(this.x,this.y,ang);
			xy1 = rotate(this.x,this.y+this.length,ang);
			xy2 = rotate(this.x+this.width,this.y+this.length,ang);
			xy3 = rotate(this.x+this.width,this.y,ang);
		}
		else if(ang<Math.PI){
			xy3 = rotate(this.x,this.y,ang);
			xy4 = rotate(this.x,this.y+this.length,ang);
			xy1 = rotate(this.x+this.width,this.y+this.length,ang);
			xy2 = rotate(this.x+this.width,this.y,ang);
		}
		else if(ang<Math.PI*3/2){
			xy2 = rotate(this.x,this.y,ang);
			xy3 = rotate(this.x,this.y+this.length,ang);
			xy4 = rotate(this.x+this.width,this.y+this.length,ang);
			xy1 = rotate(this.x+this.width,this.y,ang);
		}
		else {
			xy1 = rotate(this.x,this.y,ang);
			xy2 = rotate(this.x,this.y+this.length,ang);
			xy3 = rotate(this.x+this.width,this.y+this.length,ang);
			xy4 = rotate(this.x+this.width,this.y,ang);
		}
		xy1[1] -= this.z;
		xy2[1] -= this.z;
		xy3[1] -= this.z;
		xy4[1] -= this.z;
		
		g2d.setPaint(this.color);
		g2d.fillPolygon(new int[] {(int)xy1[0],(int)xy2[0],(int)xy2[0],(int)xy1[0]},
						new int[] {(int)xy1[1],(int)xy2[1],(int)(xy2[1]-this.height),(int)(xy1[1]-this.height)},
						4);
		g2d.fillPolygon(new int[] {(int)xy2[0],(int)xy3[0],(int)xy3[0],(int)xy2[0]},
				new int[] {(int)xy2[1],(int)xy3[1],(int)(xy3[1]-this.height),(int)(xy2[1]-this.height)},
				4);
		g2d.fillPolygon(new int[] {(int)xy1[0],(int)xy2[0],(int)xy3[0],(int)xy4[0]},
				new int[] {(int)(xy1[1]-this.height),(int)(xy2[1]-this.height),(int)(xy3[1]-this.height),(int)(xy4[1]-this.height)},
				4);
	}
	private double[] rotate(double x,double y,double ang) {
		double x2 = Math.cos(ang)*x-Math.sin(ang)*y;
		double y2 = Math.sin(ang)*x+Math.cos(ang)*y;
		return new double[] {x2,y2};
	}
}
