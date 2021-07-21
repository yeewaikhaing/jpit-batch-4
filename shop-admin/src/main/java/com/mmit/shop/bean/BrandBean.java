package com.mmit.shop.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mmit.shop.model.entity.Brand;
import com.mmit.shop.model.service.BrandService;

@Named
@ViewScoped
public class BrandBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Brand brand;
	private String message;
	@Inject
	private BrandService service;
	private boolean isSaveButtonDisabled=true;
	
	@PostConstruct
	private void init() {
		brand=new Brand();
	}
	
	public void retrieveBrandInfo(int id) {
		message=null;
		isSaveButtonDisabled=true;
		if(id == 0)
			brand=new Brand();
		else
			brand=service.findById(id);
	}
	
	public void remove(int id) {
		service.remove(id);
	}
	public void checkBrand() {
		Brand tmp=service.findByBrand(brand);
		if(tmp != null) {
			message="This Brand already exist.";
			isSaveButtonDisabled=true;
		}
		else
		{
			isSaveButtonDisabled=false;
			message=null;
		}
	}
	public void save() {
		service.save(brand);
	}
	
	public boolean isSaveButtonDisabled() {
		return isSaveButtonDisabled;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Brand> getBrands(){
		return service.findAll();
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	
	
}
