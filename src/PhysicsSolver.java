import java.util.ArrayList;
import java.lang.Math;

public class PhysicsSolver {
	public void solve(double dt) {
		for(int i = 0; i<Entity.entities.size(); i++) {
			Entity.entities.get(i).run(dt);
		}
		for(int i = 0; i<Entity.entities.size(); i++) {
			Entity e1 = Entity.entities.get(i);
			if(e1.hasPhysics()) {
				for(int j = i+1; j<Entity.entities.size(); j++) {
					Entity e2 = Entity.entities.get(j);
					if(e2.hasPhysics()) {
						Vector diffV = e2.getXY().sub(e1.getXY());
						double dist = diffV.getMagnitude();
						if(dist<35) {
							e1.shift(diffV.getUnitVec().mult(-(40.0-dist)/2));
							e2.shift(diffV.getUnitVec().mult((40.0-dist)/2));
						}
					}
				}
			}
		}
	}
	private static double clamp(double value, double min, double max) {
		return Math.max(min, Math.min(max, value));
	}
}
