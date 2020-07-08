/*
 * This is a Jin-alpha Project
 * File name : ApiAnsewerController.java
 * Created by : Jinhyun
 * Created on : Jan 2020
 * Contents : for the "/api/questions/{questionId}/answers" call controller
 */
package net.jin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.jin.answer.service.CreateAnsewerService;
import net.jin.model.Answer;
import net.jin.model.Question;
import net.jin.model.User;
import net.jin.repository.AnswerRepository;
import net.jin.repository.QuestionRepository;
import net.jin.util.HttpSessionUtils;
import net.jin.util.ResultUtils;

@RestController
@RequestMapping("/api/questions/{questionId}/answers")
public class ApiAnswerController {
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	CreateAnsewerService createAnswerService;
	
	@PostMapping("")
	public Answer createAnswer(@PathVariable Long questionId, String contents, HttpSession session) {
		Answer page = createAnswerService.createAnswer(questionId, contents, session);
		return page;
	}


	
	@DeleteMapping("/{id}")
	public ResultUtils deleteAnswer(@PathVariable Long questionId, @PathVariable Long id, HttpSession session) {
		System.out.println("**************Stsrt API deleteAnswer************");
		if(!HttpSessionUtils.isLoginUser(session)) {
			return ResultUtils.fail("You have to login first");
		}
		
		Question question = questionRepository.findById(questionId).get();		
		Answer answer = answerRepository.findById(id).get();
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		if(!answer.isSameWriter(loginUser)) {
			return ResultUtils.fail("You can delete only your answer");
		}
		answerRepository.deleteById(id);
		question.deleteAnswer();
		questionRepository.save(question);
		return ResultUtils.ok();
	}
}
