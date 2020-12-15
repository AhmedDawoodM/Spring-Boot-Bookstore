package com.bookstore;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bookstore.domain.User;
import com.bookstore.domain.security.Role;
import com.bookstore.domain.security.UserRole;
import com.bookstore.service.UserService;
import com.bookstore.utility.SecurityUtility;

@SpringBootApplication
public class BookstoreApplication implements CommandLineRunner {

	public static void main(String[] args)  {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Autowired
	private UserService userService;
	
	@Override
	public void run(String ... args) throws Exception {
		
		User user1 = new User();
		user1.setFirstName("Ahmed");
		user1.setLastName("Dawood");
		user1.setUsername("d");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("d"));
		user1.setEmail("ahmeddawood9898@gmail.com");
		Set<UserRole> userRoles = new HashSet<>();
		Role role1 = new Role();
		role1.setRoleId(1);
		role1.setName("ROLE_USER");
		userRoles.add(new UserRole(user1, role1));
		
		userService.createUser(user1, userRoles);
		
		
		
	}
	
	
 
}
