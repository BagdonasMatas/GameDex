package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Role;
import com.example.demo.enumerator.RolesEnumerator;
import com.example.demo.repository.RoleRepo;

@Component
public class ApplicationInit implements ApplicationRunner{
	
	@Autowired
	RoleRepo repo;
	

	@Override
	public void run(ApplicationArguments args) throws Exception {

		if(repo.findByRole(RolesEnumerator.ROLE_USER).isEmpty()) {
			Role user = new Role();
			user.setRole(RolesEnumerator.ROLE_USER);
			repo.save(user);
		}
		if(repo.findByRole(RolesEnumerator.ROLE_ADMIN).isEmpty()) {
			Role admin = new Role();
			admin.setRole(RolesEnumerator.ROLE_ADMIN);
			repo.save(admin);
		}
		
		
	}
}