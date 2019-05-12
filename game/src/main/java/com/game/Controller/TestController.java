package com.game.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.game.Model.Country;
import com.game.Model.GameDTO;
import com.game.Services.CountryService;

@Controller
public class TestController {
	
	@Autowired
	private CountryService countryService;
	
	@RequestMapping(value="/demo")
	private String demo(ModelMap modelMap) {
		
		GameDTO gameDTO=new GameDTO();
		gameDTO.setGameName("testgame");
		gameDTO.setPlayerName("testplayer");
		modelMap.addAttribute("gameDTO", gameDTO);
		Country country1=countryService.findByName("CHINA");
		System.out.println(country1);
		return "/test";
	}
	
	@RequestMapping(value = "/demo", method = RequestMethod.POST)
	private void demoget() {
		System.out.println("hello");
	}
	
}