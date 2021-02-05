package com.meli.quasarchallenge.model;

import java.util.List;


/*
 * 
 * Colección de Satélites del Modelo de Dominio
 * 
 */
public interface ISatellitesRepository {

	public List<Satellite> Get();

	public Satellite Get(String name);
	
}
