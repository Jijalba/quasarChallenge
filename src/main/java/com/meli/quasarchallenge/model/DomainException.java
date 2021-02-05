package com.meli.quasarchallenge.model;

/*
 * 
 * Excepción por regla de dominio.
 * 
 */
@SuppressWarnings("serial")
public class DomainException extends Exception{

	public DomainException(String reasonPhrase) {

		super(reasonPhrase);

	}

}
