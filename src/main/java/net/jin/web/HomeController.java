/*
 * This is a Jin-alpha Project
 * File name : HomeController.java
 * Created by : Jinhyun
 * Created on : Jan 2020
 * Contents : for the main home page controller
 */
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
		//question table의 모든 데이터를 "questions" 라는 이름에 담아서 index.html에 전달 {{#questions}}{{/questions}} 이용
		model.addAttribute("questions", questionRepository.findAll()); 
		return "index";
	}

}
