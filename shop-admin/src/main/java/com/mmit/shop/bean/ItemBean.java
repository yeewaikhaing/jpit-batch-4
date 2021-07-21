package com.mmit.shop.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mmit.shop.model.entity.Item;
import com.mmit.shop.model.service.ItemService;

@Named
@ViewScoped
public class ItemBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Item item;
	@Inject
	private ItemService service;
	
	@PostConstruct
	private void init() {
		String id=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		if(id != null)
			item=service.findById(Integer.parseInt(id));
		else
			item=new Item();
	}

	public String save() {
		service.save(item);
		
		return "/admin/items?faces-redirect=true";
	}
	
	public String remove(int id) {
		service.remove(id);
		
		return "/admin/items?faces-redirect=true";
	}
	
	public void getItemInfo(int id) {
		item=service.findById(id);
	}
	
	public List<Item> getItems(){
		return service.findAll();
	}


	public Item getItem() {
		return item;
	}


	public void setItem(Item item) {
		this.item = item;
	}
	
	
}
