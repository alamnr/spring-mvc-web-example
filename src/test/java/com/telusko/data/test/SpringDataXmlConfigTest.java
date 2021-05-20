package com.telusko.data.test;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.text.ParseException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TestTransaction;

import com.telusko.data.domain.Role;
import com.telusko.data.domain.AppUser;
import com.telusko.data.repo.UserRepo;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-application-context.xml")

public class SpringDataXmlConfigTest {

	@Autowired
	UserRepo repository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	

	@Before
	public void setup() {
		
	}

	@Test
	
	public void testDerivedQuery() throws ParseException {
		
		Role role1 = new Role(null, "ROLE_ADMIN");
		Role role2 = new Role(null, "ROLE_USER");

		Set<Role> roles = new HashSet<Role>(Arrays.asList(role1,role2));
		AppUser user = new AppUser(null,"john",bCryptPasswordEncoder.encode("pass"), roles);
		
		
		
		repository.save(user);
		
		Hibernate.initialize(user);
		
		assertEquals(1, repository.findAll().size());
		
		Hibernate.initialize(user);  // for reading  data from lazy loaded entity
		
		assertEquals(2, repository.findAll().get(0).getRoles().size());
		assertTrue(bCryptPasswordEncoder.matches("pass", repository.findAll().get(0).getPassword()));
		
	}

	
}
