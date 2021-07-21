package com.mmit.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	private String confirmPass;
	
	public User() {
		
	}

	public User(String username, String password, String confirmPass) {
		super();
		this.username = username;
		this.password = password;
		this.confirmPass = confirmPass;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPass() {
		return confirmPass;
	}

	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}
	
}
