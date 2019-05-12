package com.game.Services;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.game.Model.Country;
import com.game.Model.CountryGame;
import com.game.Model.Game;
import com.game.Model.GameDTO;
import com.game.Model.Image;
import com.game.Model.Player;
import com.game.enums.ImageType;
import com.game.repository.PlayerRepository;

@Service
public class PlayerServiceImpl implements PlayerService {

	private static final Logger logger = LoggerFactory.getLogger(PlayerServiceImpl.class);

	@Autowired
	private GameService gameService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private PlayerRepository playRepository;
	
	@Autowired
	private ImageService imageService;
	
	/**
	 * get player by Id :
	 * 
	 */

	@Override
	public Player getPlayerById(Integer id) {
		
		return playRepository.findOne(id);
	}
	
	/**
	 * DELETE game Using CountryGame :
	 * 
	 */
	@Override
	public boolean deleteGame(CountryGame countryGame) {
		
		boolean success = true;
		
		try {
			
			List<Player> players = playRepository.findByCountryAndGame(countryGame.getCountry(), countryGame.getGame());
			
			if(!CollectionUtils.isEmpty(players))
				playRepository.delete(players);
				logger.info("player successfully found");
			
		} catch(Exception e) {
			
			success = false;
			logger.error("Exception occured when try to delete game.", e);
		}
		
		return success;
	}
	/**
	 * Player List :
	 * 
	 */
	@Override
	public List<Player> findAll() {
		
		return (List<Player>) playRepository.findAll();
	}
	/**
	 * delete image :
	 * 
	 */

	@Override
	public boolean deleteImage(Integer id) {
		
		boolean success = true;
		try {
			
			playRepository.delete(id);
		}
		catch(Exception e) {
			
			success = false;
			logger.error("error occured while try to delete Image", e);
		}
		
		return success;
	}
	
	/**
	 * DELETE player :
	 *
	 */
	
	/**
	 * List of player by countryId, gameId :
	 * 
	 */
	@Override
	public List<Player> findByCountryIdAndGameId(Integer countryId, Integer gameId) {
		
	List<Player> list = playRepository.findByCountryIdAndGameId(countryId, gameId);
		return list;
		
	}
	
	/**
	 *  Delete player :
	 * 
	 */
	@Override
	public boolean deletePlayer(Integer id) {
		
		boolean success = true;
		
		try {
				playRepository.delete(id);
				
		} catch (Exception e) {
			
			success = false;
			logger.error("error occured while try to delete", e);
		}
		return success;
	}
	
	/**
	 * Add player :
	 * 
	 */
	@Override
	public boolean AddPlayer(MultipartFile file, GameDTO gameDTO) {
	
		boolean success = true;
		String filename = file.getOriginalFilename();
	    String directory = "src/main/resources/img";
	    String filepath = Paths.get(directory, filename).toString();
		
	    try {
			
		    BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(new File(filepath)));
		    stream.write(file.getBytes());
		    stream.close();
		    logger.info("Server File Location =" + filepath);
		    
		} catch (Exception e) {
		    
			System.out.println(e.getMessage());
		    logger.error("You failed to upload " + file + " => " + e.getMessage() ,e);
		}
		
	    	Image image=new Image();
	    	image.setImagePath(filepath);
	    	image.setImageType(ImageType.PLAYER);
	    	image.setUpdatedDate(new Date());
	    	imageService.AddImage(image);
	    	
	    Player player=new Player();
	    player.setPlayerName(gameDTO.getPlayerName());
	    player.setMatches(gameDTO.getMatches());
	    player.setImage(image);
	    
	    Game game=gameService.findById(gameDTO.getGameId());
	    player.setGame(game);
	    Country country=countryService.findById(gameDTO.getCountryId());
	    player.setCountry(country);
	    player.setId(gameDTO.getId());
	    if(gameDTO.getId() != null)
		player.setId(gameDTO.getId());
	    
		playRepository.save(player);
		return success;
	}
	/**
	 * UPDATE player :
	 * 
	 */
	
	@Override
	public boolean updatePlayer(MultipartFile file, GameDTO gameDTO, Integer id) {
			
		String oldImagePath = null;
		
		Player player = new Player();
		player.setId(gameDTO.getId());
		player.setPlayerName(gameDTO.getPlayerName());
		player.setMatches(gameDTO.getMatches());
		
		oldImagePath = gameDTO.getImage().getImagePath();
		
		Game game=gameService.findById(gameDTO.getGameId());
		player.setGame(game);
		Country country=countryService.findById(gameDTO.getCountryId());
		player.setCountry(country);
		player.setImage(gameDTO.getImage());
		
		if(file != null && file.getSize() > 0) {
			
			String filename = file.getOriginalFilename();
		    String directory = "src/main/resources/img";
		    String filepath = Paths.get(directory, filename).toString();
		 
		    try {
				
			    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
			    stream.write(file.getBytes());
			    stream.close();
			    logger.info("Server File Location=" + filepath);
			    
			} catch (Exception e) {
			    
				logger.error("Exception occured when try to save file.", e);
			    return "You failed to upload " + file + " => " + e.getMessage() != null;
			}
		    
	    	try {
	    		
	    		File file1 = new File(oldImagePath);
	    		
	    		if( file1.delete() )
	    			logger.info(file1.getName() + " is deleted!");
	    		else
	    			logger.info("Delete operation is failed.");
	    	   
	    	} catch(Exception e) {
	    		
	    		logger.error("Exception occured when try to delete file", e);
	    	}
	    	
		    player.getImage().setImagePath(filepath);
		}
		
		playRepository.save(player);
		
		return false;
	}
}