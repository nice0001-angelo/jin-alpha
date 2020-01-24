package net.jin.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.jin.domain.Answer;
import net.jin.domain.AnswerRepository;
import net.jin.domain.Question;
import net.jin.domain.QuestionRepository;
import net.jin.domain.Result;
import net.jin.domain.User;

@Controller
@RequestMapping("/answers/{questionId}/answers")
public class AnswerController {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long questionId, @PathVariable Long id, HttpSession session, Model model) {
		Answer answer = answerRepository.findById(id).get();
		Question question = questionRepository.findById(questionId).get(); // refactoring 의 local variable를 통해서 추출하고 자동 변경된것임
		Result result = valid(session, answer);
		if (!result.isValid()) {
			model.addAttribute("errorMessage", result.getErrorMessage()); // Excception into errorMessage and return to
																			// /user/login.html
			return "user/login";
		}


		User loginUser = HttpSessionUtils.getUserFromSession(session);
		if(!answer.isSameWriter(loginUser)) {
			System.out.println("You can update only your answer");
		}
		
		model.addAttribute("answer", answer); // 해당 id에 해당하는 data를 question 테이블에서 가져다가 return 한다
		System.out.print(model);
		return "qna/answerUpdateForm";
	}
	

	
	private Result valid(HttpSession session, Answer answer) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return Result.fail("You have to do this after login");
		}
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		if (!answer.isSameWriter(loginUser)) {
			return Result.fail("Your login is not matched");
		}
		return Result.ok();
	}
	
}
