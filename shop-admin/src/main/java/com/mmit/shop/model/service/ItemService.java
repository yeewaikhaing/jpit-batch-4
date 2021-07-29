package com.mmit.shop.model.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mmit.shop.bean.LoginBean;
import com.mmit.shop.model.entity.Item;
import com.mmit.shop.model.entity.Item.ClothType;

@Stateless
public class ItemService {

	@PersistenceContext
	private EntityManager em;
	@Inject
	private LoginBean loginbean;
	public List<Item> findAll(){
		return em.createNamedQuery("Item.findAll",Item.class).getResultList();
	}
	
	public Item findById(int id) {
		return em.find(Item.class, id);
	}

	public void save(Item item) {
		
		if(item.getId() == 0)
		{
			item.setCreated_by(loginbean.getLoginUser());
			em.persist(item);
		}
		else
		{
			item.setUpdated_by(loginbean.getLoginUser());
			em.merge(item);
		}
		
	}

	public void remove(int id) {
		em.remove(findById(id));
	}

	public String findPhotoById(int id) {
		
		return em.createNamedQuery("Item.findPhotoById",String.class)
				.setParameter("itemId", id)
				.getSingleResult();
	}

	public long findByClothType(ClothType type) {
		
		return em.createNamedQuery("Item.findByClothType",Long.class)
				.setParameter("clothType", type)
				.getSingleResult();
	}

	public List<Item> findByCategory(int cid) {
		
		return em.createNamedQuery("Item.findByCategory",Item.class)
				.setParameter("category", cid)
				.getResultList();
	}

	public List<Item> findByBrand(int bid) {
		return em.createNamedQuery("Item.findByBrand",Item.class)
				.setParameter("brand", bid)
				.getResultList();
	}
}
