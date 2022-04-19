package com.gl.employeeMgmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.gl.employeeMgmt.dao.UserRepository;
import com.gl.employeeMgmt.entity.User;
import com.gl.employeeMgmt.security.MyUserDetails;

public class UserServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository usrRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = usrRepo.getUserByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}

		else {
			return new MyUserDetails(user);
		}
	}

}
