package net.jin.user.service.impl;

import javax.servlet.http.HttpServletRequest;
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
	
	@Override
	// HttpServletRequest로 session 까지 포함하여 post하는것이 일반적임. 서버에서 가져올때는 HttpServletResponse를 많이 씀)
	// HttpServletResponse는 화면을 아예 그려주므로 자유도를 높히기 위해서 어씽크 방식으로 Ajax 타입으로 데이터만 보내서 화면을 그림
	public String loginUser(HttpServletRequest httpServletRequest) {
		String userId = httpServletRequest.getParameter("userId");
		String password = httpServletRequest.getParameter("password");
		HttpSession session = httpServletRequest.getSession();
		
		User user = userRepository.findByUserId(userId); // UserRepository.java에 정의
		if (user == null) {
			System.out.println("There is no matched User Id~!");
			return "redirect:/users/loginForm";
		}
		if (!user.matchNewPassword(password)) {			
			System.out.println("Wrong password~!");
			return "redirect:/users/loginForm";
		}

		System.out.println("Login Success!");
		session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);
				
		return "redirect:/";
	}
}
