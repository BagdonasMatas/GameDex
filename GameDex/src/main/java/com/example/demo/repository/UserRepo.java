package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public interface UserRepo extends CrudRepository<User,Long>{
	Optional<User> findByEmail(String email);
	
	public List<User> findAllByUserGameFavorites_id(Long gameId);
	
	public List<User> findAllByUserDeveloperFavorites_id(Long gameId);
}
