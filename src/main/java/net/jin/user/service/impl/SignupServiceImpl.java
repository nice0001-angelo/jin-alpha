package net.jin.user.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.jin.model.User;
import net.jin.repository.UserRepository;
import net.jin.user.service.SignupService;

@Service
public class SignupServiceImpl implements SignupService{
	
	@Autowired
	private UserRepository userRepository;
	
    @Override
	public String signupUser(HttpServletRequest httpServletRequest) {
    	String userId = httpServletRequest.getParameter("userId");
    	String password = httpServletRequest.getParameter("password");
    	String name = httpServletRequest.getParameter("name");
    	String email = httpServletRequest.getParameter("email");
    	
    	if(userId.equals("")||password.equals("")||name.equals("")||email.equals("")) {
    		return "signupForm";
    	}
    	   	
    	
    	User user = new User();
		userRepository.save(user);
		return "redirect:/users/list"; // not real location. just for communication
	}
}
