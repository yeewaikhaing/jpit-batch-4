package com.mmit.shop.model.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mmit.shop.model.entity.Users;

@Stateless
public class UsersService {

	@PersistenceContext
	private EntityManager em;
	
	public List<Users> findAll(){
		return em.createNamedQuery("User.findAll",Users.class).getResultList();
	}
	
	public Users findById(int id) {
		return em.find(Users.class, id);
	}

	public void save(Users user) {
		if(user.getId() == 0)
			em.persist(user);
		else
			em.merge(user);
	}

	public void remove(int id) {
		em.remove(findById(id));
	}
}
