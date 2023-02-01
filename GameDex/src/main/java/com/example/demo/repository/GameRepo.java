package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Game;

public interface GameRepo extends CrudRepository<Game,Long>{
	Optional<Game> findByName(String email);

}
