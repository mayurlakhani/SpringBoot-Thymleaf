package com.game.Services;

import com.game.Model.Image;

public interface ImageService{

	public void AddImage(Image image);

	public Image findById(Integer imageId);

	public boolean deleteImage(Integer id);

}
