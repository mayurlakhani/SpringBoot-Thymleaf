package com.game;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.game.Model.Image;
import com.game.Model.Player;
import com.game.Services.ImageService;
import com.game.Services.PlayerService;
import com.game.repository.ImageRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GameApplication.class)
@WebAppConfiguration
public class GameApplicationTests {

	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private  ImageService imageService;

	
	
	@Test
	public void contextLoads() {
		Image image=new Image();
		
		Player player=new Player();
		playerService.getPlayerById(43);
		player.setImage(null);
		image.setImagePath(null);
	}

}
