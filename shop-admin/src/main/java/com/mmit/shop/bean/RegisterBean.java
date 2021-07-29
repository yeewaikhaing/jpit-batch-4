package com.mmit.shop.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mmit.shop.model.entity.Users;
import com.mmit.shop.model.entity.Users.ACCESS_LEVEL;
import com.mmit.shop.model.service.UsersService;

@Named
@ViewScoped
public class RegisterBean implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private Users customer;
	@Inject
	private UsersService service;
	@PostConstruct
	private void init() {
		customer=new Users();
		customer.setLevel(ACCESS_LEVEL.Customer);
	}
	
	public String register() {
		
		service.save(customer);
		
		return "/index?faces-redirect=true";
	}

	public Users getCustomer() {
		return customer;
	}

	public void setCustomer(Users customer) {
		this.customer = customer;
	}
	
	
}
