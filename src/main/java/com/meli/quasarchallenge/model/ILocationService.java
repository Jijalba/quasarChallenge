package com.meli.quasarchallenge.model;

import java.util.List;


/*
 * 
 * Servicio encargado del manejo de las fuentes interceptadas
 * 
 */
public interface ILocationService {

	/*
	 * Se recibe una lista de Fuentes interceptadas que contienen 
	 * el satélite encargado de dicha intercepción y la distancia del emisor
	 * 
	 * Se intenta ubicar la posición de la fuente, 
	 * en su defecto se avisa con una Excepción de Dominio
	 */
	public Position getLocation(List<LocationSource> locationsSources) throws DomainException;
	
}
