package com.pupil.shapes;

import java.util.List;

import com.pupil.exception.ShapesException;

public interface ShapesService {
	
	public List<Shape> getShapes();

	public List<Shape> getShapeByType(String shapeType);

	public Shape saveShape(Shape shape) throws ShapesException;

}
