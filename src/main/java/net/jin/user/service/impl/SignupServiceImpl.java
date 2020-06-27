package net.jin.user.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.jin.model.User;
import net.jin.repository.UserRepository;
import net.jin.user.service.SignupService;
import net.jin.util.SecurityUtils;

@Service
public class SignupServiceImpl implements SignupService{
	
	@Autowired
	private UserRepository userRepository;
	
//	@Autowired
	//private SecurityUtils securityUtils;
	
    @Override
	public String signupUser(HttpServletRequest httpServletRequest) {
    	String userId = httpServletRequest.getParameter("userId");
    	String password = httpServletRequest.getParameter("password");
    	String name = httpServletRequest.getParameter("name");
    	String email = httpServletRequest.getParameter("email");
    	
    	if (userId.equals("")||password.equals("")||name.equals("")||email.equals("")) {
    		return "signupForm";
    	}

    	User user = new User();
//    	String hashedPassword = securityUtils.encryptSHA256(password);
    	
    	user.setUserId(userId);
    	user.setName(name);
//    	user.setPassword(hashedPassword);
      	user.setPassword(password);
    	user.setEmail(email);
    	
		userRepository.save(user);
		return "redirect:/users/list"; // not real location. just for communication
	}
}
