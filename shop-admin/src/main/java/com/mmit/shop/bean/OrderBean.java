package com.mmit.shop.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mmit.shop.model.entity.Delivery;
import com.mmit.shop.model.entity.OrderDetail;
import com.mmit.shop.model.entity.Orders;
import com.mmit.shop.model.entity.Users;
import com.mmit.shop.model.service.OrderService;

@Named
@ViewScoped
public class OrderBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<Orders> orders;
	@Inject
	private OrderService service;
	
	private List<OrderDetail> orderDetails;
	private Users customer;
	private Delivery delivery;
	@PostConstruct
	private void init() {
		customer=new Users();
		delivery=new Delivery();
		orders=service.findAll();
		orderDetails=new ArrayList<OrderDetail>();
	}
	
	public void retrieveOrderDetails(Orders order) {
		orders.forEach(o->{
			if(o.getId() == order.getId()) {
				orderDetails=o.getDetails();
				return;
			}
		});
	}
	
	public void retrieveCustomerInfo(Orders order) {
		orders.forEach(o->{
			if(o.getId() == order.getId()) {
				customer=o.getCustomer();
				delivery=o.getDelivery();
				return;
			}
		});
	}
	public List<Orders> getOrders() {
		
		return orders;
	}
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public Users getCustomer() {
		return customer;
	}

	public Delivery getDelivery() {
		return delivery;
	}
	
	
}
