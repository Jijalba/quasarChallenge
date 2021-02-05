package com.meli.quasarchallenge.applicationservices;

import java.util.List;

/*
 * 
 * Data Transfeer Object
 * Item para la request de la transmisión múltiple.
 *  name: Id del satélite que captó la transmisión.
 *  distance: Distancia en double de la fuente.
 *  message: Lista con las cadenas de palabras que se identificaron.
 *  
 */
public class SatelliteDto {

	String name;

	double distance;

	List<String> message;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public List<String> getMessage() {
		return message;
	}

	public void setMessage(List<String> messages) {
		this.message = messages;
	}
	
	public SatelliteDto() {
		
	}
	
	public SatelliteDto(String name,double distance, List<String> message) {
		this.name = name;
		this.message = message;
		this.distance = distance;
		
	}
	
}
