package com.mmit.shop.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import static javax.persistence.EnumType.STRING;

/**
 * Entity implementation class for Entity: Item
 *
 */
@Entity
@NamedQuery(name="Item.findAll",query = "SELECT i From Item i")
@NamedQuery(name="Item.findPhotoById",query="SELECT i.photo FROM Item i WHERE i.id = :itemId")
@NamedQuery(name="Item.findByClothType",query="SELECT COUNT(i) FROM Item i WHERE i.type = :clothType")
@NamedQuery(name="Item.findByCategory",query="SELECT i FROM Item i WHERE i.category.id = :category")
@NamedQuery(name="Item.findByBrand",query="SELECT i FROM Item i WHERE i.brand.id = :brand")
public class Item implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@Column(columnDefinition = "TEXT")
	private String description;
	private int price;
	@ManyToOne(optional = false)
	@JoinColumn(name="category_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Category category;
	@ManyToOne(optional = false)
	@JoinColumn(name="brand_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Brand brand;
	private String photo;
	@CreationTimestamp
	private LocalDate created_at;
	@UpdateTimestamp
	private LocalDate updated_at;
	@ManyToOne
	@JoinColumn(name="created_by")
	private Users created_by;
	@ManyToOne
	@JoinColumn(name="updated_by")
	private Users updated_by;
	
	@Enumerated(STRING)
	private ClothType type;
	
	public enum ClothType{
		Men,Ladies,Kids
	}
	public Item() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public LocalDate getCreated_at() {
		return created_at;
	}
	public void setCreated_at(LocalDate created_at) {
		this.created_at = created_at;
	}
	public LocalDate getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(LocalDate updated_at) {
		this.updated_at = updated_at;
	}
	public Users getCreated_by() {
		return created_by;
	}
	public void setCreated_by(Users created_by) {
		this.created_by = created_by;
	}
	public Users getUpdated_by() {
		return updated_by;
	}
	public void setUpdated_by(Users updated_by) {
		this.updated_by = updated_by;
	}
	public ClothType getType() {
		return type;
	}
	public void setType(ClothType type) {
		this.type = type;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
   
}
