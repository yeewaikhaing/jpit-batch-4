package com.mmit.shop.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mmit.shop.model.entity.Orders;
import com.mmit.shop.model.service.OrderService;

@Named
@ViewScoped
public class MyOrderBean implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private List<Orders> orders;
	@Inject
	private OrderService service;
	@Inject
	private LoginBean loginbean;
	
	private Orders order;
	
	
	
	@PostConstruct
	private void init() {
		orders=service.findByCustomer(loginbean.getLoginUser().getId());
		
		String id=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		if(id != null)
		{
			order=service.findById(Long.parseLong(id));
			
		}
		else
		{
			order=new Orders();
			
		}
	}

	public List<Orders> getOrders() {
		return orders;
	}

	

	public Orders getOrder() {
		return order;
	}

}
