package com.game.Services;
import java.util.List;

import com.game.Model.CountryGame;
import com.game.Model.GameDTO;

public interface CountryGameService {

	public boolean addCountryGame(GameDTO gameDTO);

	public List<CountryGame> getGameByCountryId(Integer countryId);
	
	public List<CountryGame> getCountryByGameId(Integer gameId);
	
	public List<CountryGame> findAll();

	public CountryGame findById(Integer id);

	public boolean delete(Integer id);

	
}
