package com.mmit.shop.bean;


import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mmit.shop.ShopCommonException;
import com.mmit.shop.model.entity.Users;

@Named
@SessionScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private String email;
	private String password;
	private Users loginUser;
	@Inject
	private SecurityContext sec_Context;
	@Inject
	private ExternalContext ex_Context;
	public String login() {
		
		HttpServletRequest req=(HttpServletRequest) ex_Context.getRequest();
		HttpServletResponse resp=(HttpServletResponse) ex_Context.getResponse();
		
		UsernamePasswordCredential c=new UsernamePasswordCredential(email, password);
		
		try {
			AuthenticationStatus status= sec_Context.authenticate(req, resp, AuthenticationParameters.withParams().credential(c));
			//System.err.println("STATUS : "+status);
			if(status == AuthenticationStatus.SUCCESS)
			{
				return "/index.xhtml?faces-redirect=true";
			}
			
		} catch (ShopCommonException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		}
		
		return null;
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

	public Users getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(Users loginUser) {
		this.loginUser = loginUser;
	}
	
	
}
