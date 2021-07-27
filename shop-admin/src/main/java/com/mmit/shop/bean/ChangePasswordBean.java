package com.mmit.shop.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mmit.shop.ShopCommonException;
import com.mmit.shop.model.service.UsersService;


@Named
@ViewScoped
public class ChangePasswordBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private String oldPassword;
	private String newPassword;
	private String confirmPass;
	@Inject
	private UsersService service;
	
	public String changePassword() {
		
		try {
			service.changePassword(oldPassword,newPassword,confirmPass);
		} catch (ShopCommonException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
			return null;
		}
		
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		
		return "/index?faces-redirect=true";
	}
	
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPass() {
		return confirmPass;
	}
	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}
	
	
	
}
