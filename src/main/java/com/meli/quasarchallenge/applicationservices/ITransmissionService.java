package com.meli.quasarchallenge.applicationservices;

/*
 * 
 * Servicio en capa de Aplicación que funciona
 *  como orquestador para el dominio.
 * Trabaja tanto con clases de esta capa como 
 *  también de la capa de dominio.
 * 
 */
public interface ITransmissionService {

	public TransmissionResponse intercept(TransmissionRequest request) throws Exception;
	
	public void singleIntercept(String satellteId, SingleTransmissionRequest request) throws Exception;
	
	public TransmissionResponse getInterception() throws Exception;
	
}
