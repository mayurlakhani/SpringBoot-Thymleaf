package com.game.Model;
import org.springframework.web.multipart.MultipartFile;
import com.game.enums.ImageType;

public class GameDTO {
	
	private Player player;
	
	private Country country;
	
	private Game game;

	private Image image;

	private CountryGame countryGame;
	
	private String playerName;
	
	private String countryName;
	
	private String gameName;
	
	private Integer matches;
		
	private int countryId;
	
	private int playerId;
	
	private int imageId;
	
	private int gameId;
	
	private Integer id;
	
	private ImageType imageType;
	
	private MultipartFile file;
	

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public CountryGame getCountryGame() {
		return countryGame;
	}

	public void setCountryGame(CountryGame countryGame) {
		this.countryGame = countryGame;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	
	public ImageType getImageType() {
		return imageType;
	}

	public void setImageType(ImageType imageType) {
		this.imageType = imageType;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getImageId() {
		return imageId;
	}

	public Integer getMatches() {
		return matches;
	}

	public void setMatches(Integer matches) {
		this.matches = matches;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
}
