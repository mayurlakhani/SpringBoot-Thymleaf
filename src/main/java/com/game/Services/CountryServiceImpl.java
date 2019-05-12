package com.game.Services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.game.Model.Country;
import com.game.repository.CountryRepository;

@Service
public class CountryServiceImpl implements CountryService {

private static final Logger logger = LoggerFactory.getLogger(CountryServiceImpl.class);
	
	@Autowired
	private CountryRepository countryRepository;
	
	/**
	 *  LIST of country :
	 *  
	 */
	public List<Country> getOrderByName() {
	
		List<Country> userlist = null;
		
		try {
			   userlist = (List<Country>) countryRepository.findAllByOrderByNameAsc();
		} catch(Exception e) {
			
			logger.error("Exception occured when try to get all records", e);
		}
		
		return userlist;
	}

	/**
	 *  save data :
	 *  
	 */
	@Override
	public boolean addCountry(Country country) {
		
		boolean success = true;
		
		try {
				Country country1 = findByName(country.getName());
				
				 		if( country1 == null) {
							country1 = new Country();
							country1.setName(country.getName());
							country1.setId(country.getId());
						}
				 		else {
					 			country1 = findById(country.getId());
					 			String countryname = country.getName(); 
					 			if (!countryname.equals(country1.getName())) {
					 				
					 				country1.setName(country1.getName());
					 			}	
				 		}
				 		
				 		countryRepository.save(country1);
						logger.info("country was successfully saved");
						
		} catch(Exception e) {
			
			success = false;
		} 
		
		return success;
	}
	
	/**
	 *  delete data :
	 *  
	 */
	@Override
	public boolean deleteCountry(Integer id) {
		
		boolean success = true;
		
		try {
			
			countryRepository.delete(id);
		} catch(Exception e) {
			
			success = false;
			logger.error("Exception occured when try to delete records", e);
		}
		
		return success;
	}
	/**
	 *  get by name:
	 *  
	 */
	@Override
	public Country getCountryByName(String countryName) {
		
		return countryRepository.findByName(countryName);
	}
	/**
	 * get Id :
	 * 
	 */
	@Override
	public Country findById(Integer countryId) {
		return countryRepository.findOne(countryId);
	}
	/**
	 * find by Name :
	 * 
	 */
	@Override
	public Country findByName(String name) {
		
		return countryRepository.findByName(name);
	}
}