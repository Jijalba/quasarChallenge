package com.meli.quasarchallenge.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InterceptionDetailJpa extends JpaRepository<InterceptionDetailDao,Integer> {

	List<InterceptionDetailDao> findByInterceptionId(Integer interceptionId);
	
}
