package com.meli.quasarchallenge.model;

import java.util.List;


/*
 * 
 * Servicio encargado del manejo de los mensajes interceptados
 * 
 */
public interface IMessageService {

	/*
	 * Se obtiene la lista de palabras detectadas y se intenta decodificar el mensaje
	 * en caso de error se avisa con una Excepción de Dominio.
	 */
	public String getMessage(List<List<String>> messageList)  throws DomainException;
	
}
