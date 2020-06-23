/*
 * This is a Jin-alpha Project
 * File name : QuestionController.java
 * Created by : Jinhyun
 * Created on : Jan 2020
 * Contents : for the "/questions" call controller
 */
//Exception delete refactoring

package net.jin.controller;

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
import net.jin.repository.QuestionRepository;
import net.jin.util.HttpSessionUtils;

@Controller
@RequestMapping("/questions")
public class QuestionController {

	@Autowired // 스프링프레임워크가 사용하고 싶으니 나에게 인자를 전달해달라는 어노테이션
	private QuestionRepository questionRepository; // 스프링프레임워크가 questionRepository라는 구현체를 만들어서 알아서 관리해줌

	@GetMapping("/form")
	public String form(HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/loginForm";
		}
		return "qna/form";
	}

	@PostMapping("")
	public String create(String title, String contents, HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/loginform";
		}

		User sessionUser = HttpSessionUtils.getUserFromSession(session);
		// Question newQuestion = new Question(sessionUser.getUserId(), title,
		// contents);
		// Question.java 안에서 User와 관계를 맺었기 때문에 User객체를 바로 가져올수 있음
		// 객체내에서 get으로 꺼내올 생각 말고 바로 객체를 가져오는 것으로 수정
		Question newQuestion = new Question(sessionUser, title, contents);
		questionRepository.save(newQuestion);
		return "redirect:/";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable Long id, Model model) {
		model.addAttribute("question", questionRepository.findById(id).get());
		return "qna/show";
	}

	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
		Question question = questionRepository.findById(id).get(); // refactoring 의 local variable를 통해서 추출하고 자동 변경된것임
		Result result = valid(session, question);
		if (!result.isValid()) {
			model.addAttribute("errorMessage", result.getErrorMessage()); // Excception into errorMessage and return to
																			// /user/login.html
			return "user/login";
		}

		model.addAttribute("question", question); // 해당 id에 해당하는 data를 question 테이블에서 가져다가 return 한다
		return "qna/updateForm";

	}

	private Result valid(HttpSession session, Question question) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return Result.fail("You have to do this after login");
		}
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		if (!question.isSameWriter(loginUser)) {
			return Result.fail("Your login is not matched");
		}
		return Result.ok();
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
