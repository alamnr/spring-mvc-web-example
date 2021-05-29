package com.telusko.data.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
   	     

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		String userName = super.obtainUsername(request);
		String password = super.obtainPassword(request);
		String make = request.getParameter("make");

		CustomAuthenticationToken token = new CustomAuthenticationToken(userName, password, make);

		super.setDetails(request, token);
		return this.getAuthenticationManager().authenticate(token);
	}

}
