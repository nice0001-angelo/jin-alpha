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
import net.jin.user.service.GoUpdateUserFormService;
import net.jin.user.service.LoginService;
import net.jin.user.service.SignupService;
import net.jin.user.service.UpdateUserService;
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

	@Autowired
	private UpdateUserService updateUserService;

	
	@Autowired
	private GoUpdateUserFormService goUpdateUserFormService;
	
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
	public String signupRequest(HttpServletRequest httpServletRequest) {
		String page = signupService.signupUser(httpServletRequest);
		return page;
	}

	@GetMapping("/userList")
	public String list(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "user/userList"; 
	}

	@GetMapping("/{id}/goUpdateUserForm")
	public String goUpdateUserForm(@PathVariable Long id, Model model, HttpSession session) {
		String page = goUpdateUserFormService.goUpdateUserForm(id, model, session);
		return page;
	}

	// list.html로 부터 넘겨 받은 정보를 updateForm.html에서 수정한 후 저장하기 위한 메소드
	// 저장후 list.html 화면을 호출하여 정보를 display 하도록 함.
	// updateForm.html 화면에서 Information update 버튼 클릭시 method="post"
	// action="/users/{{id}}" 을 통해서 호출됨
	@PostMapping("/{id}/updateUser")
	public String updateUser(@PathVariable Long id, User updatedUser, HttpSession session) {
		String page = updateUserService.updateUser(id, updatedUser, session);
		return page;
	}
}
