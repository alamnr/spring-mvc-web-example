package com.telusko.data.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


import com.telusko.data.domain.Role;
import com.telusko.data.domain.AppUser;
import com.telusko.data.repo.UserRepo;



@Component("customUserDetailService")
public class CustomUserDetailService implements UserDetailsService {

	
	@Autowired
	UserRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		
		/*AppUser user = repo.findByName(name); 	
			 
		return new User(user.getName(),user.getPassword(),
										AuthorityUtils.createAuthorityList((String[]) user.getRoles().toArray()));*/
		
		return repo.findByUserName(name);
	}

}
