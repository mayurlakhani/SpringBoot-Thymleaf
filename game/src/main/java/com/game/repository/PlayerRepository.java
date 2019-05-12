package com.game.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.game.Model.Country;
import com.game.Model.Game;
import com.game.Model.Player;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer> {

	public List<Player> findByCountryAndGame(Country country, Game game);
	
	public List<Player> findByCountryIdAndGameId(Integer countryId, Integer gameId);
	
	public List<Player> findByImageId(Integer imageId);

	
	
}
		

