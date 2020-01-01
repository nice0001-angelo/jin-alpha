package net.jin.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import net.jin.domain.User;
import net.jin.domain.UserRepository;

@Controller
public class UserController {
	
	//userRepository로 바꾸면서 필요 없어짐
	//private List<User> users = new ArrayList<User>();
	
	@Autowired
	private UserRepository userRepository; //UserRepository는 스프링부트에서 알아서 생성해줌
	
	@PostMapping("/create")
	public String create(User user) {
		System.out.println("User: " + user);
		//users.add(user);
		userRepository.save(user);
		return "redirect:user/list";
	}
	
	@GetMapping("user/list")
	public String list(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "/user/list";
	}
}
