package com.mmit.shop;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mmit.shop.bean.LoginBean;

@WebFilter({"/public/checkout.xhtml","/public/customer-orders.xhtml"})
public class ShopWebFilter implements Filter{

	@Inject
	private LoginBean loginbean;
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// not login
		if(loginbean.getLoginUser().getId() == 0) {
			HttpServletRequest req=(HttpServletRequest) request;
			HttpServletResponse resp=(HttpServletResponse) response;
			
			req.getServletContext()
			.getRequestDispatcher("/public/customer-login.xhtml")
			.forward(req, resp);
		}
		else {
			chain.doFilter(request, response);
		}
	}

}
