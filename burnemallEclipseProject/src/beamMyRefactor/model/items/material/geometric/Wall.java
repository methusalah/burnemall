package beamMyRefactor.model.items.material.geometric;

import java.util.Collection;

import geometry.Point2D;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import beamMyRefactor.model.lighting.Beam;

@Root
public class Wall extends AbstractGeometry {
	
	private static final double THICKNESS = 2.5;
	private static final double EXTENT = 40;

	public Wall(@Element(name="center")Point2D center) {
		super(center, 0);

		initialShape.addPoint(+THICKNESS, +EXTENT);
		initialShape.addPoint(+THICKNESS, -EXTENT);
		initialShape.addPoint(-THICKNESS, -EXTENT);
		initialShape.addPoint(-THICKNESS, +EXTENT);
		initialShape.close();

		update();
	}

	@Override
	public Collection<Beam> interact(Beam beam, Point2D intersect) {
		// TODO Auto-generated method stub
		return null;
	}
}