package com.game.Controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.game.Model.Country;
import com.game.Model.GameDTO;
import com.game.Services.CountryService;
import com.game.enums.PageTemplateConstant;

@Controller
public class CountryController {
	
	@Autowired
	private CountryService countryService;
	
	@RequestMapping(value = "/home")
	public String test(ModelMap modelMap) {
		
		modelMap.addAttribute("gameDTO", new GameDTO());
		modelMap.addAttribute("countrylists", countryService.getOrderByName());
		return "home";
	}
	
	@RequestMapping(value="/getGame")
	public String getGame(){
		
		return "home";
	}
	@RequestMapping(value="/check")
	public String getGamee(){
		
		return "check";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String addTeamPage(ModelMap modelMap) {
		
		Country country = new Country();
		modelMap.addAttribute("country", country);
		List<Country> countrylists = countryService.getOrderByName();
		modelMap.addAttribute("countrylists", countrylists);
		return PageTemplateConstant.ADD_COUNTRY.view;
	}
	/**
	 * addcountry :
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String addnewCountry(RedirectAttributes redirectAttributes, @ModelAttribute(value = "country") Country country) {
		
		boolean addCountry = countryService.addCountry(country);
		
			if(addCountry == true) { 
				
				String message = "Country was successfully added";
				redirectAttributes.addFlashAttribute("successMsg", message);
			}
			else {
				
				String message = "Country was not added or already exists";
				redirectAttributes.addFlashAttribute("errorMsg", message);
			}
			
			return "redirect:/";
	}

	/**
	 *  DELETE LINK :
	 *
	 */
	@RequestMapping(value = "/deletecountry/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable(value = "id") Integer id, RedirectAttributes redirectAttributes){
		
		boolean deletecountry = countryService.deleteCountry(id);
		
		if(deletecountry == true) { 
			
			String message = "Country was successfully deleted";
			redirectAttributes.addFlashAttribute("successMsg", message);
		}
		else {
			
			String message = "Country was not Deleted";
			redirectAttributes.addFlashAttribute("errorMsg", message);
		}
		
		return "redirect:/";
	}
	
	/**
	 * Get by ID :
	 *
	 */
	@RequestMapping("/get/{id}")
	public String getCountry(@PathVariable(value="id") Integer id,
			@ModelAttribute(value = "country") Country country,
			ModelMap modelMap) {
		
		modelMap.addAttribute("country", countryService.findById(id));
		modelMap.addAttribute("edited", "edited"); 
		modelMap.addAttribute("countrylists", countryService.getOrderByName());
		return PageTemplateConstant.ADD_COUNTRY.view;
	}
	
}
