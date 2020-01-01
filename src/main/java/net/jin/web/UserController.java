package net.jin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
	
	@GetMapping("/form")
	public String form() {
		return "user/form";
	}
	
	@PostMapping("") //not real location. just for communication
	public String create(User user) {
		System.out.println("User: " + user);
		//users.add(user);
		userRepository.save(user);
		return "redirect:/users"; //not real location. just for communication
	}
	
	@GetMapping("") //not real location. just for communication
	public String list(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "/user/list"; //real location(src/main/resources/static/user/list.html)
	}
}
