package com.telusko.web.java.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

//@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
			
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("pass")).roles("USER")
        		.and().withUser("admin").password(passwordEncoder().encode("pass")).roles("USER,ADMIN");
    }
	
	protected void configure(HttpSecurity http) throws Exception {
	    http.authorizeRequests()
	    	.antMatchers("/","/add").hasAnyRole("USER","ADMIN")
	    	.antMatchers("/secure").hasRole("ADMIN")
	    	.antMatchers("/resources/**", "/about").permitAll()
	    	
	    	.anyRequest().authenticated()
	    	//.and().httpBasic();
	    	.and().formLogin().permitAll()
	    	.and().logout().logoutUrl("/logout").logoutSuccessUrl("/login").permitAll();
	        //.and().csrf().disable();
	    
		
	}
	
	/*The above default configuration makes sure any request to the application is authenticated 
	with form based login or HTTP basic authentication.
	
	Also, it is exactly similar to the following XML configuration:
		
	<http>
		<intercept-url pattern="/**" access="isAuthenticated()"/>
		<form-login />
		<http-basic />
	</http>*/
	
	//@Bean
	public PasswordEncoder passwordEncoder() {
		
	    return  NoOpPasswordEncoder.getInstance();
	}
}

