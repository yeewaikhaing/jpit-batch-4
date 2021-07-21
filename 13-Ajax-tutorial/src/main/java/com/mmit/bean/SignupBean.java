package com.mmit.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class SignupBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private User user;
	
	private List<User> users;
	@Inject
	private UserService serivce;
	
	private String message;
	@PostConstruct
	private void init() {
		user=new User();
		users=serivce.findAll();
	}

	public void checkConfirmPass() {
		
		if(!user.getPassword().equals(user.getConfirmPass()))
			message="Confirm Password do not match";
		else
			message="";
	}
	public void register() {
		
		serivce.save(user);
		user=new User();
		users=serivce.findAll();
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return users;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
