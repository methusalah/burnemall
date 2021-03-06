package beamMyRefactor.model.items.material.geometric;

import geometry.Point2D;
import geometry.Ray2D;

import java.util.ArrayList;
import java.util.Collection;

import math.Angle;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import beamMyRefactor.model.lighting.Beam;


@Root
public class Reflector extends AbstractGeometry {

	
	public Reflector(@Element(name="angle") double angle) {
		this(Point2D.ORIGIN, angle);
	}
	public Reflector(Point2D coord, double angle) {
		super(coord, angle);
	}


	@Override
	public Collection<Beam> interact(Beam beam, Point2D intersect) {
		ArrayList<Beam> res = new ArrayList<>();
		double incident = Angle.getOrientedDifference(collisionNormal, beam.getRay().getAngle()+Angle.FLAT);
		Beam b = new Beam(beam);
		b.setRay(new Ray2D(intersect, collisionNormal-incident));
		res.add(b);
		return res;
	}
}
