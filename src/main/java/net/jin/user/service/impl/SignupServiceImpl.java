package net.jin.user.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.jin.repository.UserRepository;
import net.jin.user.service.SignupService;
import net.jin.model.User;


@Service
public class SignupServiceImpl implements SignupService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private User user;
	
	@Override
	public String SignupUser(HttpServletRequest httpServletRequest) {
		//String userId = httpServletRequest.getParameter();
		userRepository.save(user);
		return "redirect:/users/list"; // not real location. just for communication
	}
}
