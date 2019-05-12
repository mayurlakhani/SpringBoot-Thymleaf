package com.game.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.game.Model.*;


@Repository
public interface CountryGameRepository extends CrudRepository<CountryGame, Integer> {


	List<CountryGame> findGameByCountryId(Integer id);
	List<CountryGame> findAll();
	List<CountryGame> findCountryByGameId(Integer gameId);
}
		

