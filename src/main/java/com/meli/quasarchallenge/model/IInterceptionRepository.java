package com.meli.quasarchallenge.model;

import java.util.List;


/*
 * 
 * Colección de las intercepciones, pudiendo obtener su fuente y mensajes, 
 * 
 */
public interface IInterceptionRepository {


	void saveMessage(int interceptionId, List<String> message);

	int saveSource(Satellite satellite, double distance);
	
	void deprecateOldInterceptions(Satellite satellite,Integer lastId);
	
	void decodeInterceptions();
	
	List<List<String>> getUndecodedMessages();
	
	List<LocationSource> getUndecodedSources();

}
