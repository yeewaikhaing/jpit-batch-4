package com.mmit.shop;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.mmit.shop.model.entity.Users;
import com.mmit.shop.model.entity.Users.ACCESS_LEVEL;
import com.mmit.shop.model.service.UsersService;

@ApplicationScoped
@Singleton
@Startup
public class AdminUserCreation {

	@Inject
	private UsersService service;
	
	@PostConstruct
	private void init() {
		long count=service.countUsers();
		if(count == 0) {
			Users user=new Users();
			user.setAddress("Yangon");
			user.setEmail("jk@gmail.com");
			user.setLevel(ACCESS_LEVEL.Admin);
			user.setPassword("12345678");
			user.setPhone("09795578841");
			user.setUsername("Jeon Jung Kook");
			
			service.save(user);
		}
	}
}
