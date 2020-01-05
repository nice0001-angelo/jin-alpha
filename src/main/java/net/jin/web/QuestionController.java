package net.jin.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.jin.domain.Question;
import net.jin.domain.QuestionRepository;
import net.jin.domain.User;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	
	@Autowired //스프링프레임워크가 사용하고 싶으니 나에게 인자를 전달해달라는 어노테이션
	private QuestionRepository questionRepository; //스프링프레임워크가 questionRepository라는 구현체를 만들어서 알아서 관리해줌
	
	@GetMapping("/form")
	public String form(HttpSession session) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "/users/loginform";
		}
		return "qna/form";
	}
	
	@PostMapping("")
	public String create(String title, String contents, HttpSession session) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "/users/loginform";
		}
		
		User sessionUser = HttpSessionUtils.getUserFromSession(session);
		Question newQuestion = new Question(sessionUser.getUserId(), title, contents);
		questionRepository.save(newQuestion);
		return "redirect:/";
	}
}
