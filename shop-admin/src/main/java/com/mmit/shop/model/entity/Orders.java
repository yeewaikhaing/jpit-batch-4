package com.mmit.shop.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REMOVE;

/**
 * Entity implementation class for Entity: Orders
 *
 */
@Entity
@NamedQuery(name="Orders.findAll",query="SELECT o FROM Orders o")
@NamedQuery(name="Orders.findByCustomer",query="SELECT o FROM Orders o WHERE o.customer.id = :customerId")
public class Orders implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Users customer;
	@CreationTimestamp
	private LocalDate orderDate;
	private LocalDate receiveDate;
	@OneToMany(mappedBy = "order", cascade = PERSIST)
	private List<OrderDetail> details=new ArrayList<OrderDetail>();
	@OneToOne(mappedBy = "order", cascade = { PERSIST, MERGE, REMOVE })
	private Delivery delivery;
	@Enumerated(EnumType.STRING)
	private Status status;
	public enum Status{
		Pending,Received,Cancelled,Delivered
	}
	public Orders() {
		super();
	}
	
	public void addOrderItem(OrderDetail od) {
		od.setOrder(this);
		details.add(od);
	}
	
	public void addDelivery(Delivery deli) {
		deli.setOrder(this);
		this.setDelivery(deli);
	}
	public int getTotalQty() {
		return details.stream().mapToInt(od-> od.getSubQty()).sum();
	}
	
	public int getTotalAmount()
	{
		return details.stream().mapToInt(od->od.getSubQty()*od.getItem().getPrice()).sum();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Users getCustomer() {
		return customer;
	}
	public void setCustomer(Users customer) {
		this.customer = customer;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public LocalDate getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(LocalDate receiveDate) {
		this.receiveDate = receiveDate;
	}
	public List<OrderDetail> getDetails() {
		return details;
	}
	public void setDetails(List<OrderDetail> details) {
		this.details = details;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
   
}
