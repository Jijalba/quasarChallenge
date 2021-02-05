package com.meli.quasarchallenge.applicationservices;

import java.util.List;

/*
 * 
 * Request para la recepción de una transmisión única 
 * distance: Distancia en double de la fuente.
 *  message: Lista las palabras que se identificaron.
 *
 */
public class SingleTransmissionRequest {

	double distance;
	
	List<String> message;

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public List<String> getMessage() {
		return message;
	}

	public void setMessage(List<String> message) {
		this.message = message;
	}
	
	
	
}
