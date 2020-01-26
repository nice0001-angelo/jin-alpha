/*
 * This is a Jin-alpha Project
 * File name : ApiUserController.java
 * Created by : Jinhyun
 * Created on : Jan 2020
 * Contents : for the "/api/users" call controller
 */
package net.jin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.jin.domain.User;
import net.jin.domain.UserRepository;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/{id}")
	public User show(@PathVariable Long id) {
		return userRepository.findById(id).get();
	}

}
