package com.pupil.shapes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShapesRepository extends JpaRepository<Shape, Long>{

	public List<Shape> findShapeByType(String type);

	public Shape findShapeByName(String name);

}
