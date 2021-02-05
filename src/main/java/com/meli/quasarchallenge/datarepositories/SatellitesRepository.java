package com.meli.quasarchallenge.datarepositories;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.meli.quasarchallenge.infrastructure.SatelliteDao;
import com.meli.quasarchallenge.infrastructure.SatelliteJpa;
import com.meli.quasarchallenge.model.ISatellitesRepository;
import com.meli.quasarchallenge.model.Satellite;


@Repository
public class SatellitesRepository implements ISatellitesRepository {

	@Autowired
	SatelliteJpa satelliteJpa;

	@Cacheable("cache")
	public List<Satellite> Get(){

		return satelliteJpa.findAll()
				.stream()
				.map(s -> ToEntity(s))
				.collect(Collectors.toList());

	}

	@Cacheable(value="cache",key = "#name")
	public Satellite Get(String name){
		
		var optionalDao = satelliteJpa.findById(name);

		if(!optionalDao.isPresent())
			return null;

		var dao = optionalDao.get();
		
		return ToEntity(dao);

	}

	Satellite ToEntity(SatelliteDao dao) {

		if(dao == null)
			return null;

		return new Satellite(dao.getId(),dao.getX(),dao.getY());

	}


}
