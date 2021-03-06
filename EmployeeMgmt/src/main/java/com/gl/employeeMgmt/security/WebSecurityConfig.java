package com.gl.employeeMgmt.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gl.employeeMgmt.service.UserServiceImpl;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/h2-console/**").permitAll().antMatchers(HttpMethod.GET, "/api/employees")
				.permitAll().antMatchers(HttpMethod.POST, "/api/user").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.POST, "/api/role").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.GET, "/api/employees/*").permitAll()
				.antMatchers(HttpMethod.GET, "/api/employees/search/*").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/employees").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/api/employees/*").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.POST, "/api/employees").hasAuthority("ADMIN").anyRequest().authenticated().and()
				.httpBasic().and().cors().and().csrf().disable();
		http.headers().frameOptions().disable();
	}

}
