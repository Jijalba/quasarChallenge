package com.meli.quasarchallenge.model;


/*
 * 
 * Entidad que modela la detección de una fuente de transmisión.
 * distance: Distancia de la fuente al satélite que la interceptó.
 * satelliteName: Id del satélite que interceptó la transmisión.
 * 
 */
public class LocationSource {

	double distance;

	String satelliteName;

	
	public double getDistance() {
		return distance;
	}


	public void setDistance(double distance) {
		this.distance = distance;
	}


	public String getSatelliteName() {
		return satelliteName;
	}


	public void setSatelliteName(String satelliteName) {
		this.satelliteName = satelliteName;
	}


	public LocationSource(double distance, String satelliteName) {
		this.distance = distance;
		this.satelliteName = satelliteName;
	
	}
	
}
