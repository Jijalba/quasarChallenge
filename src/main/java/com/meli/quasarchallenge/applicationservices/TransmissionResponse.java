package com.meli.quasarchallenge.applicationservices;

import com.meli.quasarchallenge.model.Position;

/*
 * Response al interceptar una transmisión y 
 * decodificar su contenido y fuente
 * position: Posición de la fuente.
 * message: Cadena decodificada de los diferentes mensajes.
 * 
 */
public class TransmissionResponse {

	Position position;
	
	String message;

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public TransmissionResponse(Position sourcePosition,String message) {
		this.position = sourcePosition;
		this.message = message;
	
	}
	
	public TransmissionResponse() {
		
	}
	
}
