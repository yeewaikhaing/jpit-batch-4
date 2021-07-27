package com.mmit.shop;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class ShopCommonException extends RuntimeException{

	
	private static final long serialVersionUID = 1L;

	public ShopCommonException(String msg) {
		super(msg);
	}
}
