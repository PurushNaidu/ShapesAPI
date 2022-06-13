package com.pupil.exception;

public class ShapesException extends Exception {

	private static final long serialVersionUID = 1L;
	String message;

	public ShapesException(String message) {
		this.message = message;
	}

	public String toString() {
		return ("Exception : " + message);
	}
}