package com.meli.quasarchallenge.domainservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.quasarchallenge.model.DomainException;
import com.meli.quasarchallenge.model.IInterceptionRepository;
import com.meli.quasarchallenge.model.ISatellitesRepository;
import com.meli.quasarchallenge.model.ISingleInterceptionService;

@Service
public class SingleInterceptionService implements ISingleInterceptionService {

	@Autowired
	ISatellitesRepository satellites;

	@Autowired
	IInterceptionRepository interceptions;

	public void save(String satelliteId, double distance, List<String> message) throws DomainException {

		var interceptor = satellites.Get(satelliteId); 

		if(interceptor == null)
			throw new DomainException("El nombre del satélite no existe");

		var interceptionId = interceptions.saveSource(interceptor, distance);
		
		interceptions.saveMessage(interceptionId, message);

		interceptions.deprecateOldInterceptions(interceptor,interceptionId);

	}

}
