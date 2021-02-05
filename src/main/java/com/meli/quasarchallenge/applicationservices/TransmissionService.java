package com.meli.quasarchallenge.applicationservices;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.quasarchallenge.model.IInterceptionRepository;
import com.meli.quasarchallenge.model.ILocationService;
import com.meli.quasarchallenge.model.IMessageService;
import com.meli.quasarchallenge.model.ISingleInterceptionService;
import com.meli.quasarchallenge.model.LocationServiceRequest;

@Service
public class TransmissionService implements ITransmissionService {

	@Autowired
	ILocationService locationService;

	@Autowired
	IMessageService messageService;
	
	@Autowired
	ISingleInterceptionService singleInterceptionService;
	
	@Autowired
	IInterceptionRepository interceptions;

	public TransmissionResponse intercept(TransmissionRequest request) throws  Exception{

		if(request.satellites.size() < 3)
			throw new IllegalArgumentException("No se interceptaron suficientes transmisiones");

		var messageList = request.getSatellites()
				.stream()
				.map(s -> s.getMessage())
				.filter(m -> m != null && !m.isEmpty())
				.collect(Collectors.toList());

		var locationSources = request.getSatellites()
				.stream()
				.map(s -> new LocationServiceRequest(s.getDistance(),s.getName()))
				.collect(Collectors.toList());

		if(messageList.size() < 3 || locationSources.size() < 3)
			throw new IllegalArgumentException("No se interceptaron suficientes transmisiones");	

		var interceptedMessage = messageService.getMessage(messageList);

		var sourcePosition = locationService.getLocation(locationSources);

		return new TransmissionResponse(sourcePosition,interceptedMessage);

	}

	public TransmissionResponse getInterception() throws  Exception{

		var messageList = interceptions.getUndecodedMessages();
		
		var sources = interceptions.getUndecodedSources();
		
		if(messageList.size() < 3 || sources.size() < 3)
			throw new IllegalArgumentException("No se interceptaron suficientes transmisiones");	

		var interceptedMessage = messageService.getMessage(messageList);

		var sourcePosition = locationService.getLocation(sources);

		interceptions.decodeInterceptions();
		
		return new TransmissionResponse(sourcePosition,interceptedMessage);

	}
	
	public void singleIntercept(String satelliteId, SingleTransmissionRequest request) throws Exception {
		
		var message = request.getMessage();
		
		if(message.isEmpty())
			throw new IllegalArgumentException("La transmisión no contenía mensaje");

		singleInterceptionService.save(satelliteId,request.getDistance(),request.getMessage());
			
	}

		
}
