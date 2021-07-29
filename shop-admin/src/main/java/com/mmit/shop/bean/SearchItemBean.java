package com.mmit.shop.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mmit.shop.model.entity.Item;
import com.mmit.shop.model.service.BrandService;
import com.mmit.shop.model.service.CategoryService;
import com.mmit.shop.model.service.ItemService;

@Named
@ViewScoped
public class SearchItemBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ItemService service;
	@Inject
	private CategoryService catService;
	@Inject
	private BrandService brandService;
	private List<Item> items;
	
	private String search_by=null;
	
	private String message;
	@PostConstruct
	private void init() {
		ExternalContext cxt=FacesContext.getCurrentInstance().getExternalContext();
		// for item displays
		String categoryId=cxt.getRequestParameterMap().get("cid");
		String brandId=cxt.getRequestParameterMap().get("bid");
		if(categoryId != null)
			{
				items=service.findByCategory(Integer.parseInt(categoryId));
				search_by="Category";
				message=catService.findById(Integer.parseInt(categoryId)).getName();
			}
		else if(brandId != null)
		{
			items=service.findByBrand(Integer.parseInt(brandId));
			search_by="Brand";
			message=brandService.findById(Integer.parseInt(brandId)).getName();
		}
		else if(categoryId == null && brandId == null)
		{
			items=service.findAll();
			search_by=null;
			message=null;
		}
		
	}

	public List<Item> getItems() {
		return items;
	}

	public String getSearch_by() {
		return search_by;
	}

	public String getMessage() {
		return message;
	}
	
	
}
