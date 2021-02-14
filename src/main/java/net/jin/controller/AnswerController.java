/*
 * This is a Jin-alpha Project
 * File name : AnswerController.java
 * Created by : Jinhyun
 * Created on : Dec 2019
 * Contents : for the "/answers/{questionId}/answers" call controller
 */
package net.jin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import net.jin.answer.service.GoUpdateAnswerFormService;

@Controller//빈
@RequestMapping("/answers/{questionId}/answers")
public class AnswerController {

	@Autowired
	private GoUpdateAnswerFormService goUpdateAnswerFormService;
	
	@GetMapping("/{id}/goUpdateAnswerForm")
	public String goUpdateAnswerForm(@PathVariable Long questionId, @PathVariable Long id, HttpSession session, Model model) {
		String page = goUpdateAnswerFormService.goUpdateAnswerForm(questionId, id, session, model);
		return page;
	}
}
