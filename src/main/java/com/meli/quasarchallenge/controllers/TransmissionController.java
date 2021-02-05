package com.meli.quasarchallenge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.meli.quasarchallenge.applicationservices.ITransmissionService;
import com.meli.quasarchallenge.applicationservices.TransmissionRequest;
import com.meli.quasarchallenge.applicationservices.TransmissionResponse;

/*
*
* Controller que responde al servicio /topsecret/
* POST: Detección de un mensaje por varios satélites
* 		transmisión múltiple e intento de decodificación del mismo.
* 
*/
@RestController
@RequestMapping(path = "/topsecret/")
public class TransmissionController {

	@Autowired
	ITransmissionService transmissionService;

	@PostMapping
	public ResponseEntity<TransmissionResponse> intercept(RequestEntity<TransmissionRequest> request) {

		var response = new TransmissionResponse();

		if(!request.hasBody())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se recibió transmisión");

		try 
		{
			response =	transmissionService.intercept(request.getBody());

		}
		catch(Exception ex) 
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());

		}

		return ResponseEntity.status(HttpStatus.OK).body(response); 

	}

}
