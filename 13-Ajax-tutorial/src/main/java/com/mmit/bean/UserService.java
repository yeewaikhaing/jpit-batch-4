package com.mmit.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserService {

	@PersistenceContext
	private EntityManager em;
	
	public List<User> findAll(){
		
		return em.createQuery("SELECT u FROM User u",User.class).getResultList();
	}
	
	public void save(User u) {
		em.persist(u);
	}
}
