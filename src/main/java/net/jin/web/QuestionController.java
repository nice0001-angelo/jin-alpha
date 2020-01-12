package net.jin.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		//Question newQuestion = new Question(sessionUser.getUserId(), title, contents);
		// Question.java 안에서 User와 관계를 맺었기 때문에 User객체를 바로 가져올수 있음
		// 객체내에서 get으로 꺼내올 생각 말고 바로 객체를 가져오는 것을 고려하자~!
		Question newQuestion = new Question(sessionUser, title, contents);
		questionRepository.save(newQuestion);
		return "redirect:/";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable Long id, Model model) {
		model.addAttribute("question", questionRepository.findById(id).get());
		return "/qna/show";
	}
	
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
		System.out.println(session);

		
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "/user/login";
		}

		
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		
		//System.out.println(loginUser);
		
		Question question = questionRepository.findById(id).get(); //refactoring 의 local variable를 통해서 추출하고 자동 변경된것임
		if (!question.isSameWriter(loginUser)) {
			return "/user/login";
		}
		

		
		model.addAttribute("question", question); //해당 id에 해당하는 data를 question 테이블에서 가져다가 return 한다
		return "/qna/updateForm";
	}
	
	@PostMapping("/{id}/update")
	public String update(@PathVariable Long id, String title, String contents) {
		Question question = questionRepository.findById(id).get();
		question.update(title, contents);
		questionRepository.save(question);
		return String.format("redirect:/questions/%d", id);
	}
	

	@PostMapping("/{id}/delete")
	public String delete(@PathVariable Long id, HttpSession session) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "/user/login";
		}
		
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		Question question = questionRepository.findById(id).get(); //refactoring 의 local variable를 통해서 추출하고 자동 변경된것임
		if (!question.isSameWriter(loginUser)) {
			return "/user/login";
		}
		
		questionRepository.deleteById(id);
		return "redirect:/";
	}
}
