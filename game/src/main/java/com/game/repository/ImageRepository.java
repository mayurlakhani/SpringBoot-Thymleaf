package com.game.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.game.Model.*;

@Repository
public interface ImageRepository extends CrudRepository<Image, Integer> {

}