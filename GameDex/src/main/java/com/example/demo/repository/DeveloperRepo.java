package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Developer;

@Repository
public interface DeveloperRepo extends CrudRepository<Developer,Long>{

}
