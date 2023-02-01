package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserGame;

@Repository
public interface UserGameRepo extends CrudRepository<UserGame,Long>{

	public List<UserGame> findAllByUser_id(Long userId);
	public List<UserGame> findAllByGame_id(Long gameId);


}
