package com.mmit.shop.model.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

import com.mmit.shop.ShopCommonException;
import com.mmit.shop.bean.LoginBean;
import com.mmit.shop.model.entity.Users;

@Stateless
public class UsersService {

	@PersistenceContext
	private EntityManager em;
	@Inject
	private Pbkdf2PasswordHash encoder;
	@Inject
	private LoginBean loginbean;
	public List<Users> findAllWithoutMe(){
		return em.createNamedQuery("User.findAll",Users.class)
				.setParameter("email", loginbean.getLoginUser().getEmail())
				.getResultList();
	}
	
	public Users findById(int id) {
		return em.find(Users.class, id);
	}

	public void save(Users user) {
		if(user.getId() == 0)
		{
			user.setPassword(encoder.generate(user.getPassword().toCharArray()));
			em.persist(user);
		}
		else
			em.merge(user);
	}

	public void remove(int id) {
		em.remove(findById(id));
	}

	public Users findByEmail(String caller) {
		
		try {
			return em.createNamedQuery("Users.findByEmail",Users.class)
					.setParameter("email", caller)
					.getSingleResult();
		} catch (NoResultException e) {
			
		}
		return null;
	}

	public long countUsers() {
		
		return em.createNamedQuery("User.findCount",Long.class)
				.getSingleResult();
	}

	public void changePassword(String oldPassword, String newPassword, String confirmPass) {
		Users logInUser=findByEmail(loginbean.getEmail());// return email
		
		if(!encoder.verify(oldPassword.toCharArray(), logInUser.getPassword()))
			throw new ShopCommonException("Incorrect Old Password");
		if(!newPassword.equals(confirmPass))
			throw new ShopCommonException("Incorrect Confirm Password");
		
		logInUser.setPassword(encoder.generate(newPassword.toCharArray()));
		
	}
}
