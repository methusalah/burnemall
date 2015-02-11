package beamMyRefactor.model.items.material;

import geometry.Circle2D;
import geometry.Point2D;
import geometry.Ray2D;
import geometry.Transform2D;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Collection;
import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import tools.LogUtil;
import beamMyRefactor.model.ModelUtil;
import beamMyRefactor.model.lighting.Beam;

@Root
public class Destroyable extends AbstractPhotosensitive {
	
	private static final double HP = 100;
	private static final double DPS = 5;
	
	Circle2D initialShape;
	Circle2D shape;
	
	double inithealth;
	double health = HP;
	Color initCol;
	double lastHit = 0;
	boolean hit = false;

	public Destroyable(Point2D center, double angle, double radius) {
		super(center, angle);
		initialShape = new Circle2D(new Point2D(0, 0), radius);
		update();
		initCol = new Color(color.getRed(), color.getGreen(), color.getBlue());
		inithealth = HP;
	}
	
	@Override
	public Point2D intersect(Ray2D ray) {
		if(destroyed())
			return null;
		
		List<Point2D> i = ray.getIntersection(shape).getAll();
		if(!i.isEmpty())
			hit = true;
		
		return ModelUtil.nearest(i, ray.getStart());
	}

	@Override
	public Collection<Beam> interact(Beam beam, Point2D intersect) {
		if(lastHit != 0)
			health -= (System.currentTimeMillis()-lastHit)/1000*DPS;
		lastHit = System.currentTimeMillis();
		
		if(!destroyed())
			color = new Color(initCol.getRed()+(int)((1-health/HP)*(255-initCol.getRed())), initCol.getGreen(), initCol.getBlue());
		else
			color = Color.DARK_GRAY;
		
		return null;
	}

	@Override
	protected void update() {
		Transform2D tr = new Transform2D(coord, angle);
		shape = initialShape.getTransformed(tr);
	}

	@Override
	public void beforeTick() {
		if(!hit)
			lastHit = 0;
	}
	
	boolean destroyed(){
		return health <= 0;
	}
	
	@Override
	public Object getShape() {
		return shape;
	}
}
