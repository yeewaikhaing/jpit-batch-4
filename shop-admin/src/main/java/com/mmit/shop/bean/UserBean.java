package com.mmit.shop.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mmit.shop.model.entity.Users;
import com.mmit.shop.model.entity.Users.ACCESS_LEVEL;
import com.mmit.shop.model.service.UsersService;

@Named
@ViewScoped
public class UserBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private UsersService service;
	
	private Users user;
	@Inject
	private LoginBean loginbean;
	@PostConstruct
	public void init() {
		String id=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		if(id != null)
			user=service.findById(Integer.parseInt(id));
		else
			user=new Users();
	}
	
	public String save() {
		try {
			service.save(user);
			if(user.getId() == loginbean.getLoginUser().getId())
				loginbean.setLoginUser(user);
		} catch (EJBException e) {
			FacesContext cxt=FacesContext.getCurrentInstance();
			cxt.addMessage("userForm:email", new FacesMessage("Email is already exist"));
			return null;
		}
		return "/admin/users?faces-redirect=true";
	}
	
	public String remove(int id) {
		service.remove(id);
		return "/admin/users?faces-redirect=true";
	}
	
	
	public List<Users> getUsers(){
		return service.findAll();
	}

	public ACCESS_LEVEL[] getLevels() {
		
		return ACCESS_LEVEL.values();
	}
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	
}
