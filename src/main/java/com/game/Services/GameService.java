package com.game.Services;

import java.util.List;

import com.game.Model.Game;
import com.game.Model.GameDTO;

public interface GameService {

	public boolean addGame(GameDTO gameDTO);

	public Game getGameById(Integer id);

	public List<Game> getOrderByName();

	public Game findById(Integer gameId);
	
	public Game findByGameName(String gameName);

	public boolean deleteGame(Integer id);

	public List<Game> findAll();

}
