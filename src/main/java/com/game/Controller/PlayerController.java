package com.game.Controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.game.Model.GameDTO;
import com.game.Model.Player;
import com.game.Services.CountryGameService;
import com.game.Services.CountryService;
import com.game.Services.GameService;
import com.game.Services.ImageService;
import com.game.Services.PlayerService;
import com.game.enums.PageTemplateConstant;

@Controller
public class PlayerController {
	
	private static final Logger logger = LoggerFactory.getLogger(PlayerController.class);
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private ImageService imageService;

	@Autowired
	private GameService gameService;
	
	@Autowired
	private CountryGameService countryGameService;
	
	@RequestMapping(value="/player/playerdetail")
	public String getPlayerList(ModelMap modelMap,@RequestParam(value="id") Integer id) {
		
		modelMap.addAttribute("players", playerService.getPlayerById(id));
		modelMap.addAttribute("playerlists", playerService.findAll());
		
		return PageTemplateConstant.ADD_PLAYER.view;
	}
	
	@RequestMapping(value = "/player/updatePlayer", method = RequestMethod.GET)
	public String updateGame(ModelMap modelMap,@RequestParam(value="id",required=false) Integer id) {
		modelMap.addAttribute("players", playerService.getPlayerById(id));
	return PageTemplateConstant.PLAYER_DETAIL.view;
	
	}
	
	/**
	 *  ADD/SAVE PLAYER :
	 */
	
	@RequestMapping(value = "/player/addPlayer", method = RequestMethod.GET)
	public String addGame(ModelMap modelMap) {
		
		GameDTO gameDTO = new GameDTO();
		modelMap.addAttribute("player", gameDTO);
		modelMap.addAttribute("countrylists", countryService.getOrderByName());
		modelMap.addAttribute("gamelists", gameService.getOrderByName());
		return PageTemplateConstant.ADD_PLAYER.view;
	}
	
	/**
	 *  GET PLAYER BY GAME AND COUNTRY :
	 *  
	 */
	@RequestMapping(value="/player/getplayerByGameAndCountry")
	public String getPlayerByGameAndCountry(ModelMap modelMap, @RequestParam(value = "countryId") Integer countryId,@RequestParam(value="gameId") Integer gameId){
		
		List<Player> players = playerService.findByCountryIdAndGameId(countryId,gameId);
		modelMap.addAttribute("players", players);
		return PageTemplateConstant.HOME.view + " :: playerListFragment";
	}
	
	@RequestMapping(value="/player/getallplayerdata")
	public String getallplayerdata(ModelMap modelMap,@RequestParam(value="id") Integer id) {
		Player players=playerService.getPlayerById(id);
		
		modelMap.addAttribute("players", players);	
		return PageTemplateConstant.PLAYER_DETAIL.view;
	}
	
	/**
	 *  GET IMAGE :
	 */
	@RequestMapping(value = "/player/getImage")
	public boolean getImage(@RequestParam("filePath") String filePath, HttpServletResponse response) {
		
		boolean success = true;
		try {
				response.setContentType("image/jpeg");
				File f = new File(filePath);
				BufferedImage bi;
				bi = ImageIO.read(f);
				OutputStream out = response.getOutputStream();
				ImageIO.write(bi, "jpg", out);
				out.close();
				
		} catch (IOException e) {
			
			logger.error("Image not found", e);
			success = false;
		}
		
		return success;
	}
	
	/**
	 * UPDATE PLAYER :
	 * 
	 */
	@RequestMapping("/player/update/{id}")
	public String updatePlayer(@PathVariable(value="id") Integer id,
			ModelMap modelMap, RedirectAttributes redirectAttributes,@RequestParam(value="name",required=false)String name) {
		
		Player player = playerService.getPlayerById(id);
		modelMap.addAttribute("player", player);
		modelMap.addAttribute("edited", "edited");
		modelMap.addAttribute("countrylists", countryService.getOrderByName());
		modelMap.addAttribute("countryGames",countryGameService.getGameByCountryId(player.getCountry().getId()));
		return PageTemplateConstant.UPDATE_PLAYER.view; 
	}
	
	/**
	 * ADD PLAYER : Save File :
	 */
	@RequestMapping(value = "/player/addPlayer", method = RequestMethod.POST)
	public String addPlayer(RedirectAttributes redirectAttributes, 
			@RequestParam(value = "file") MultipartFile file, 
			@ModelAttribute(value = "player") GameDTO gameDTO, BindingResult bindingResult) {
		
		boolean addplayer = playerService.AddPlayer( file, gameDTO);
		
		if( addplayer == true) {
			
			 String message = "player was successfully added";
			 redirectAttributes.addFlashAttribute("successMsg", message);
		}
		else {
			
			 String message = "player was already exists or not to be added";
			 redirectAttributes.addFlashAttribute("errorMsg", message);
		}
		return "redirect:/home"; 
	}
	
	/**
	 * UPDATE PLAYER : action return
	 * 
	 */
	@RequestMapping(value = "/player/savePlayer", method = RequestMethod.POST)
	public String savePlayer(RedirectAttributes redirectAttributes, 
			@RequestParam(value = "file") MultipartFile file, 
			@ModelAttribute(value = "player") GameDTO gameDTO, BindingResult bindingResult, Integer id) {
		
		boolean updateplayer = playerService.updatePlayer(file, gameDTO, id);
		
		if(updateplayer = true) {
			
			String message = "player was successfully Updated";
			redirectAttributes.addFlashAttribute("successMsg", message);
		}
		else {
			
			String message = "player was not Updated";
			redirectAttributes.addFlashAttribute("errorMsg", message);
		}
		return "redirect:/home"; 
	}
	
	/**
	 * 	DELETE method :
	 *
	 */
	@RequestMapping(value = "player/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable(value = "id") Integer id, RedirectAttributes redirectAttributes) {
		
		Player player = playerService.getPlayerById(id);
		boolean deleteplayer = playerService.deletePlayer(id);
		boolean deleteimage = imageService.deleteImage(player.getImage().getId());
		
		String message = "player was successfully deleted";
		redirectAttributes.addFlashAttribute("successMsg", message);
		return "redirect:/home";
	}
}
