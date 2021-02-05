package com.meli.quasarchallenge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.meli.quasarchallenge.applicationservices.ITransmissionService;
import com.meli.quasarchallenge.applicationservices.TransmissionRequest;
import com.meli.quasarchallenge.applicationservices.TransmissionResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/*
*
* Controller que responde al servicio /topsecret/
* POST: Detección de un mensaje por varios satélites
* 		transmisión múltiple e intento de decodificación del mismo.
* 
*/
@RestController
@RequestMapping(path = "/topsecret/")
@Api(description = "Recepción de tramisiones múltiples bajo el servicio /topsecret/")
public class TransmissionController {

	@Autowired
	ITransmissionService transmissionService;

	@ApiOperation(value = "Recepción de una transmisión múltiple e intento de decodificación de la misma")
	@PostMapping
	public ResponseEntity<TransmissionResponse> intercept(@RequestBody TransmissionRequest request) {

		var response = new TransmissionResponse();

		if(request == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se recibió transmisión");

		try 
		{
			response =	transmissionService.intercept(request);

		}
		catch(Exception ex) 
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());

		}

		return ResponseEntity.status(HttpStatus.OK).body(response); 

	}

}
