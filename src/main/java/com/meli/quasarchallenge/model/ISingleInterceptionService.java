package com.meli.quasarchallenge.model;

import java.util.List;

/*
 * 
 * Servicio para guardar una intercepción única.
 * 
 */
public interface ISingleInterceptionService {

	/*
	 * 
	 * Guarda en base de datos los datos de la transmisión para poder
	 * ser decodificados cuando se solicite.
	 * 
	 * Depreca todas las transmisiones previas que detectó el satélite
	 * considerando que estas ya no son fiables.
	 * 
	 */
	public void save(String satelliteId,double distance, List<String> message) throws DomainException;
	
}
