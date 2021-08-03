package com.mmit.shop.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mmit.shop.model.entity.Item;
import com.mmit.shop.model.entity.OrderDetail;
import com.mmit.shop.model.entity.Orders;
import com.mmit.shop.model.service.ItemService;

@Named
@SessionScoped
public class CartBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Orders order;
	@Inject
	private ItemService service;
	@PostConstruct
	private void init() {
		order=new Orders();
	}
	
	// ajax call
	
	public void addToCart(int itemId) {
		// increate qty of existing item
		for(OrderDetail od : order.getDetails()) {
			if(od.getItem().getId() == itemId) {
				od.setSubQty(od.getSubQty()+1);
				return;
			}
		}
		
		// add new item to cart
		Item item=service.findById(itemId);
		OrderDetail newOrderDetail=new OrderDetail();
		newOrderDetail.setSubQty(1);
		newOrderDetail.setItem(item);
		
		order.addOrderItem(newOrderDetail);
	}
	
	public String removeFromCart(OrderDetail od) {
		order.getDetails().remove(od);
		
		return "/public/cart-info?faces-redirect=true";
	}
	
	public void updateCart(int itemId) {
		for(OrderDetail od: order.getDetails()) {
			if(od.getItem().getId() == itemId) {
				if(od.getSubQty()<1) {
					od.setSubQty(1);
				}
			}
		}
	}
	public int getItemCount() {
		return order.getDetails().size();
	}
	public Orders getOrder() {
		return order;
	}

	
	public void setOrder(Orders order) {
		this.order = order;
	}
	
	
}
