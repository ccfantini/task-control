package com.itau.taskcontrol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.itau.taskcontrol.model.UserTaskEntity;
import com.itau.taskcontrol.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserTaskEntity user = userRepository.findByUsername(username);
		if (user != null) {
			return user;
		}
		throw new UsernameNotFoundException(username);
	}

	public UserTaskEntity getLoggedUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ((UserTaskEntity) principal);
	}

	public boolean loggedUserIsAdmin() {
		UserTaskEntity user = getLoggedUser();
		return user.getRole().equals("ADMIN");
	}

}
