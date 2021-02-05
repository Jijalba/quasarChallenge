package com.meli.quasarchallenge.model;

/*
 * 
 * Entidad Satélite detector de transmisiones
 * 
 * name: Id del satélite.
 * position: Posición en el plano del mismo
 * 			 x,y
 * 
 */
public class Satellite {

	String name;
	
	Position position;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	
	public Satellite(String name, double x, double y) {
		this.name = name;
		this.position = new Position(x,y);
	}
	
}
