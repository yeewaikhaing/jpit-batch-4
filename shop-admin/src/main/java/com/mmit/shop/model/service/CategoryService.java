package com.mmit.shop.model.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mmit.shop.model.entity.Category;

@Stateless
public class CategoryService {

	@PersistenceContext
	private EntityManager em;
	
	public List<Category> findAll(){
		return em.createNamedQuery("Category.findAll",Category.class).getResultList();
	}
	
	public Category findById(int id) {
		return em.find(Category.class, id);
	}

	public void save(Category categoy) {
		
		if(categoy.getId() == 0)
			em.persist(categoy);
		else
			em.merge(categoy);
		
	}

	public void remove(int id) {
		em.remove(findById(id));
		
	}

	public String findByName(Category c) {
		
		try {
			return em.createNamedQuery("Category.findByName",String.class)
					   .setParameter("cname", c.getName())
					   .setParameter("cid", c.getId())
					   .getSingleResult();
		} catch (Exception e) {
			
		}
		return null;
	}
}
