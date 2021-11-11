package net.Adventures.springboot.repository;

import java.util.List;


import org.springframework.data.repository.CrudRepository;

import net.Adventures.springboot.entities.Adventure;

public interface AdventureRepository extends CrudRepository<Adventure, Integer> {
	  public List<Adventure> findByCountry(String country);
	  public List<Adventure> findByState(String state);
	  
	 /*
	 public List<Adventure> findByCity(String city);*/
	}