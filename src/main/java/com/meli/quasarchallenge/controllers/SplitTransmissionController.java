package com.meli.quasarchallenge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.meli.quasarchallenge.applicationservices.ITransmissionService;
import com.meli.quasarchallenge.applicationservices.SingleTransmissionRequest;
import com.meli.quasarchallenge.applicationservices.TransmissionResponse;
import com.meli.quasarchallenge.model.DomainException;

/*
 *
 * Controller que responde al servicio /topsecret_split/
 * POST: Detección de un mensaje por un satélite
 * 		 Transmisión única.
 * GET: Decodificación de los últimos mensajes que no fueron
 * 		decodificados o deprecados por antiguedad.
 */
@RestController
@RequestMapping(path = "/topsecret_split/")
public class SplitTransmissionController {

	@Autowired
	ITransmissionService transmissionService;

	@RequestMapping(value="{satellite_name}",method = RequestMethod.POST)
	public ResponseEntity<String> singleIntercept(@PathVariable("satellite_name")String satelliteId, RequestEntity<SingleTransmissionRequest> request) {

		if(!request.hasBody())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se recibió transmisión");

		try {
			transmissionService.singleIntercept(satelliteId,request.getBody());

		}
		catch(Exception ex) 
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());

		}

		return ResponseEntity.ok("Transmisión interceptada con éxito"); 

	}

	@GetMapping
	public ResponseEntity<TransmissionResponse> getInterception(){

		var response = new TransmissionResponse();
		
		try {
			response  = transmissionService.getInterception();

		}
		catch(Exception ex)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

}
