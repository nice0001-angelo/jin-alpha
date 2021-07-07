/*
 * This is a Jin-alpha Project
 * File name : ApiAnsewerController.java
 * Created by : Jinhyun
 * Created on : Jan 2020
 * Contents : for the "/api/questions/{questionId}/answers" call controller
 */
package net.jin.api.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.jin.answer.service.CreateAnsewerService;
import net.jin.answer.service.DeleteAnswerService;
import net.jin.model.Answer;
import net.jin.util.ResultUtils;

@RestController
@RequestMapping("/api/questions/{questionId}/answers")
public class ApiAnswerController {

	@Autowired
	CreateAnsewerService createAnswerService;

	@Autowired
	DeleteAnswerService deleteAnswerService;

	@PostMapping("/createAnswer")
	public Answer createAnswer(@PathVariable Long questionId, String contents, HttpSession session) {
		System.out.println();
		Answer answer = createAnswerService.createAnswer(questionId, contents, session);
		return answer;
	}

	@PostMapping("/{id}/deleteAnswer")
	public ResultUtils deleteAnswer(@PathVariable Long questionId, @PathVariable Long id, HttpSession session) {
		ResultUtils resultUtils = deleteAnswerService.deleteAnswer(questionId, id, session);
		return resultUtils;
	}
}
