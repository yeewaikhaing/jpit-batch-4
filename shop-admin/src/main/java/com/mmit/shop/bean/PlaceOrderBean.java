package com.mmit.shop.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mmit.shop.model.entity.Orders;
import com.mmit.shop.model.entity.Users;
import com.mmit.shop.model.service.OrderService;

@Named
@ViewScoped
public class PlaceOrderBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private String name;
	private String phone;
	private String address;
	@Inject
	private LoginBean loginbean;
	@Inject
	private OrderService service;
	@Inject
	private CartBean cartbean;
	@PostConstruct
	private void init() {
		Users user=loginbean.getLoginUser();
		name=user.getUsername();
		phone=user.getPhone();
		address=user.getAddress();
	}
	
	public String placeOrder() {
		service.createOrder(name,phone,address,cartbean.getOrder());
		
		cartbean.setOrder(new Orders());// after placing order, make new order
		return "/index?faces-redirect=true";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public LoginBean getLoginbean() {
		return loginbean;
	}
	public void setLoginbean(LoginBean loginbean) {
		this.loginbean = loginbean;
	}
	
	
}
