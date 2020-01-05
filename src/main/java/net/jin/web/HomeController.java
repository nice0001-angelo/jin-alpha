package net.jin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.jin.domain.QuestionRepository;

@Controller
public class HomeController {

	@Autowired
	private QuestionRepository questionRepository;
	
	@GetMapping("")
	public String home(Model model) {
		model.addAttribute("questions", questionRepository.findAll()); //"questions" 라는 이름에 담아서 index.html에 전달 {{#questions}}{{/questions}} 이용
		return "index";
	}

}
