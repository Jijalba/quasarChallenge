package com.meli.quasarchallenge.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InterceptionJpa extends JpaRepository<InterceptionDao,Integer>{

	List<InterceptionDao> findByDecoded(boolean decoded);

	List<InterceptionDao> findBysatelliteIdAndDeprecated(String satelliteId,boolean deprecated);
	
	List<InterceptionDao> findByDecodedAndDeprecated(boolean decoded,boolean deprecated);
	
}
