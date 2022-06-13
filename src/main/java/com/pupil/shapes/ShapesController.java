package com.pupil.shapes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pupil.exception.ShapesException;

@RestController
public class ShapesController {
	
	@Autowired
	ShapesService shapesService;

	@GetMapping("/index")
	public String index() {
		return "index";
	}

	@GetMapping("shapes")
	public List<Shape> getShapes() {
		return shapesService.getShapes();
	}
	
	@GetMapping("/shape/{type}")
	public List<Shape> getShapeByType(@PathVariable("type") String shapeType) throws ShapesException{
		if (shapeType != null) {
		     return shapesService.getShapeByType(shapeType.toUpperCase());
		} else {
			throw new ShapesException("Shape Type can't be null !!! ");
		}
	}
	
	@PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveShape(@RequestBody Shape shape){
		try {
			Shape shapeResult = shapesService.saveShape(shape);
			if (shapeResult != null) {
				return new ResponseEntity<String>("Save success", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("Shape not saved !!!", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (ShapesException e) {
			return new ResponseEntity<String>(e.toString(), HttpStatus.BAD_REQUEST);
		}
		
	}

}
