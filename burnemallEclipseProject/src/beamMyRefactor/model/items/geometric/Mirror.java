package beamMyRefactor.model.items.geometric;

import geometry.Point2D;
import geometry.Ray2D;
import geometry.Segment2D;
import geometry.Transform2D;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.Collection;

import math.Angle;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import beamMyRefactor.model.Beam;
import beamMyRefactor.util.Util;

@Root
public class Mirror extends Reflector {

	@Element
	private double width;
	
	public Mirror(@Element(name="center") Point2D center, @Element(name="width") double width, @Element(name="angle") double angle) {
		super(center, angle);
		this.width=width;
		
		initialShape.addPoint(0, width/5);
		initialShape.addPoint(-width/2, 0);
		initialShape.addPoint(width/2, 0);
		initialShape.close();
		update();
	}
}
