package com.game.Services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.game.Model.CountryGame;
import com.game.Model.GameDTO;
import com.game.Model.Player;

public interface PlayerService {

	public boolean AddPlayer(MultipartFile file, GameDTO gameDTO);

	public Player getPlayerById(Integer id);
	
	public boolean deleteGame(CountryGame countryGame); 

	public List<Player> findAll();

	public boolean deleteImage(Integer id);

	public boolean updatePlayer(MultipartFile file, GameDTO gameDTO,Integer id);

	public boolean deletePlayer(Integer id);

	public List<Player> findByCountryIdAndGameId(Integer countryId, Integer gameId);
	
}