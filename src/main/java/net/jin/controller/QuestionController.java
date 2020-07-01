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
import net.jin.model.Result;
import net.jin.model.User;
import net.jin.question.service.CreateQuestionService;
import net.jin.question.service.GoQuestionFormSerivce;
import net.jin.question.service.GoUpdateQuestionFormService;
import net.jin.question.service.ShowQuestionService;
import net.jin.repository.QuestionRepository;
import net.jin.util.HttpSessionUtils;

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

	@GetMapping("/{id}/form")
	public String goUpdateQuestionForm(@PathVariable Long id, Model model, HttpSession session) {
		String page = goUpdateQuestionForm(id, model, session);
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
	
	public Result valid(HttpSession session, Question question) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return Result.fail("You have to do this after login");
		}
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		if (!question.isSameWriter(loginUser)) {
			return Result.fail("Your login is not matched");
		}
		return Result.ok();
	}

	@PostMapping("/{id}/update")
	public String update(@PathVariable Long id, String title, String contents, Model model, HttpSession session) {
		Question question = questionRepository.findById(id).get(); // refactoring 의 local variable를 통해서 추출하고 자동 변경된것임
		Result result = valid(session, question);
		if (!result.isValid()) {
			model.addAttribute("errorMessage", result.getErrorMessage()); // Excception into errorMessage and return to
																			// /user/login.html
			return "user/login";
		}

		question.update(title, contents);
		questionRepository.save(question);
		return String.format("redirect:/questions/%d", id);

	}

	@PostMapping("/{id}/delete")
	public String delete(@PathVariable Long id, Model model, HttpSession session) {
		Question question = questionRepository.findById(id).get(); // refactoring 의 local variable를 통해서 추출하고 자동 변경된것임
		Result result = valid(session, question);
		if (!result.isValid()) {
			model.addAttribute("errorMessage", result.getErrorMessage()); // Excception into errorMessage and return to
																			// /user/login.html
			return "user/login";
		}

		questionRepository.deleteById(id);
		return "redirect:/";
	}
}
