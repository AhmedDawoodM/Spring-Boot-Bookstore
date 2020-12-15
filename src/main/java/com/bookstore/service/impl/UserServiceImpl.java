package com.bookstore.service.impl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.domain.User;
import com.bookstore.domain.security.PasswordResetToken;
import com.bookstore.domain.security.UserRole;
import com.bookstore.repository.PasswordResetTokenRepository;
import com.bookstore.repository.RoleRepository;
import com.bookstore.repository.UserRepository;
import com.bookstore.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
 
	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public PasswordResetToken getPasswordResetToken(final String token) {
		// TODO Auto-generated method stub
		return passwordResetTokenRepository.findByToken(token);
	}

	@Override
	public void createPasswordResetTokenForUser(final User user, final String token) {
		final PasswordResetToken myToken = new PasswordResetToken(token, user);
		
		passwordResetTokenRepository.save(myToken);
		
		
	}

	@Override
	public User findByUsername(String username) {	
		return userRepository.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) {	
		return userRepository.findByEmail(email);
	}

	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {	
		User localUser = userRepository.findByUsername(user.getUsername());
		
		if(localUser != null) {
			LOG.info("user with name \"{}\" already exists.", user.getUsername());
		} else {
			for (UserRole userRole : userRoles) {
				roleRepository.save(userRole.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
			
			localUser = userRepository.save(user);
		}
		return localUser;
	}
	

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}
	
	

}
