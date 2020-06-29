/*
 * This is a Jin-alpha Project
 * File name : UserController.java
 * Created by : Jinhyun
 * Created on : Jan 2020
 * Contents : for the "/users" call controller
 */
package net.jin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.jin.model.User;
import net.jin.repository.UserRepository;
import net.jin.user.service.LoginService;
import net.jin.user.service.SignupService;
import net.jin.util.HttpSessionUtils;

@Controller
@RequestMapping("/users")
public class UsersController {


	@Autowired
	private UserRepository userRepository; // UserRepository는 스프링부트에서 알아서 생성해줌
	
	@Autowired
	private LoginService loginService;

	@Autowired
	private SignupService signupService;
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}

	@GetMapping("/signupForm")
	public String form() {
		return "user/signupForm";
	}
	
	@PostMapping("/loginRequest") 
	public String loginRequest(HttpServletRequest httpServletRequest) { 
		String page = loginService.loginUser(httpServletRequest); 
		return page; 
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY); // HttpSessionUtils.java 에 상수 선언하고 가져다 씀
		return "redirect:/";
	}

	@PostMapping("/signupRequest") 
	public String signupRequest(HttpServletRequest httpServletRequest ) {
		String page = signupService.signupUser(httpServletRequest);
		return page;
	}
	
	@GetMapping("/userList") 
	public String list(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "user/userList"; // real location(src/main/resources/static/user/list.html)
	}

	// userList.html 화면에서 특정 사용자 정보를 update하기 위한 정보를 updateForm.html에 전달하기 위한 메소드
	// id를 unique key로 해서 전체 값을 넘김
	@GetMapping("/{id}/updateUserForm")
	public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
	
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/loginForm";
		}

		// 자기 ID로 로그인한 정보만 수정할 수 있도록 로직 추가(로그인 상태에서 다른 아이디도 수정할 수 있는 문제점 제거)
		//User sessionedUser = (User) tempUser;
		User sessionedUser = HttpSessionUtils.getUserFromSession(session);
		//if (!id.equals(sessionedUser.getId())) { // User.java 에 getId 생성 했음. 아래에 matchNewId 로 대체
		if(!sessionedUser.matchNewId(id)) {
			throw new IllegalStateException("You can update with only yours~!");
		}

		// User user = userRepository.findOne(id); findOne는 에러가 나서 아래와 같이 바꿈 Java 8
		// optional
		// model.addAttribute("users", user);
		// findById(id) 대신에 콕 집어서 getter 로 값을 가져 오게 되면 위에서 if(!id.equals~~ 에서부터 3줄은 주석
		// 처리 해도 됨
		User user = userRepository.findById(id).get();
		// User user = userRepository.findById(sessionedUser.getId()).get();
		model.addAttribute("user", user);
		return "user/updateUserForm"; // real location(src/main/resources/templates/user/updateForm.html)
	}

	// list.html로 부터 넘겨 받은 정보를 updateForm.html에서 수정한 후 저장하기 위한 메소드
	// 저장후 list.html 화면을 호출하여 정보를 display 하도록 함.
	// updateForm.html 화면에서 Information update 버튼 클릭시 method="post"
	// action="/users/{{id}}" 을 통해서 호출됨
	@PostMapping("/{id}")
	public String signupUpdate(@PathVariable Long id, User updatedUser, HttpSession session) {
		// login이 되어 있는 상태에서만 개인정보를 수정할 수 있도록 함. session이 null 일때는 수정이 안되고 login 화면으로
		// 이동토록 함
		//Object tempUser = session.getAttribute("sessioneduser");
		//if (tempUser == null) {  //세션관리 메소드를 만들아서 아래와 같이 체크 가능함
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/loginForm";
		}

		// 자기 ID로 로그인한 정보만 수정할 수 있도록 로직 추가(로그인 상태에서 다른 아이디도 수정할 수 있는 문제점 제거)
		//User sessionedUser = (User) tempUser;
		User sessionedUser = HttpSessionUtils.getUserFromSession(session);
		//if (!id.equals(sessionedUser.getId())) { // User.java 에 getId 생성 했음 아래에  matchNewId로 데체
		if(!sessionedUser.matchNewId(id)) {
			throw new IllegalStateException("You can update with only yours~!");
		}

		User user = userRepository.findById(id).get();
		user.update(updatedUser);
		userRepository.save(user);
		return "redirect:/users";
	}
}
