package com.meli.quasarchallenge.applicationservices;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.meli.quasarchallenge.model.Position;

@SpringBootTest
public class TransmissionServiceTest {

	@Autowired
	ITransmissionService transmissionService;

	public void intercept() throws Exception {

		var request = new TransmissionRequest();

		var satellites = new ArrayList<SatelliteDto>();

		satellites.add(new SatelliteDto("kenobi",608.27,Arrays.asList("Este","","","mensaje","")));
		satellites.add(new SatelliteDto("skywalker",0,Arrays.asList("","es","","","secreto")));
		satellites.add(new SatelliteDto("sato",444.21,Arrays.asList("Este","","un","","")));

		request.setSatellites(satellites);


		var response = transmissionService.intercept(request);

		assertEquals(response,new TransmissionResponse(new Position(100,-100),"Este es un mensaje secreto"));

	}

	public void notEnoughTransmissions() throws Exception {

		var request = new TransmissionRequest();

		var satellites = new ArrayList<SatelliteDto>();

		satellites.add(new SatelliteDto("kenobi",608.27,Arrays.asList("Este","","","mensaje","")));
		satellites.add(new SatelliteDto("skywalker",0,Arrays.asList("","es","","","secreto")));

		request.setSatellites(satellites);

		try {
			transmissionService.intercept(request);
		}
		catch(Exception e) {

			assertEquals(e.getMessage(),"No se interceptaron suficientes transmisiones");
		}

	}
	
	public void notEnoughMessages() throws Exception {

		var request = new TransmissionRequest();

		var satellites = new ArrayList<SatelliteDto>();

		satellites.add(new SatelliteDto("kenobi",608.27,Arrays.asList("Este","","","mensaje","")));
		satellites.add(new SatelliteDto("skywalker",0,Arrays.asList("","es","","","secreto")));
		satellites.add(new SatelliteDto("sato",444.21,null));

		request.setSatellites(satellites);

		try {
			transmissionService.intercept(request);
		}
		catch(Exception e) {

			assertEquals(e.getMessage(),"No se interceptaron suficientes transmisiones");
		}

	}
	
	public void singleIntercept() throws Exception {

		var request = new SingleTransmissionRequest();
		
		request.setDistance(0);
		request.setMessage(Arrays.asList("Este","","","mensaje",""));

		
			transmissionService.singleIntercept("kenobi",request);
		
	}
	
	public void singleInterceptNoMessage() throws Exception {

		var request = new SingleTransmissionRequest();
		
		request.setDistance(0);
		request.setMessage(new ArrayList<String>());

		try {
			transmissionService.singleIntercept("kenobi",request);
		}
		catch(Exception e) {

			assertEquals(e.getMessage(),"La transmisión no contenía mensaje");
		}

	}
	

}
