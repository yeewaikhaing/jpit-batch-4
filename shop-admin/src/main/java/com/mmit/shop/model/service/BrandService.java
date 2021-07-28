package com.mmit.shop.model.service;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mmit.shop.bean.LoginBean;
import com.mmit.shop.model.entity.Brand;

@Stateless
public class BrandService {

	@PersistenceContext
	private EntityManager em;
	@Inject
	private LoginBean loginbean;
	public List<Brand> findAll(){
		return em.createNamedQuery("Brand.findAll",Brand.class).getResultList();
	}
	
	public Brand findById(int id) {
		return em.find(Brand.class, id);
	}

	public void remove(int id) {
		em.remove(findById(id));
	}
	
	public void save(Brand brand)throws EJBException {
		
		if(brand.getId() == 0)
		{
			brand.setCreated_by(loginbean.getLoginUser());
			em.persist(brand);
		}
		else
		{
			brand.setUpdated_by(loginbean.getLoginUser());
			em.merge(brand);
		}
	}


	public Brand findByBrand(Brand brand) {
		try {
			return em.createNamedQuery("Brand.findByBrand",Brand.class)
					.setParameter("name", brand.getName())
					.setParameter("id", brand.getId())
					.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
}
