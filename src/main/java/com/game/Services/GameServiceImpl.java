package com.game.Services;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.Model.Game;
import com.game.Model.GameDTO;
import com.game.repository.GameRepository;
@Service
public class GameServiceImpl implements GameService {

	private static final Logger logger = LoggerFactory.getLogger(GameServiceImpl.class);
	
	@Autowired
	private GameRepository gameRepository;
	
	/**
	 * Get by Id :
	 * 
	 */
	@Override
	public Game getGameById(Integer id) {
		
		return gameRepository.findOne(id);
	}
	
	/**
	 * List Order by name :
	 * 
	 */
	@Override
	public List<Game> getOrderByName() {
		
		List<Game> gamelist = null;
			
			try {
				
				gamelist = (List<Game>) gameRepository.findAllByOrderByGameNameAsc();
			} catch(Exception e) {
				
				logger.error("Exception occured when try to get all records", e);
			}
			
			return gamelist;
	}
	
	/**
	 * FIND By Id :
	 * 
	 */
	@Override
	public Game findById(Integer gameId) {
		
		return gameRepository.findOne(gameId);
	}
	/**
	 * FIND By GameName :
	 * 
	 */
	@Override
	public Game findByGameName(String gameName) {
		
		return gameRepository.findByGameName(gameName);
	}
	/**
	 * DELETE game :
	 * 
	 */
	@Override
	public boolean deleteGame(Integer id) {
		
		boolean success = true;
		try {
				
			gameRepository.delete(id);
		}  
		catch(Exception e) {
			
			logger.error("Exception occured when try to delete", e);
			success = false;
		}
		
		return success;
	}
	/**
	 * Game List :
	 * 
	 */
	@Override
	public List<Game> findAll() {
		
		return (List<Game>) gameRepository.findAll();
	}
	/**
	 * ADD game :
	 * 
	 */
	@Override
	public boolean addGame(GameDTO gameDTO) {
		
		boolean success = true;
		try {
			Game game =new Game();
			game.setGameName(gameDTO.getGameName().toUpperCase());
			gameRepository.save(game);
		} catch (Exception e) {
			
			logger.error("error while try to add game" , e);
		}
		return success;
	}
	
}
