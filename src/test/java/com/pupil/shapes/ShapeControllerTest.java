package com.pupil.shapes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.pupil.exception.ShapesException;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Rollback
public class ShapeControllerTest {
	
    @Autowired
    ShapesController shapesController;
    
	@Test
	public void testIndex() {
		String result = shapesController.index();
		assertEquals("index", result);
	}
	
	@Test
	public void testGetShapes() {
		
		List<Shape> result = shapesController.getShapes();
		
		assertNotNull(result);
		assertNotEquals(0, result.size());
		assertEquals("SQUARE", result.get(0).type);
	}
	
	
	@Test
	public void testGetShapeByType() throws ShapesException {
	
		List<Shape> result = shapesController.getShapeByType("SQUARE");
		
		assertNotNull(result);
		assertNotEquals(0, result.size());
		assertEquals("Square1", result.get(0).name);
	}
	
	@Test
	public void testSaveShape() throws ShapesException {
		
		Coordinates cords = new Coordinates(10,15,20,25);
		
		Shape shape = new Shape("Square2","SQUARE");
		shape.setCoordinates(cords);
	
		ResponseEntity<String> result = shapesController.saveShape(shape);
		
		assertNotNull(result);
		assertEquals("Save success", result.getBody());
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	public void testSaveShapeWithDuplicateName() throws ShapesException {
		
		Coordinates cords = new Coordinates();
		cords.setX1(2);
		cords.setX2(5);
		cords.setY1(3);
		cords.setY2(5);
		
		Shape shape = new Shape();
		shape.setName("Square1");
		shape.setType("SQUARE");
		shape.setCoordinates(cords);
	
		ResponseEntity<String> result = shapesController.saveShape(shape);
		
		assertNotNull(result);
		assertEquals("Exception : Duplicate shape name not allowed !!! ", result.getBody());
		assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
	}

	@Test
	public void testSaveShapeWithOverlap() throws ShapesException {
		
		Coordinates cords = new Coordinates(2,5,3,5);
		
		Shape shape = new Shape("Square3","SQUARE");
		shape.setCoordinates(cords);
	
		ResponseEntity<String> result = shapesController.saveShape(shape);
		
		assertNotNull(result);
		assertEquals("Exception : Overlapping shape not allowed !!! Check coordinates !", result.getBody());
		assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
	}
}
