/*
 * This is a Jin-alpha Project
 * File name : QuestionController.java
 * Created by : Jinhyun
 * Created on : Jan 2020
 * Contents : for the "/questions" call controller
 */
//Exception delete refactoring

package net.jin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.jin.model.Question;
import net.jin.model.User;
import net.jin.question.service.CreateQuestionService;
import net.jin.question.service.DeleteQuestionService;
import net.jin.question.service.GoQuestionFormSerivce;
import net.jin.question.service.GoUpdateQuestionFormService;
import net.jin.question.service.ShowQuestionService;
import net.jin.question.service.UpdateQuestionService;
import net.jin.repository.QuestionRepository;
import net.jin.util.HttpSessionUtils;
import net.jin.util.ResultUtils;

@Controller
@RequestMapping("/questions")
public class QuestionController {

	@Autowired // 스프링프레임워크가 사용하고 싶으니 나에게 인자를 전달해달라는 어노테이션
	private QuestionRepository questionRepository; // 스프링프레임워크가 questionRepository라는 구현체를 만들어서 알아서 관리해줌

	@Autowired
	private GoQuestionFormSerivce goQuestionFormService;
	
	@Autowired
	private CreateQuestionService createQuestionService;
	
	@Autowired
	private ShowQuestionService showQuestionService;
	
	@Autowired
	private GoUpdateQuestionFormService goUpdateQuestionFormService;
	
	@Autowired
	private UpdateQuestionService updateQuestionService;
	
	@Autowired
	private DeleteQuestionService deleteQuestionService;
	
	@GetMapping("/goQuestionForm")
	public String goQuestionForm(HttpServletRequest httpServletRequest) {
		String page = goQuestionFormService.goQuestionForm(httpServletRequest);
		return page;
	}

	@PostMapping("/createQuestion")
	public String createQuestion(HttpServletRequest httpServletRequest) {
		String page = createQuestionService.createQuestion(httpServletRequest);
		return page;
		}

	@GetMapping("/{id}/showQuestion")
	public String showQuestion(@PathVariable Long id, Model model) {
		String page = showQuestionService.showQuestion(id, model);
		return page;
	}

	@GetMapping("/{id}/goUpdateQuestionForm")
	public String goUpdateQuestionForm(@PathVariable Long id, Model model, HttpSession session) {
		String page = goUpdateQuestionFormService.goUpdateQuestionForm(id, model, session);
		return page;
	}
	
	@PostMapping("/{id}/updateQuestion")
	public String updateQuestion(@PathVariable Long id, String title, String contents, Model model, HttpSession session) {
		String page = updateQuestionService.updateQuestion(id, title, contents, model, session);
		return page;
		}
	
	@PostMapping("/{id}/deleteQuestion")
	public String deleteQuestion(@PathVariable Long id, Model model, HttpSession session) {
		String page = deleteQuestionService.deleteQuestion(id, model, session);
		return page;
	}

	private boolean hasPemission(HttpSession session, Question question) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			throw new IllegalStateException("You have to do this after login");
		}
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		if (!question.isSameWriter(loginUser)) {
			throw new IllegalStateException("Your login is not matched");
		}
		return true;
	}
	
	public ResultUtils valid(HttpSession session, Question question) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return ResultUtils.fail("You have to do this after login");
		}
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		if (!question.isSameWriter(loginUser)) {
			return ResultUtils.fail("Your login is not matched");
		}
		return ResultUtils.ok();
	}


}
