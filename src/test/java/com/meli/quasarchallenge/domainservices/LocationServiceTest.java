package com.meli.quasarchallenge.domainservices;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.meli.quasarchallenge.model.DomainException;
import com.meli.quasarchallenge.model.ILocationService;
import com.meli.quasarchallenge.model.LocationSource;
import com.meli.quasarchallenge.model.Position;

@SpringBootTest
public class LocationServiceTest {

	@Autowired
	ILocationService locationService;

	@Test
	public void challengeExampleTest() throws DomainException {

		var sources = new ArrayList<LocationSource>();

		sources.add(new LocationSource(608.27,"kenobi"));
		sources.add(new LocationSource(0,"skywalker"));
		sources.add(new LocationSource(444.21,"sato"));

		var sourcePosition = locationService.getLocation(sources);	

		assertEquals(new Position(100,-100),sourcePosition);

	}

	@Test
	public void illegalSatellite() throws DomainException {

		var sources = new ArrayList<LocationSource>();

		sources.add(new LocationSource(608.27,"kenobi"));
		sources.add(new LocationSource(0,"tiePilot"));
		sources.add(new LocationSource(444.21,"sato"));

		try {
			locationService.getLocation(sources);	

		}catch (Exception ex) {
			assertEquals(ex.getMessage(),"Uno de los satélites no existe");


		}
	}

	@Test
	public void AllNoData() throws DomainException {

		var sources = new ArrayList<LocationSource>();

		try {
			locationService.getLocation(sources);	

		}catch (Exception ex) {
			assertEquals(ex.getMessage(),"No se recibió información para la fuente de transmisión");

		}

	}
	
	@Test
	public void notEnoughData() throws DomainException {

		var sources = new ArrayList<LocationSource>();

		sources.add(new LocationSource(0,"skywalker"));
		sources.add(new LocationSource(444.21,"sato"));
		
		try {
			locationService.getLocation(sources);	

		}catch (Exception ex) {
			assertEquals(ex.getMessage(),"No hay suficientes transmisiones interceptadas");

		}

	}

}
