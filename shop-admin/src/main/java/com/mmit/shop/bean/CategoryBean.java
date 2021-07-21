package com.mmit.shop.bean;


import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mmit.shop.model.entity.Category;
import com.mmit.shop.model.service.CategoryService;

@ViewScoped
@Named
public class CategoryBean implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Inject
	private CategoryService service;
	
	private Category categoy;
	
	private boolean isSaveButtonDisabled=true;
	
	private String message;
	@PostConstruct
	private void init() {
		categoy=new Category();
	}
	
	public void save() {
		service.save(categoy);
	}
	
	public void checkCategoryName() {
		String name=service.findByName(categoy);
		
		if(name != null) {
			message="This category already exist";
			isSaveButtonDisabled=true;
		}
		else {
			message="";
			isSaveButtonDisabled=false;
		}
	}
	
	// ajax ( r.t must be void)
	
	public void retrieveCategoryInfo(int id) {
		if(id == 0)
			categoy=new Category();
		else
			categoy=service.findById(id);
	}
	public void remove(int id) {
		service.remove(id);
	}
	public List<Category> getCategories(){
		return service.findAll();
	}

	public Category getCategoy() {
		return categoy;
	}

	public void setCategoy(Category categoy) {
		this.categoy = categoy;
	}

	public boolean isSaveButtonDisabled() {
		return isSaveButtonDisabled;
	}

	public String getMessage() {
		return message;
	}

	
	
}
