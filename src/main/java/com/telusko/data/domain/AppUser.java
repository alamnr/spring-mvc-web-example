package com.telusko.data.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AppUser implements UserDetails {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique = true)
	private String name;
	
	private String password;
	
	
	@OneToMany(cascade = CascadeType.PERSIST)
	Set<Role> roles;


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<String> roles = new ArrayList<String>();
		for (Role obj : this.getRoles()) {
			roles.add(obj.getRoleName());
		}
		return AuthorityUtils.createAuthorityList((String[]) roles.toArray());
	}


	@Override
	public String getUsername() {
		
		return this.getName();
	}


	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}


	@Override
	public boolean isEnabled() {
		
		return true;
	}
	
	

}
