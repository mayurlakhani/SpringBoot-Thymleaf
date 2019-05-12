package com.game.enums;

public enum PageTemplateConstant {
	
	HOME("home"),
	INDEX("index"),
	ADD_COUNTRY("addCountry"),
	ADD_GAME("addGame"),
	ADD_TEAM("addTeam"),
	ADD_PLAYER("addPlayer"), 
	UPDATE_PLAYER("updatePlayer"),
	PLAYER_DETAIL("playerdetail"),
	ADD_IMAGE("addImage"),
	GAME_LIST("gameList");
	
	public String view;
	
	PageTemplateConstant(String view) {
		this.view = view;
	}
}