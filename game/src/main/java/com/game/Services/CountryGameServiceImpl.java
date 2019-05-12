package com.game.Services;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.Model.Country;
import com.game.Model.CountryGame;
import com.game.Model.Game;
import com.game.Model.GameDTO;
import com.game.repository.CountryGameRepository;

@Service
public class CountryGameServiceImpl implements CountryGameService{

	private static final Logger logger = LoggerFactory.getLogger(CountryGameServiceImpl.class);
	
	@Autowired
	private CountryGameRepository countryGameRepository;
	
	@Autowired
	private CountryService countryService;

	@Autowired
	private GameService gameService;
	
	@Override
	public List<CountryGame> getGameByCountryId(Integer countryId) {
		
		return countryGameRepository.findGameByCountryId(countryId);
	}

	@Override
	public List<CountryGame> findAll() {
	
		return (List<CountryGame>) countryGameRepository.findAll();
	}

	@Override
	public CountryGame findById(Integer id) {
		
		return countryGameRepository.findOne(id);
	}

	@Override
	public List<CountryGame> getCountryByGameId(Integer gameId) {
		
		return countryGameRepository.findCountryByGameId(gameId);
	}

	@Override
	public boolean delete(Integer id) {
		
		boolean success = true;
		try {
				CountryGame countryGame=countryGameRepository.findOne(id);
				if(countryGame != null) {
				countryGameRepository.delete(id);
				}
		}
		catch(Exception e) {
			success = false;
			logger.error("Exception occured when try to delete game.", e);
		}
		return success;
	}

	@Override
	public boolean addCountryGame(GameDTO gameDTO) {
			
		boolean success = true;
		Country country=countryService.findById(gameDTO.getCountryId());
		Game game1 = gameService.findByGameName(gameDTO.getGameName());
		
		try {
			 if(game1 == null) {
				 
					game1 = new Game();
					game1.setGameName(gameDTO.getGameName());
					gameService.addGame(gameDTO);
			 }
			 		CountryGame countryGame1;
					if (gameDTO.getCountryGame() == null) {
			 			
			 			countryGame1 = new CountryGame();
						countryGame1.setGame(game1);
						countryGame1.setCountry(country);
						
					} else {
						
						countryGame1=findById(gameDTO.getCountryGame().getId());
						countryGame1.setGame(game1);
						countryGame1.setCountry(country);
					}
					
					countryGameRepository.save(countryGame1);
					
		} catch(Exception e) {
			
			logger.error("exception occured while try to save record",e);
			success = false;
		}
		
		return success;
	}

}
