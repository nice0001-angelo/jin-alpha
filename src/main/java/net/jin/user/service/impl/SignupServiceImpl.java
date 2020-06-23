package net.jin.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.jin.repository.UserRepository;
import net.jin.user.service.SignupService;
import net.jin.model.User;


@Service
public class SignupServiceImpl implements SignupService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public String SignupUser(User user) {
		userRepository.save(user);
		return "redirect:/users/list"; // not real location. just for communication
	}
}
