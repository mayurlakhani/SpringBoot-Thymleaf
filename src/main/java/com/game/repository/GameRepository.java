package com.game.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.game.Model.*;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {

	public List<Game> findAllByOrderByGameNameAsc();
	
	public Game findByGameName(String gameName);
	
	public List<Game> findAll();

}
		

