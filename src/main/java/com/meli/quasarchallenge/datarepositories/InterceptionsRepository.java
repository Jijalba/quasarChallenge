package com.meli.quasarchallenge.datarepositories;

import java.util.ArrayList;
import java.util.List;import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.meli.quasarchallenge.infrastructure.InterceptionDao;
import com.meli.quasarchallenge.infrastructure.InterceptionDetailDao;
import com.meli.quasarchallenge.infrastructure.InterceptionDetailJpa;
import com.meli.quasarchallenge.infrastructure.InterceptionJpa;
import com.meli.quasarchallenge.model.IInterceptionRepository;
import com.meli.quasarchallenge.model.LocationSource;
import com.meli.quasarchallenge.model.Satellite;

@Repository
public class InterceptionsRepository implements IInterceptionRepository {

	@Autowired
	InterceptionJpa interceptionJpa;

	@Autowired
	InterceptionDetailJpa interceptionDetailJpa;


	public void deprecateOldInterceptions(Satellite satellite,Integer lastId) {

		var interceptions = interceptionJpa.findBysatelliteIdAndDeprecated(satellite.getName(),false);

		interceptions.stream().filter(i -> i.getInterceptionId() != lastId).forEach(i -> i.setDeprecated(true));

		interceptionJpa.saveAll(interceptions);

	}

	public void decodeInterceptions() {

		var interceptions = interceptionJpa.findByDecodedAndDeprecated(false,false);

		interceptions.stream().forEach(i -> i.setDecoded(true));

		interceptionJpa.saveAll(interceptions);

	}

	public int saveSource(Satellite satellite, double distance) {

		var record = ToRecord(satellite,distance);

		interceptionJpa.save(record);

		return record.getInterceptionId();

	}

	public void saveMessage(int interceptionId, List<String> message) {

		List<InterceptionDetailDao> details = new ArrayList<InterceptionDetailDao>();


		for(String word: message)
			details.add(ToDetailRecord(interceptionId,word));


		interceptionDetailJpa.saveAll(details);

	}

	public List<List<String>> getUndecodedMessages() {

		var response =  new ArrayList<List<String>>();

		var undecoded = interceptionJpa.findByDecodedAndDeprecated(false,false);

		for(InterceptionDao interception : undecoded)
		{

			var details = interceptionDetailJpa.findByInterceptionId(interception.getInterceptionId());

			response.add(details.stream().map(d -> d.getWord()).filter(w -> w != null).collect(Collectors.toList()));

		}

		return response;

	}

	public List<LocationSource> getUndecodedSources() {

		var undecoded = interceptionJpa.findByDecodedAndDeprecated(false,false);

		var response = undecoded.stream()
				.map(i -> new LocationSource(i.getDistance(),i.getSatelliteId()))
				.collect(Collectors.toList());


		return response;

	}

	InterceptionDao ToRecord(Satellite satellite,double distance) {

		if(satellite != null)
			return new InterceptionDao(satellite,distance);

		return null;

	}

	InterceptionDetailDao ToDetailRecord(int interceptionId,String word) {

		return new InterceptionDetailDao(interceptionId,word);

	}


}
