package net.jin.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.jin.domain.User;
import net.jin.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {
	

	@Autowired
	private UserRepository userRepository; //UserRepository는 스프링부트에서 알아서 생성해줌

	
	@GetMapping("/profile")
	public String profile() {
		return "user/profile";
	}
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "user/login";
	}
	
	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session) {
		User user = userRepository.findByUserId(userId); //UserRepository.java에 정의
		if (user == null) {
			System.out.println("Key in!");
			return "redirect:loginForm";
		}
		if (!password.equals(user.getPassword())) {
			System.out.println("Login Failure!");
			return "redirect:loginForm";
		}
		
		System.out.println("Login Success!");
		session.setAttribute("user", user);
		
		return "redirect:/";		
	}
	
	@GetMapping("/form")
	public String form() {
		return "user/form";
	}
	
	@PostMapping("") //not real location. just for communication
	public String create(User user) {
		System.out.println("User: " + user);
		userRepository.save(user);
		return "redirect:/users"; //not real location. just for communication
	}
	
	@GetMapping("") //not real location. just for communication
	public String list(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "user/list"; //real location(src/main/resources/static/user/list.html)
	}
	
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model) {
		//User user = userRepository.findOne(id); findOne는 에러가 나서 아래와 같이 바꿈 Java 8 optional
		//model.addAttribute("users", user);
		User user = userRepository.findById(id).get();
		model.addAttribute("user",user);
		return "user/updateForm"; //real location(src/main/resources/static/user/updateForm.html)
	}
	
	@PostMapping("/{id}")
	public String update(@PathVariable Long id, User newUser) {
		User user = userRepository.findById(id).get();
		user.update(newUser);
		userRepository.save(user);
		return "redirect:/users";
	}
}
