package com.game.Services;

import java.util.List;

import com.game.Model.Country;

public interface CountryService {

	
	public List<Country> getOrderByName();

	public boolean deleteCountry(Integer id);

	public Country getCountryByName(String countryName);

	public Country findById(Integer countryId);

	public Country findByName(String name);

	public boolean addCountry(Country country);

}
