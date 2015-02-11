package beamMyRefactor.model.items.material;

import geometry.Circle2D;
import geometry.Point2D;
import geometry.Ray2D;
import geometry.Transform2D;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Collection;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import beamMyRefactor.model.ModelUtil;
import beamMyRefactor.model.lighting.Beam;

@Root
public class Goal extends AbstractPhotosensitive {
	
	Circle2D shape;
	boolean hit = true;

	public Goal(@Element(name="center") Point2D center) {
		super(center, 0);
		update();
	}

	@Override
	public Point2D intersect(Ray2D beam) {
		return ModelUtil.nearest(beam.getIntersection(shape).getAll(), beam.getStart());
	}

	@Override
	public Collection<Beam> interact(Beam beam, Point2D intersect) {
		hit = true;
		return null;
	}

	@Override
	protected void update() {
		shape = new Circle2D(new Point2D(0, 0), 5);
		Transform2D tr = new Transform2D(coord, angle);
		shape = shape.getTransformed(tr);
	}

	@Override
	public void beforeTick() {
		hit=false;
	}
	
	@Override
	public Object getShape() {
		return shape;
	}
}
