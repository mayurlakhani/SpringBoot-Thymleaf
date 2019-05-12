package com.game.Controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.game.Model.CountryGame;
import com.game.Model.GameDTO;
import com.game.Services.CountryGameService;
import com.game.Services.CountryService;
import com.game.Services.GameService;
import com.game.Services.PlayerService;
import com.game.enums.PageTemplateConstant;

@Controller
public class GameController {

	@Autowired
	private GameService gameService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private CountryGameService countryGameService;
	
	@Autowired 
	private PlayerService playerService;
	
	@RequestMapping(value = "/valid", method = RequestMethod.GET)
	public String validcheck(ModelMap modelMap) {
		return "test2";
		
	}
	
	@RequestMapping(value = "game/addGame", method = RequestMethod.GET)
	public String addGame(ModelMap modelMap) {
		modelMap.addAttribute("gameDTO",new GameDTO());
		modelMap.addAttribute("getallcountry",countryService.getOrderByName());
		modelMap.addAttribute("gamecountry",countryGameService.findAll());
		
		return PageTemplateConstant.ADD_GAME.view;
	}
	
	/**
	 * add Game :
	 * 
	 */
	@RequestMapping(value = "game/addGame", method = RequestMethod.POST)
	public String AddGame(@ModelAttribute(value = "gameDTO") GameDTO gameDTO,
								RedirectAttributes redirectAttributes) {
		 
		 boolean countrygame=countryGameService.addCountryGame(gameDTO);
		 
		 if(countrygame == true) {
			 
			 String message = "game added successfully";	
			 redirectAttributes.addFlashAttribute("successMsg", message);
		 }
		 else {
			 
			 String message = "game is not added or may exists";	
			 redirectAttributes.addFlashAttribute("errorMsg", message);
		 }
		 
 		return "redirect:/game/addGame";
	}
	
	/**
	 * Update method:
	 *
	 */
	@RequestMapping(value = "/game/update/{id}", method = RequestMethod.GET)
	public String updateGame(ModelMap modelMap, RedirectAttributes redirectAttributes, 
												@PathVariable(value="id") Integer id) {
		
		GameDTO gameDTO=new GameDTO();
		CountryGame countryGame=countryGameService.findById(id);
		gameDTO.setGameName(countryGame.getGame().getGameName());
		gameDTO.setCountryName(countryGame.getCountry().getName());
		gameDTO.setCountryGame(countryGame);
		
		modelMap.addAttribute("gameDTO",gameDTO);
		modelMap.addAttribute("gamecountry",countryGameService.findAll());
		modelMap.addAttribute("edited", "edited");
		
 		return PageTemplateConstant.ADD_GAME.view;
	}
	
	/**
	 * Delete method :
	 *
	 */
	@RequestMapping(value = "/game/delete/{id}", method = RequestMethod.GET)
	public String delete(ModelMap modelMap,RedirectAttributes redirectAttributes , 
										 @PathVariable(value = "id") Integer id ) {
		
		CountryGame countryGame=countryGameService.findById(id);
		boolean deleteplayer = playerService.deleteGame(countryGame);
		boolean deletecountrygame = countryGameService.delete(id);
		boolean deletegame = gameService.deleteGame(countryGame.getId());
		
		modelMap.addAttribute("gameDTO", new GameDTO());
		String message = "Game  was successfully deleted";
		redirectAttributes.addFlashAttribute("successMsg", message);
 		return "redirect:/game/addGame";
	}
	
	/**
	 * AJax service:get Game by Countryid :
	 * 
	 */
	@RequestMapping("/getGameByCountryid")
	public String getGameByCountryId(ModelMap modelMap, @RequestParam(value = "countryId", required = false) Integer countryId,
			@RequestParam(value = "gameId" , required = false) Integer gameId) {
		
		List<CountryGame> countryGames = countryGameService.getGameByCountryId(countryId);
		modelMap.addAttribute("countryGames", countryGames);
		return PageTemplateConstant.HOME.view + " :: gameListFragment";
	}
}
