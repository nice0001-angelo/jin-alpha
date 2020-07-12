/*
 * This is a Jin-alpha Project
 * File name : ApiUserController.java
 * Created by : Jinhyun
 * Created on : Jan 2020
 * Contents : for the "/api/users" call controller
 */
package net.jin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.jin.model.User;
import net.jin.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/{id}")
	public User show(@PathVariable Long id) {
		return userRepository.findById(id).get();
	}

	@GetMapping("/userList")
	public String list(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "user/userList"; 
	}
}
