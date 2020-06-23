package net.jin.user.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.jin.model.User;
import net.jin.repository.UserRepository;
import net.jin.user.service.LoginService;
import net.jin.util.HttpSessionUtils;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private UserRepository userRepository;
	
	
	public String loginUser(String userId, String password, HttpSession session) {
		User user = userRepository.findByUserId(userId); // UserRepository.java에 정의
		if (user == null) {
			System.out.println("Key in!");
			return "redirect:/users/loginForm";
		}
		//if (!password.equals(user.getPassword())) {
		//user.java 에 matchNewPassword() 정의해서 로직으로 쓴다
		if (!user.matchNewPassword(password)) {			
			System.out.println("Login Failure!");
			return "redirect:/users/loginForm";
		}

		System.out.println("Login Success!");
		//session.setAttribute("sessionedUser", user);
		session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);
				
		return "redirect:/";
	}
}
