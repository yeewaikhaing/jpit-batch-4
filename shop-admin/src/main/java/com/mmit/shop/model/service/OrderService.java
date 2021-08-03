package com.mmit.shop.model.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mmit.shop.bean.LoginBean;
import com.mmit.shop.model.entity.Delivery;
import com.mmit.shop.model.entity.OrderDetail;
import com.mmit.shop.model.entity.Orders;
import com.mmit.shop.model.entity.Orders.Status;

@Stateless
public class OrderService {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private LoginBean loginbean;
	public void createOrder(String name, String phone, String address, Orders order) {
		
		Delivery deli=new Delivery();
		deli.setReceiverAddress(address);
		deli.setReceiverName(name);
		deli.setReceiverPhone(phone);
		
		order.addDelivery(deli);
		
		order.setStatus(Status.Pending);
		
		order.setCustomer(loginbean.getLoginUser());
		
		em.persist(order);
		
	}
	public List<Orders> findAll() {
		List<Orders> list=em.createNamedQuery("Orders.findAll",Orders.class).getResultList();
		list.forEach(o->o.getDetails().forEach(od->{}));
		return list;
	}
	public List<Orders> findByCustomer(int custId) {
		List<Orders> list=em.createNamedQuery("Orders.findByCustomer",Orders.class)
				.setParameter("customerId", custId)
				.getResultList();
		
		list.forEach(o->o.getDetails().forEach(od->{}));
		
		return list;
	}
	public Orders findById(long id) {
		Orders order=em.find(Orders.class, id);
		List<OrderDetail> details=order.getDetails();
		details.forEach(od->{});
		return order;
	}
}
