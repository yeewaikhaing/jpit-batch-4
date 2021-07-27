package com.mmit.shop.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;

import static javax.persistence.EnumType.STRING;

/**
 * Entity implementation class for Entity: Users
 *
 */
@Entity
@NamedQuery(name = "User.findAll",query = "SELECT u FROM Users u")
@NamedQuery(name="Users.findByEmail",query="SELECT u FROM Users u WHERE u.email = :email")
@NamedQuery(name="User.findCount",query="SELECT COUNT(u) FROM Users u")
public class Users implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	@Column(unique = true)
	@NotEmpty(message = "Require Email")
	private String email;
	@NotEmpty(message="Requre Password")
	private String password;
	private String phone;
	private String address;
	@CreationTimestamp
	private LocalDate resgisterDate;
	@Enumerated(STRING)
	private ACCESS_LEVEL level;
	
	public enum ACCESS_LEVEL{
		Admin,Staff,Customer
	}
	public Users() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public LocalDate getResgisterDate() {
		return resgisterDate;
	}
	public void setResgisterDate(LocalDate resgisterDate) {
		this.resgisterDate = resgisterDate;
	}
	public ACCESS_LEVEL getLevel() {
		return level;
	}
	public void setLevel(ACCESS_LEVEL level) {
		this.level = level;
	}
   
}
