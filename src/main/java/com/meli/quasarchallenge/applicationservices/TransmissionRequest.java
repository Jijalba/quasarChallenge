package com.meli.quasarchallenge.applicationservices;

import java.util.List;

/*
 * 
 * Request para la recepción de una transmisión múltiple 
 * satellites: Lista con la información de las diferentes 
 * 			   transmisiones detectadas
 *
 */
public class TransmissionRequest {
	
	List<SatelliteDto> satellites;

	public List<SatelliteDto> getSatellites() {
		return satellites;
	}

	public void setSatellites(List<SatelliteDto> satellites) {
		this.satellites = satellites;
	}

}
