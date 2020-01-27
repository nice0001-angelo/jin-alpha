/*
 * This is a Jin-alpha Project
 * File name : HomeController.java
 * Created by : Jinhyun
 * Created on : Jan 2020
 * Contents : for the main home page controller
 */
package net.jin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.jin.domain.QuestionRepository;

@Controller
public class HomeController {

	@Autowired
	private QuestionRepository questionRepository;
	
	@RequestMapping("")
	// spring-data-jpa를 활용하여 Pageable로 페이지 처리
	// PageableDefault 로 한페이지당 보이는 갯수, Sort 방식 정의
	public String home(Model model, @PageableDefault(sort = {"id"}, direction=Direction.DESC, size=5) Pageable pageable) {
		//question table의 모든 데이터를 "questions" 라는 이름에 담아서 index.html에 전달 {{#questions}}{{/questions}} 이용
		model.addAttribute("questions", questionRepository.findAll(pageable)); 
		return "index";
	}

}
