package com.game.Services;
import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.Model.Image;
import com.game.Model.Player;
import com.game.repository.ImageRepository;
@Service
public class ImageServiceImpl implements ImageService{

private static final Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired
	private PlayerService playerService;
	
	@Override
	public void AddImage(Image image) {
		
		try {
			
			imageRepository.save(image);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			logger.error("error found while try to add Image", e);
		}
		
		
	}

	@Override
	public Image findById(Integer imageId) {
		
		return imageRepository.findOne(imageId);
	}

	@Override
	public boolean deleteImage(Integer id) {
		
		boolean success = true;
		try {
			  Player player = playerService.getPlayerById(id);
			  String ImagePath = player.getImage().getImagePath();
    		  File file1 = new File(ImagePath);
    		  
	    		if( file1.delete() ) {
	    			
	    			System.out.println(file1.getName() + " is deleted!");
	    		} else {
	    			
	    			System.out.println("Delete operation is failed.");
	    		}
    	   
    	} catch(Exception e) {
    		
    		e.printStackTrace();
    		success = false;
    	}
		 
		imageRepository.delete(id);
		return success;
	}
}