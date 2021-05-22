package com.telusko.web;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.telusko.data.domain.AppUser;

@Component
public class AddService {
	
	public int addMe(int i,int j) {
		AppUser user = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(user);
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
		return i+j;
	}

}
