package com.mmit.shop;

import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

import com.mmit.shop.bean.LoginBean;
import com.mmit.shop.model.entity.Users;
import com.mmit.shop.model.service.UsersService;

@ApplicationScoped
public class CustomIdentityStore implements IdentityStore {

	@Inject
	private UsersService service;
	@Inject
	private Pbkdf2PasswordHash encoder;
	@Inject
	private LoginBean loginbean;
	
	@Override
	public CredentialValidationResult validate(Credential credential) {
		
		UsernamePasswordCredential c=(UsernamePasswordCredential) credential;
		
		Users authUser=service.findByEmail(c.getCaller());
		
		if(authUser == null)
			throw new ShopCommonException("This email does not exist.");
		if(!encoder.verify(c.getPasswordAsString().toCharArray(), authUser.getPassword()))
			throw new ShopCommonException("Password is incorrect");
		
		loginbean.setLoginUser(authUser);
		
		return new CredentialValidationResult(authUser.getEmail(), Set.of(authUser.getLevel().name()));
	}
}
