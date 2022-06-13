package com.pupil.shapes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pupil.exception.ShapesException;

@Service
public class ShapesServiceImpl implements ShapesService{
	
	@Autowired
	public ShapesRepository shapesRepository;
	
	@Override
	public List<Shape> getShapes() {
		return shapesRepository.findAll();
	}

	@Override
	public List<Shape> getShapeByType(String shapeType) {
		return shapesRepository.findShapeByType(shapeType);
	}

	@Override
	public Shape saveShape(Shape inputShape) throws ShapesException {
		
		// check if input shape has any null
		if (shapeHasNullValues(inputShape))
			throw new ShapesException("Check input, Null values not allowed !!! ");
		
		// check if duplicate name
		Shape duplicateShape = shapesRepository.findShapeByName(inputShape.getName());
		
		if (duplicateShape != null) {
			throw new ShapesException("Duplicate shape name not allowed !!! ");
		}
		
		// Swap if coordinates are incorrectly given.
		checkAndFixCoordinates(inputShape);
		
		// check if overlapping shape		
		List<Shape> shapesList = shapesRepository.findShapeByType(inputShape.getType().toUpperCase());
		
		Shape overlappingShape = shapesList.stream()
				.filter(shapeCursor -> (inputShape.getCoordinates().getX1() < shapeCursor.getCoordinates().getX2()
						&& inputShape.getCoordinates().getX2() > shapeCursor.getCoordinates().getX1()
						&& inputShape.getCoordinates().getY1() < shapeCursor.getCoordinates().getY2()
						&& inputShape.getCoordinates().getY2() > shapeCursor.getCoordinates().getY1()))
				.findFirst().orElse(null);
		
		if (overlappingShape != null) {
			throw new ShapesException("Overlapping shape not allowed !!! Check coordinates !");
		}
		
		// update shape type to in upper case to avoid case sensitive issues
		inputShape.setType(inputShape.getType().toUpperCase());
		
		// insert into shapes
		return shapesRepository.save(inputShape);
		
	}
	
	public boolean shapeHasNullValues(Shape shape) {
		if (shape != null) {
			if (shape.getName() == null || shape.getType() == null || shape.getCoordinates() == null ) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}
	
	void checkAndFixCoordinates(Shape shape) {
		Coordinates cords = shape.getCoordinates();
		// X1 must be less than X2
		if (cords.getX1() > cords.getX2()) {
			int temp = cords.getX1();
			cords.setX1(cords.getX2());
			cords.setX2(temp);
		}

		// Y2 must be less than Y1
		if (cords.getY1() < cords.getY2()) {
			int temp = cords.getY2();
			cords.setY2(cords.getY1());
			cords.setY2(temp);
		}
	}
}
