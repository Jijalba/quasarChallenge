package com.meli.quasarchallenge.domainservices;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.meli.quasarchallenge.model.DomainException;
import com.meli.quasarchallenge.model.ISingleInterceptionService;

@SpringBootTest
public class SingleInterceptionServiceTest {


	@Autowired
	ISingleInterceptionService singleInterceptionService;

	@Test
	public void IllegalSatellite() {

		var satelliteId = "dVader";
		var distance = 25.0;
		List<String> message = Arrays.asList("I","am","","father");

		try {
			singleInterceptionService.save(satelliteId, distance, message);

		}
		catch(Exception e) {

			assertEquals(e.getMessage(),"El nombre del satélite no existe");
		}

	}

	@Test
	public void saveInterception() throws DomainException {

		var satelliteId = "sato";
		var distance = 25.0;
		List<String> message = Arrays.asList("This","","the","way");

			singleInterceptionService.save(satelliteId, distance, message);

	}

}
