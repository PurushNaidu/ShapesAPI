package com.pupil.shapes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Coordinates {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;

	public int X1;
	public int X2;
	public int Y1;
	public int Y2;

	public Coordinates() {

	}

	public Coordinates(int X1, int X2, int Y1, int Y2) {
		this.X1 = X1;
		this.X2 = X2;
		this.Y1 = Y1;
		this.Y2 = Y2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getX1() {
		return X1;
	}

	public void setX1(int x1) {
		X1 = x1;
	}

	public int getX2() {
		return X2;
	}

	public void setX2(int x2) {
		X2 = x2;
	}

	public int getY1() {
		return Y1;
	}

	public void setY1(int y1) {
		Y1 = y1;
	}

	public int getY2() {
		return Y2;
	}

	public void setY2(int y2) {
		Y2 = y2;
	}

}
