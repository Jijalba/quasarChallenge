package com.meli.quasarchallenge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.meli.quasarchallenge.applicationservices.ITransmissionService;
import com.meli.quasarchallenge.applicationservices.SingleTransmissionRequest;
import com.meli.quasarchallenge.applicationservices.TransmissionResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
@Api(description = "Recepción de tramisiones únicas bajo el servicio /topsecret_split/ y decodificación de las mismas")
public class SplitTransmissionController {

	@Autowired
	ITransmissionService transmissionService;

	@ApiOperation(value = "Intercepción mediante un satélite de una transmisión única")
	@RequestMapping(value="{satellite_name}",method = RequestMethod.POST)
	public ResponseEntity<String> singleIntercept(@PathVariable("satellite_name")String satelliteId,@RequestBody SingleTransmissionRequest request) {

		if(request == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se recibió transmisión");

		try {
			transmissionService.singleIntercept(satelliteId,request);

		}
		catch(Exception ex) 
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());

		}

		return ResponseEntity.ok("Transmisión interceptada con éxito"); 

	}

	@ApiOperation(value = "Intento de decodificación de las últimas transmisiones únicas recibidas")
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
